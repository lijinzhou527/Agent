package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AgentController {

    //GET
    @GetMapping(value = "test")
    public String run(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return id.toString();
    }

    //class
    @PostMapping(value = "post")
    public Agent run(@RequestBody Agent agent) {

        return agent;
    }

    //list
    @PostMapping(value = "list")
    public List<Agent> run(@Valid @RequestBody List<Agent> agents) {

        return agents;
    }
}
