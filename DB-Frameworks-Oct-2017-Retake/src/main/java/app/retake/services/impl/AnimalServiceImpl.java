package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelParser modelMapper;
    private final PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, ModelParser modelMapper, PassportRepository passportRepository) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        Passport passport = this.modelMapper.convert(dto.getPassport(),Passport.class);
        this.passportRepository.save(passport);
        Animal animal = modelMapper.convert(dto, Animal.class);
        passport.setAnimal(animal);
        animal.setPassport(passport);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return this.animalRepository.findAnimalsByOwnerPhoneNumber(phoneNumber);
    }

    @Override
    public Animal getByName(String name){
        return this.animalRepository.findOneByName(name);
    }
    @Override
    public Animal getBySerialNumber(String number){
        return this.animalRepository.findOneByPassportSerialNumber(number);
    }

}
