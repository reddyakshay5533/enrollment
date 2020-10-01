package com.test.enrollment.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.test.enrollment.model.Dependent;
import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.Enrollee;
import com.test.enrollment.model.EnrolleeVO;
import com.test.enrollment.repository.DependentRepository;
import com.test.enrollment.repository.EnrolleeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnrollmentServiceImplTest {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
	EnrollmentServiceImpl enrollmentServiceImpl;

	@Mock
	EnrolleeRepository enrolleeRepo;

	@Mock
	DependentRepository dependentRepo;

	@Test
	public void testInsertEnrollee() throws Exception {

		EnrolleeVO enrolleeVO = Mockito.mock(EnrolleeVO.class);

		when(enrolleeVO.getDob()).thenReturn("02-01-2020");

		Enrollee enrollee = Mockito.mock(Enrollee.class);

		when(enrollee.getEnrolleeId()).thenReturn(1L);

		when(enrolleeRepo.save(Mockito.any())).thenReturn(enrollee);

		long id = enrollmentServiceImpl.insertEnrollee(enrolleeVO);

		assertEquals(id, 1L);

	}

	@Test
	public void testUpdateEnrollee() throws Exception {
		EnrolleeVO enrolleeVO = Mockito.mock(EnrolleeVO.class);

		Enrollee enrolleedb = Mockito.mock(Enrollee.class);

		when(enrolleeRepo.findById(Matchers.anyLong())).thenReturn(Optional.of(enrolleedb));

		when(enrolleeVO.getDob()).thenReturn("02-01-2020");

		when(enrolleeRepo.save(Mockito.any())).thenReturn(enrolleedb);

		enrollmentServiceImpl.updateEnrollee(enrolleeVO);

	}

	@Test
	public void testDeleteEnrollee() {
		doNothing().when(enrolleeRepo).deleteById(Matchers.anyLong());

		enrollmentServiceImpl.deleteEnrollee(1L);
	}

	@Test
	public void testInsertEnrolleeDependent() throws Exception {
		DependentVO dependentVO = Mockito.mock(DependentVO.class);

		when(dependentVO.getDob()).thenReturn("02-01-2020");

		Enrollee enrolleedb = Mockito.mock(Enrollee.class);

		when(enrolleeRepo.findById(Matchers.anyLong())).thenReturn(Optional.of(enrolleedb));

		enrollmentServiceImpl.insertEnrolleeDependent(1L, dependentVO);

	}

	@Test
	public void testUpdateEnrolleeDependent() throws Exception {
		Dependent dependent = Mockito.mock(Dependent.class);

		when(dependentRepo.findDependentEnrollee(Matchers.anyLong(), Matchers.anyLong())).thenReturn(dependent);

		enrollmentServiceImpl.updateEnrolleeDependent(1L, new DependentVO());
	}

	@Test
	public void testDeleteEnrolleeDependent() {
		Dependent dependent = Mockito.mock(Dependent.class);

		when(dependentRepo.findDependentEnrollee(Matchers.anyLong(), Matchers.anyLong())).thenReturn(dependent);

		enrollmentServiceImpl.deleteEnrolleeDependent(1L, 1L);
	}

}
