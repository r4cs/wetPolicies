package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
//import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.service.BancadaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final BancadaService bancadaService;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(BancadaService bancadaService) {
        this.bancadaService = bancadaService;
    }

    @GetMapping("/")
    String index(Principal principal, Model model) {
        List<Bancada> bancadasVerde = bancadaService.findByClassificacao("verde");
        List<Bancada> bancadasAmarela = bancadaService.findByClassificacao("amarelo");
        List<Bancada> bancadasVermelha = bancadaService.findByClassificacao("vermelho");

        System.out.println("*** Bancadas Verde: " + bancadasVerde);
        System.out.println("*** Bancadas Amarelo: " + bancadasAmarela.size());
        System.out.println("*** Bancadas Vermelho: " + bancadasVermelha.stream().findFirst());

        model.addAttribute("bancadasVerde", bancadasVerde);
        model.addAttribute("bancadasAmarela", bancadasAmarela);
        model.addAttribute("bancadasVermelha", bancadasVermelha);

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