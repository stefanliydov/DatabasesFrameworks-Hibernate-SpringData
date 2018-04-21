package app.retake.services.api;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;

import java.text.ParseException;
import java.util.List;

public interface AnimalService {
    void create(AnimalJSONImportDTO dto) throws ParseException;
    List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber);

    Animal getByName(String name);

    Animal getBySerialNumber(String number);
}
