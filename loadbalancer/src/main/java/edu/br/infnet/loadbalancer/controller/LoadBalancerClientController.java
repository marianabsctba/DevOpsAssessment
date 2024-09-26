package edu.br.infnet.loadbalancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class LoadBalancerClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/status")
    public String statusService() {
        return restTemplate.getForObject("http://Q01/status", String.class);
    }
}
