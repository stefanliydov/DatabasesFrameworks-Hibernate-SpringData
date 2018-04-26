package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.json.DistrictJSONImportDTO;

public interface DistrictService {
    void create(DistrictJSONImportDTO dto);
}
