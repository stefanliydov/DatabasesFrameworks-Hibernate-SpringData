package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.entities.dto.json.CarJSONImportDTO;
import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = IllegalArgumentException.class)
public class CarServiceImpl implements CarService {

    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private ModelParser modelParser;

    @Autowired
    public CarServiceImpl(RacerRepository racerRepository, CarRepository carRepository, ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(CarJSONImportDTO dto){
        Racer racer = this.racerRepository.findOneByName(dto.getRacerName());
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setPrice(dto.getPrice());
        car.setYearOfProduction(dto.getYearOfProduction());
        car.setMaxSpeed(dto.getMaxSpeed());
        car.setZeroToSixty(dto.getZeroToSixty());
        car.setRacer(racer);
        racer.getCars().add(car);
        this.racerRepository.save(racer);
        this.carRepository.saveAndFlush(car);

    }
}
