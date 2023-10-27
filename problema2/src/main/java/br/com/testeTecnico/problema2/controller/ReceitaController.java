package br.com.testeTecnico.problema2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReceitaController {

    @GetMapping("/buscar-receitas")
    public String buscarReceitas(@RequestParam String prato) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://forkify-api.herokuapp.com/api/search?q=" + prato;
        return restTemplate.getForObject(apiUrl, String.class);

    }

}
