package com.fdm.pond.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fdm.pond.model.opportunity.Client;
import com.fdm.pond.model.opportunity.Placement;

public interface PlacementRepository extends PagingAndSortingRepository<Placement, Long>  {
	List<Placement> findByPosition(String position);
	
	List<Client> findByClient(Client client);
}
