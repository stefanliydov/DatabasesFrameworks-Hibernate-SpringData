package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.xml.RacesXMLImportDTO;
import org.softuni.mostwanted.entities.models.District;
import org.softuni.mostwanted.entities.models.Race;
import org.softuni.mostwanted.entities.models.RaceEntry;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntriesRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {
    private RaceRepository raceRepository;
    private DistrictRepository districtRepository;
    private RaceEntriesRepository raceEntriesRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntriesRepository raceEntriesRepository) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntriesRepository = raceEntriesRepository;
    }

    @Override
    public void create(RacesXMLImportDTO dto) {
        District district = this.districtRepository.getOneByName(dto.getDistrictName());
        Race race = new Race();
        race.setDistrict(district);
        race.setLaps(dto.getLaps());
        if(dto.getEntriesId()!=null) {
            for (Integer integer : dto.getEntriesId()) {
                RaceEntry raceEntry;
                if (integer != null) {
                    raceEntry = this.raceEntriesRepository.findOne(integer);
                    raceEntry.setRace(race);
                    if (raceEntry != null) {
                        race.getRaceEntrys().add(raceEntry);
                    }
                }
            }
        }
        district.getRaces().add(race);
        this.districtRepository.save(district);
        this.raceRepository.saveAndFlush(race);
    }
}
