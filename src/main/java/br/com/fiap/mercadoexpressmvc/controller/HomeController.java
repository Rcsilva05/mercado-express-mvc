package br.com.fiap.mercadoexpressmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Rotas de pagina inicial e login (ambas publicas).
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/produtos";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
