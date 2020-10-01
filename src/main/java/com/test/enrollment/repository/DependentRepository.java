package com.test.enrollment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.enrollment.model.Dependent;

@Repository
public interface DependentRepository extends PagingAndSortingRepository<Dependent, Long> {

	@Query("select a from Dependent a where a.enrollee.enrolleeId = :enrolleeId and a.did= :did")
	public Dependent findDependentEnrollee(@Param("enrolleeId") long enrolleeId, @Param("did") long did);

}