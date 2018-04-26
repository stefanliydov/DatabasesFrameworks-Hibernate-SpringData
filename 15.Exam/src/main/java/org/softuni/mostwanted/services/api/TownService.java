package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.json.TownJSONImportDTO;

public interface TownService {
    void create(TownJSONImportDTO dto);
}
