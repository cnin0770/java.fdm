package com.fdm.pond.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fdm.pond.model.employee.Employee;
import com.fdm.pond.model.employee.Role;
import com.fdm.pond.repository.EmployeeRepository;

@Service
public class EmployeeDetailsService implements UserDetailsService {

		private final EmployeeRepository employeeRepository;

		@Autowired
		public EmployeeDetailsService(EmployeeRepository repository) {
			this.employeeRepository = repository;
		}

		
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			Employee e = employeeRepository.findByEmail(email);
			if(e==null)
				throw new UsernameNotFoundException("email not found: " + email);
			else
			return new EmployeeDetails(e);
	
		}

	}

