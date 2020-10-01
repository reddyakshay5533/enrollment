package com.test.enrollment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.test.enrollment.model.Enrollee;

@Repository
public interface EnrolleeRepository extends PagingAndSortingRepository<Enrollee, Long>{

}
