package com.fdm.pond.controller;

import com.fdm.pond.model.employee.Consultant;

import com.fdm.pond.repository.ConsultantRepository;

import lombok.extern.log4j.Log4j2;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/consultant")
@CrossOrigin(origins = "http://localhost:3000")
public class ConsultantController {

    private final ConsultantRepository consultantRepository;

    @Autowired
    public ConsultantController(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    // usage: "GET /api/consultant?id=1" or "GET /api/consultant" to get all
    @GetMapping
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_ADMIN", "ROLE_MANAGER"})
    public List<Consultant> getConsultant(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) return Collections.singletonList(consultantRepository.findById(id).orElse(null));

        Iterable<Consultant> consultants = consultantRepository.findAll();
        List<Consultant> result = new ArrayList<>();
        consultants.forEach(result::add);
        return result;
    }
}
