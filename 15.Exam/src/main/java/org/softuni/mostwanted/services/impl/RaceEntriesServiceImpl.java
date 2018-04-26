package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.xml.RaceEntriesXMLImportDTO;
import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.RaceEntry;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntriesRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceEntriesServiceImpl implements RaceEntriesService {

    private RaceEntriesRepository raceEntriesRepository;
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private Parser parser;

    @Autowired
    public RaceEntriesServiceImpl(RaceEntriesRepository raceEntriesRepository,
                                  RacerRepository racerRepository,
                                  CarRepository carRepository, @Qualifier("XMLParser") Parser parser) {
        this.raceEntriesRepository = raceEntriesRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.parser = parser;
    }


    @Override
    public void create(RaceEntriesXMLImportDTO dto) {
        Racer racer = null;
        Car car = null;


        RaceEntry raceEntry = new RaceEntry();
        raceEntry.setFinishTime(dto.getFinishTime());
        raceEntry.setHasFinished(dto.isHasFinished());
        if (dto.getCarId() != null) {
            car = this.carRepository.findOne(dto.getCarId());
            car.getRaceEntries().add(raceEntry);
            this.carRepository.save(car);
        }
        if(dto.getRacer()!=null){
            racer = this.racerRepository.findOneByName(dto.getRacer());
            racer.getRaceEntries().add(raceEntry);
            this.racerRepository.save(racer);
        }
        raceEntry.setRacer(racer);
        raceEntry.setCar(car);
        this.raceEntriesRepository.saveAndFlush(raceEntry);
    }
}
