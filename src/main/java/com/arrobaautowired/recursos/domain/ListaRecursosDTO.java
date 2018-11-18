package com.arrobaautowired.recursos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class ListaRecursosDTO {

    private String uuidCarpeta;

    private List<CriteriaRecursoDTO> recursos;

    private String readingPath;

    private MultipartFile[] pendingFiles;

}
