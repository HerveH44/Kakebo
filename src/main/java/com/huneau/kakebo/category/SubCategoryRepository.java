package com.huneau.kakebo.category;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends PagingAndSortingRepository<SubCategory, Long>, JpaSpecificationExecutor<SubCategory> {
}
