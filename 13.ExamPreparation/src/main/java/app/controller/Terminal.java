package app.controller;

import app.parser.impl.FileIOImpl;
import app.repositories.PhotographRepository;
import app.service.CameraService;
import app.service.impl.CameraServiceImpl;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class Terminal implements CommandLineRunner {

    private final static String CAMERAS_JSON = "D:\\DatabasesFrameworks-Hibernate-SpringData\\13.ExamPreparation\\src\\main\\resources\\files\\json\\cameras.json";

    private PhotographRepository photographRepository;
    private CameraService cameraService;

    @Autowired
    public Terminal(PhotographRepository photographRepository, CameraService cameraService) {
        this.photographRepository = photographRepository;
        this.cameraService = cameraService;
    }

    @Override
    public void run(String... args) throws Exception {
        cameraService.importCameras(CAMERAS_JSON);
    }
}
