package com.arrobaautowired.recursos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class CriteriaRecursoDTO {

    private String nombreFichero;

    private Long sizeInBytes;

    private LocalDateTime fechaCreacion;

    private String uuidFichero;

    private String usuarioSubida;

    private LinkedHashMap<LocalDateTime,String> history;

}
