package es.cic.curso2025.proy009.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso2025.proy009.service.DirectorService;

@RestController
@RequestMapping("/Director")
public class DirectorController {

    private DirectorService directorService;

    
}
