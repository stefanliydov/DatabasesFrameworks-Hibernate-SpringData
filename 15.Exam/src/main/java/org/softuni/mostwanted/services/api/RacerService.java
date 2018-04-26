package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.json.RacerJSONImportDTO;

public interface RacerService {
    void create(RacerJSONImportDTO dto);
}
