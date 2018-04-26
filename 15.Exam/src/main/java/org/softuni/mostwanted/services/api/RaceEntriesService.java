package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.entities.dto.xml.RaceEntriesWrapperXMLImportDTO;
import org.softuni.mostwanted.entities.dto.xml.RaceEntriesXMLImportDTO;

public interface RaceEntriesService {

    void create(RaceEntriesXMLImportDTO dtoWrap);
}
