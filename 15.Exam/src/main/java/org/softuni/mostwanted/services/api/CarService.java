package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.json.CarJSONImportDTO;

public interface CarService {
    void create(CarJSONImportDTO dto);
}
