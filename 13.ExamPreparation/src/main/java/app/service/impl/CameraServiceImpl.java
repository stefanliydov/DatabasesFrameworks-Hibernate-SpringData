package app.service.impl;

import app.model.entities.BasicCamera;
import app.model.entities.dto.BasicCameraDto;
import app.parser.impl.JSONSerializer;
import app.repositories.CameraRepository;
import app.service.CameraService;
import com.google.gson.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CameraServiceImpl implements CameraService {

    private CameraRepository cameraRepository;
    private JSONSerializer jsonSerializer;
    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
        this.jsonSerializer = new JSONSerializer();
    }

    @Override
    public void importCameras(String path) throws IOException {
        BasicCameraDto[] deserialized = this.jsonSerializer.deserialize(BasicCameraDto[].class,path);
        this.createMany(deserialized);
    }

    @Override
    public void create(BasicCameraDto dto) {

    }

    @Override
    public void createMany(BasicCameraDto[] dtos) {
        for (BasicCameraDto dto : dtos) {
            if(dto.getType().equals("DSLR")){
                
            }
        }
    }


}
