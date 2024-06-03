package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.service.DeputadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final DeputadoService deputadoService;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(DeputadoService deputadoService) {
        this.deputadoService = deputadoService;
    }

    @GetMapping("/")
    String index(Principal principal, Model model) {
        List<Deputado> deputadosVerde = deputadoService.findByClassificacao("verde");
        List<Deputado> deputadosAmarelo = deputadoService.findByClassificacao("amarelo");
        List<Deputado> deputadosVermelho = deputadoService.findByClassificacao("vermelho");

        System.out.println("*** Deputados Verde: " + deputadosVerde);
        System.out.println("*** Deputados Amarelo: " + deputadosAmarelo.size());
        System.out.println("*** Deputados Vermelho: " + deputadosVermelho.stream().findFirst());

        model.addAttribute("deputadosVerde", deputadosVerde);
        model.addAttribute("deputadosAmarelo", deputadosAmarelo);
        model.addAttribute("deputadosVermelho", deputadosVermelho);

        logger.debug("Principal em root /: {}", principal);
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }
//    String index(Model model) {
////    String index(Principal principal, Model model) {
//        List<Class<?>> deputado = List.of(
//                Deputado.class);
//        model.addAttribute("deputado", deputado);
//        List<String> endpoints = deputado.stream()
//                .map(Class::getSimpleName)
//                .toList();
//        model.addAttribute("deputado", deputado);
//
//        return "home/homeNotSignedIn";
//    }

//    @GetMapping("/logout")
//    String logout() {
//        return "/home/homeNotSignedIn";
//    }

}