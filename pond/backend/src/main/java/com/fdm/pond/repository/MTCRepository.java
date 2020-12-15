package com.fdm.pond.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fdm.pond.model.opportunity.ManagersToClients;

public interface MTCRepository extends PagingAndSortingRepository<ManagersToClients, Long>{

}
