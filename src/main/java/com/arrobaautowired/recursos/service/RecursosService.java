package com.arrobaautowired.recursos.service;

import com.arrobaautowired.files.StorageService;
import com.arrobaautowired.recursos.domain.CriteriaRecursoDTO;
import com.arrobaautowired.recursos.domain.ListaRecursosDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecursosService {

    private final static String CARPETA = "CarpetaNombre";

    private final StorageService storageService;
    @Value("${root.location}")
    private String propertiesPath;

    public RecursosService(StorageService storageService) {
        this.storageService = storageService;
    }


    public ListaRecursosDTO getListaRecursos() {
        return ListaRecursosDTO.builder()
                .readingPath(propertiesPath)
                .uuidCarpeta(UUID.randomUUID().toString().replace("-","#@#"))
                .recursos(storageService
                        .loadAll()
                        .parallel()
                        .map(
                                path -> {
                                    log.debug("Building file info from {}", path);
                                    CriteriaRecursoDTO obtenido = CriteriaRecursoDTO
                                            .builder()
                                            .fechaCreacion(Instant.ofEpochMilli(path.toFile().lastModified()).atZone(ZoneId.systemDefault()).toLocalDateTime())
                                            .sizeInBytes(null)
                                            .nombreFichero(path.getFileName().toString())
                                            .uuidFichero(UUID.randomUUID().toString())
                                            .build();
                                    log.debug("Processed file: {}", obtenido);
                                    return obtenido;
                                }).collect(Collectors.toList()))
                .build();
    }

}
