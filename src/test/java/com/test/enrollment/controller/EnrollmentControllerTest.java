package com.test.enrollment.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.enrollment.exception.EnrollmentException;
import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.EnrolleeVO;
import com.test.enrollment.service.impl.EnrollmentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EnrollmentControllerTest {

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	public EnrolleeVO createEnrollee() {
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setActivationStatus(Boolean.TRUE);
		enrolleeVO.setDob("04-01-2020");
		enrolleeVO.setName("Sample");
		return enrolleeVO;
	}

	public DependentVO createDependent() {
		DependentVO dependent = new DependentVO();
		dependent.setId(1L);
		dependent.setName("Test Dependent");
		dependent.setDob("01-02-2015");
		return dependent;
	}

	@InjectMocks
	EnrollmentController controller;

	@Mock
	EnrollmentServiceImpl service;

	@Test
	public void testInsertEnrollee() throws Exception {
		EnrolleeVO enrolleeVO = createEnrollee();

		when(service.insertEnrollee(enrolleeVO)).thenReturn(1L);

		ResponseEntity<String> response = controller.insertEnrollee(enrolleeVO);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	public void testInsertEnrollee_Invalid_Request() throws Exception {
		EnrolleeVO enrolleeVO = createEnrollee();

		enrolleeVO.setDob(null);

		when(service.insertEnrollee(enrolleeVO)).thenReturn(1L);

		ResponseEntity<String> response = controller.insertEnrollee(enrolleeVO);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void testUpdateEnrollee() throws Exception {
		EnrolleeVO enrolleeVO = createEnrollee();

		doNothing().when(service).updateEnrollee(enrolleeVO);

		ResponseEntity<String> response = controller.updateEnrollee(1L, enrolleeVO);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testUpdateEnrollee_Id_Not_Exists() throws Exception {
		EnrolleeVO enrolleeVO = createEnrollee();

		doThrow(new EnrollmentException()).when(service).updateEnrollee(enrolleeVO);

		ResponseEntity<String> response = controller.updateEnrollee(1L, enrolleeVO);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void testDeleteEnrollee() {
		doNothing().when(service).deleteEnrollee(1L);

		ResponseEntity<String> response = controller.deleteEnrollee(1L);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testDeleteEnrollee_Id_Not_Exists() {
		doThrow(new EnrollmentException()).when(service).deleteEnrollee(1L);

		ResponseEntity<String> response = controller.deleteEnrollee(1L);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testInsertEnrolleeDependent() throws Exception {
		DependentVO dependent = createDependent();

		doNothing().when(service).insertEnrolleeDependent(Matchers.anyLong(), Matchers.any());

		ResponseEntity<String> response = controller.insertEnrolleeDependent(1L, dependent);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testInsertEnrolleeDependent_invalid_data() throws Exception {
		DependentVO dependent = createDependent();

		dependent.setName(null);

		doNothing().when(service).insertEnrolleeDependent(Matchers.anyLong(), Matchers.any());

		ResponseEntity<String> response = controller.insertEnrolleeDependent(1L, dependent);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testInsertEnrolleeDependent_id_not_exists() throws Exception {
		DependentVO dependent = createDependent();

		doThrow(new EnrollmentException()).when(service).insertEnrolleeDependent(Matchers.anyLong(), Matchers.any());

		ResponseEntity<String> response = controller.insertEnrolleeDependent(1L, dependent);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testUpdateEnrolleeDependent() throws Exception {
		DependentVO dependent = createDependent();

		doNothing().when(service).updateEnrolleeDependent(Matchers.anyLong(), Matchers.any());

		ResponseEntity<String> response = controller.updateEnrolleeDependent(1L, 1L, dependent);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateEnrolleeDependent_id_not_exists() throws Exception {
		DependentVO dependent = createDependent();

		doThrow(new EnrollmentException()).when(service).updateEnrolleeDependent(Matchers.anyLong(), Matchers.any());

		ResponseEntity<String> response = controller.updateEnrolleeDependent(1L, 1L, dependent);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testDeleteEnrolleeDependent() {
		doNothing().when(service).deleteEnrolleeDependent(Matchers.anyLong(), Matchers.anyLong());

		ResponseEntity<String> response = controller.deleteEnrolleeDependent(1L, 1L);

		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testDeleteEnrolleeDependent_id_not_exists() {
		doThrow(new EnrollmentException()).when(service).deleteEnrolleeDependent(Matchers.anyLong(),
				Matchers.anyLong());

		ResponseEntity<String> response = controller.deleteEnrolleeDependent(1L, 1L);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	

}
