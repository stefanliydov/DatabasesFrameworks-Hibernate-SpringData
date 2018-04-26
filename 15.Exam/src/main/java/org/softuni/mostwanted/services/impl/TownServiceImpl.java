package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.json.TownJSONImportDTO;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class TownServiceImpl implements TownService{

    private TownRepository townRepository;
    private ModelParser modelParser;
    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelParser modelParser) {
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(TownJSONImportDTO dto){
        Town townCheck = townRepository.findOneByName(dto.getName());
        if(townCheck!=null){
            throw new IllegalArgumentException("Town already in database!");
        }

        Town town = modelParser.convert(dto, Town.class);
        this.townRepository.saveAndFlush(town);

    }
}
