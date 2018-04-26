package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.xml.RacesXMLImportDTO;

public interface RaceService {

    void create(RacesXMLImportDTO dto);
}
