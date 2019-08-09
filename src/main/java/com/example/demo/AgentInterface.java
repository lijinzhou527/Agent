package com.example.demo;


import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AgentInterface {
    List<Agent> run(@Valid @RequestBody List<Agent> agents);

    void run() throws Exception;
}
