package com.fdm.pond.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.fdm.pond.model.employee.*;
import com.fdm.pond.repository.EmployeeRepository;
import com.fdm.pond.security.JwtResponse;
import com.fdm.pond.validation.Validation;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api")
@Log4j2
public class RegisterEmployeeController {
	
    @Autowired
    EmployeeRepository employeeRepository;
    
    private final PasswordEncoder passwordEncoder;
    @Autowired
	public RegisterEmployeeController(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
    
	@PutMapping("/register/Consultant")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> registerConsultant(@RequestBody Consultant consultant) {
		log.trace("Admin attempted to register a consultant");
		if (consultant == null) {
    		//logger.warn("Failed registration");
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
            
        }
		else if(!Validation.IsValidEmployee(consultant))
        	return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);

        // DUPLICATES
        else if (employeeRepository.findByEmail(consultant.getEmail())!=null) {
    		//logger.warn("Failed registration as username is already in use");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
        	if(consultant.getPassword()==null)
        		consultant.setPassword(passwordEncoder.encode(consultant.getFirstName()));
            Consultant _consultant = new Consultant(consultant.getEmail(), consultant.getPassword(), consultant.getFirstName(),consultant.getLastName());
            log.info("New employee was made for user "+ _consultant.getEmail());
            _consultant = employeeRepository.save(_consultant);
            return ResponseEntity.ok(_consultant);
        }

    }
	
	@PutMapping("/register/Manager")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerManager(@RequestBody Manager manager) {
		log.trace("Admin attempted to register a manager");
		if (manager == null) {
    		//logger.warn("Failed registration");
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
		else if(!Validation.IsValidEmployee(manager))
        	return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);


        // DUPLICATES
        else if (employeeRepository.findByEmail(manager.getEmail())!=null) {
    		//logger.warn("Failed registration as username is already in use");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
        	if(manager.getPassword()==null)
        		manager.setPassword(passwordEncoder.encode(manager.getFirstName()));
        	Manager _manager = new Manager(manager.getEmail(), manager.getPassword(), manager.getFirstName(),manager.getLastName());
            log.info("New employee was made for user "+ _manager.getEmail());
            _manager=  employeeRepository.save(_manager);
            return ResponseEntity.ok(_manager);
        }

    }

	@PutMapping("/register/Admin")
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
		log.warn("Admin attempted to register an admin");
		if (admin == null) {
    		log.warn("Failed registration");
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else if(!Validation.IsValidEmployee(admin)) {
        	log.warn("Admin attempted to register invalid admin");
        	return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }

        // DUPLICATES
        else if (employeeRepository.findByEmail(admin.getEmail())!=null) {
    		log.warn("Failed registration: email is already in use");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
        	if(admin.getPassword()==null)
        		admin.setPassword(passwordEncoder.encode(admin.getFirstName()));
            Admin _admin = new Admin(admin.getEmail(), admin.getPassword(), admin.getFirstName(),admin.getLastName());
            log.info("New admin was made for user "+ _admin.getEmail());
            _admin = employeeRepository.save(_admin);
            return ResponseEntity.ok(_admin);
        }

    }


}
