package com.fdm.pond.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fdm.pond.model.opportunity.Client;
import com.fdm.pond.model.opportunity.Opportunity;

@Repository
public interface OpportunityRepository extends PagingAndSortingRepository<Opportunity, Long> {
	List<Opportunity> findByPosition(String position);
	
	List<Client> findByClient(Client client);
}
