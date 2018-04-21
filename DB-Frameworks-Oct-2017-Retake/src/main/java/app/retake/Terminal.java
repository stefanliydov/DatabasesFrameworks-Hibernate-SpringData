package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final AnimalAidController animalAidController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final VetController vetController;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(AnimalAidController animalAidController, FileIO fileIO, ConsoleIO consoleIO, VetController vetController, ProcedureController procedureController) {
        this.animalAidController = animalAidController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {
      // consoleIO.write(this.animalAidController
      //          .importDataFromJSON(this.fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
      // consoleIO.write(this.vetController.importDataFromXML(this.fileIO.read(Config.VETS_IMPORT_XML)));

        this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIO.read(Config.PROCEDURES_IMPORT_XML)));

    }
}
