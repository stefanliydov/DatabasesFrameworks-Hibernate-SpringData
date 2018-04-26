package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.json.DistrictJSONImportDTO;
import org.softuni.mostwanted.entities.models.District;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class DistrictServiceImpl implements DistrictService {

    private TownRepository townRepository;
    private DistrictRepository districtRepository;
    private ModelParser modelParser;
    @Autowired
    public DistrictServiceImpl(TownRepository townRepository, DistrictRepository districtRepository, ModelParser modelParser) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(DistrictJSONImportDTO dto){
        District districtCheck = this.districtRepository.findOneByName(dto.getName());
        if(districtCheck !=null){
            throw new IllegalArgumentException("District already in database!");
        }
        District district = new District();
        district.setName(dto.getName());

        String townName =dto.getTownName();


        if(!townName.isEmpty()) {
            Town town = townRepository.findOneByName(townName);
            town.getDistricts().add(district);
            townRepository.save(town);
            district.setTown(town);
        }
        this.districtRepository.saveAndFlush(district);
    }
}
