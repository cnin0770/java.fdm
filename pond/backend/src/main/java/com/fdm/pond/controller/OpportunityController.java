package com.fdm.pond.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdm.pond.repository.OpportunityRepository;
import com.fdm.pond.model.opportunity.Opportunity;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/opportunity")
@CrossOrigin(origins = "http://localhost:3000")
public class OpportunityController {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityController(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    // usage: "GET /api/opportunity?id=1" or "GET /api/opportunity" to get all
    @GetMapping
    @RolesAllowed("ROLE_EMPLOYEE")
    public List<Opportunity> getOpportunity(@RequestParam(value = "id", required = false) Long id) {
        Iterable<Opportunity> opportunities = (id == null) ? opportunityRepository.findAll() :
                opportunityRepository.findAllById(Collections.singletonList(id));

        List<Opportunity> result = new ArrayList<>();
        for (Opportunity opportunity: opportunities) {
            if (opportunity.getVisible()) result.add(opportunity);
        }

        return result;
    }

    // usage: "DELETE /api/opportunity?id=1"
    @DeleteMapping
    @RolesAllowed("ROLE_ADMIN")
    public void deleteOpportunityById(@RequestParam(value = "id") Long id) {
        Opportunity opportunity = opportunityRepository.findById(id).orElse(null);
        if (opportunity == null) return;
        log.info("[Entity] Opportunity {} to be removed.", id);
        opportunity.setVisible(false);

        opportunityRepository.save(opportunity);
    }
}
