package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private ConsoleIO consoleIO;
    private FileIO fileIO;
    private TownController townController;
    private DistrictController districtController;
    private RacerController racerController;
    private CarController carController;
    private RaceEntriesController raceEntriesController;
    private RaceController raceController;

    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIO, TownController townController, DistrictController districtController, RacerController racerController, CarController carController, RaceEntriesController raceEntriesController, RaceController raceController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntriesController = raceEntriesController;
        this.raceController = raceController;
    }
    @Override
    public void run(String... args) throws Exception {
        //JSON import
        consoleIO.write(townController.townImportJSON(fileIO.read(Config.TOWN_JSON_IMPORT)));
        consoleIO.write(districtController.districtJSONImport(fileIO.read(Config.DISTRICT_JSON_IMPORT)));
        consoleIO.write(racerController.racerJSONImport(fileIO.read(Config.RACER_JSON_IMPORT)));
        consoleIO.write(carController.carJSONImport(fileIO.read(Config.CARS_JSON_IMPORT)));

        //XML Import
        consoleIO.write(raceEntriesController.raceEntriesXMLImport(fileIO.read(Config.RACE_ENTRIES_XML_IMPORT)));
        consoleIO.write(raceController.raceXMLImport(fileIO.read(Config.RACES_XML_IMPORT)));

        //JSON Export
        fileIO.write(this.townController.exportAllTownsWithRacersJSON(),"/files/json/output/town-with-racers-export");
        String s = this.racerController.racerExportJSON();
        fileIO.write(this.racerController.racerExportJSON(),"/files/json/output/racing-cars-export");




    }
}
