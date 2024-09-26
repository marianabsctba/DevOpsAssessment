package edu.br.infnet.q01.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private Environment environment;

    @GetMapping
    public ResponseEntity<String> getStatus() {
        String port = environment.getProperty("local.server.port");
       return ResponseEntity.ok("Service is up on port: " + port);
    }
}
