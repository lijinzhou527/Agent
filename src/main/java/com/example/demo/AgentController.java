package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AgentController {

    private String name;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String string) {
        date = string;
    }

    public String getName() {
        return name;
    }

    public void setName(String string) {
        name = string;
    }

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
