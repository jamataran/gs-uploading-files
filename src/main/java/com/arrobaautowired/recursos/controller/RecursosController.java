package com.arrobaautowired.recursos.controller;

import com.arrobaautowired.files.StorageService;
import com.arrobaautowired.recursos.domain.ListaRecursosDTO;
import com.arrobaautowired.recursos.service.RecursosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

@Controller
@RequestMapping("recursos")
@Slf4j
public class RecursosController {

    private final RecursosService recursosService;

    private final StorageService storageService;

    public RecursosController(RecursosService recursosService, StorageService storageService) {
        this.recursosService = recursosService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String getLista(Model model) {
        model.addAttribute("DTO", recursosService.getListaRecursos());
        return "lista";
    }

    @GetMapping("/detalle/{id}")
    public String getDetalleFichero(@PathVariable(name = "id") String uuidFile) {
        return "detalle";
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute(name = "DTO") ListaRecursosDTO listaRecursosDTO, Model model) {
        if (listaRecursosDTO.getPendingFiles() != null)
            Stream
                    .of(listaRecursosDTO.getPendingFiles())
                    .parallel()
                    .forEach(multipartFile -> {
                        storageService.store(multipartFile);
                        log.debug("FileProcessed: {}", multipartFile.getOriginalFilename());
                    });

        model.addAttribute("DTO", recursosService.getListaRecursos());
        return "redirect:/recursos";
    }

}
