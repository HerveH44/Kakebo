package com.huneau.kakebo.outcome;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends PagingAndSortingRepository<Outcome, Long>, JpaSpecificationExecutor<Outcome> {

}
