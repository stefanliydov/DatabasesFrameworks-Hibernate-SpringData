package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.json.RacerJSONImportDTO;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class RacerServiceImpl implements RacerService {

    private RacerRepository racerRepository;
    private TownRepository townRepository;
    private ModelParser modelParser;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(RacerJSONImportDTO dto){
        Racer racerCheck = this.racerRepository.findOneByName(dto.getName());
        if(racerCheck != null){
            throw new IllegalStateException("Racer already in database!");
        }
        Town town =  this.townRepository.findOneByName(dto.getTownName());

        Racer racer = new Racer();
        racer.setName(dto.getName());
        racer.setAge(dto.getAge());
        racer.setBounty(dto.getBountry());
        racer.setHomeTown(town);
        town.getRacers().add(racer);
        this.townRepository.save(town);
        this.racerRepository.saveAndFlush(racer);
    }
}
