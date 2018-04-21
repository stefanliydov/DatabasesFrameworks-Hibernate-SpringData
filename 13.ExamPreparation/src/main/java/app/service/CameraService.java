package app.service;

import app.model.entities.dto.BasicCameraDto;

import java.io.IOException;

public interface CameraService {
    void importCameras(String path) throws IOException;
    void create(BasicCameraDto dto);
    void createMany(BasicCameraDto[] dtos);

}
