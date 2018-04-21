package app.retake.services.impl;

import app.retake.domain.dto.ProcedureAnimalAidXMLExportDTO;
import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {
    private ProcedureRepository procedureRepository;
    private ModelParser modelParser;
    private final VetService vetService;
    private final AnimalService animalService;
    private final AnimalAidService animalAidService;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository,
                                ModelParser modelParser,
                                VetService vetService,
                                AnimalService animalService, AnimalAidService animalAidService) {
        this.procedureRepository = procedureRepository;
        this.modelParser = modelParser;
        this.vetService = vetService;
        this.animalService = animalService;
        this.animalAidService = animalAidService;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Animal animal = this.animalService.getBySerialNumber(dto.getAnimal());
        Vet vet = this.vetService.getByName(dto.getVet());
        if (animal == null || vet == null) {
            throw new IllegalArgumentException("Invalid input!");
        }
        Set<AnimalAid> animalAids = dto.getAnimalsAids().stream()
                .map(animalAid -> {
                    AnimalAid aid = this.animalAidService.getByName(animalAid.getName());
                    if (aid == null) {
                        throw new IllegalArgumentException("Invalid input");
                    }
                    return aid;
                }).collect(Collectors.toSet());

        Procedure procedure = new Procedure();
        procedure.setAnimal(animal);
        procedure.setVet(vet);
        procedure.setDatePerformed(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDate()));
        procedure.setServices(animalAids);
        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<Procedure> procedures = procedureRepository.findAll();
        List<ProcedureXMLExportDTO> export = procedures.stream()
                .map(p -> {

                    List<ProcedureAnimalAidXMLExportDTO> animalAidsDtos = p.getServices()
                            .stream()
                            .map(ai -> new ProcedureAnimalAidXMLExportDTO(ai.getName(), ai.getPrice()))
                            .collect(Collectors.toList());

                    ProcedureXMLExportDTO dto = new ProcedureXMLExportDTO(p.getAnimal().getPassport().getOwnerPhoneNumber(),
                            p.getAnimal().getPassport().getSerialNumber(),
                            animalAidsDtos);
                    return dto;
                })
                .collect(Collectors.toList());

        ProcedureWrapperXMLExportDTO pr = new ProcedureWrapperXMLExportDTO();
        pr.setProcedures(export);
        return pr;
    }

}

