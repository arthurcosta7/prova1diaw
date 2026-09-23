package com.example.CandidatosTSE.controller;

import java.util.List;

import com.example.CandidatosTSE.model.Candidato;
import com.example.CandidatosTSE.service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

@Controller
public class CandidatosTseController {
    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false, defaultValue = "") String cargo,
            @RequestParam(required = false, defaultValue = "") String partido,
            @RequestParam(required = false, defaultValue = "") String texto,
            Model model
    ) {
        List<Candidato> candidatos = candidatosTseService.filtrar(cargo, partido, texto);
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());
        model.addAttribute("cargoSelecionado", cargo);
        model.addAttribute("partidoSelecionado", partido);
        model.addAttribute("textoSelecionado", texto);
        return "index";
    }

}