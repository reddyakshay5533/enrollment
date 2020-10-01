package com.test.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enrollment.exception.EnrollmentException;
import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.EnrolleeVO;
import com.test.enrollment.service.EnrolleeService;
import com.test.enrollment.util.EnrollmentUtil;

@RestController
@ControllerAdvice
public class EnrollmentController {

	@Autowired
	EnrolleeService enrolleeService;

	@PostMapping("/enrollee")
	public ResponseEntity<String> insertEnrollee(@RequestBody EnrolleeVO enrollee) {

		if (!EnrollmentUtil.validateEnrollee(enrollee)) {
			return new ResponseEntity<String>("Invalid Enrollee Request ", HttpStatus.BAD_REQUEST);
		}

		try {
			long id = enrolleeService.insertEnrollee(enrollee);
			return new ResponseEntity<String>("Enrollee Added, id:" + id, HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EnrollmentException();
		}

	}

	@PutMapping("/enrollee/{id}")
	public ResponseEntity<String> updateEnrollee(@PathVariable long id, @RequestBody EnrolleeVO enrollee) {

		enrollee.setId(id);
		try {
			enrolleeService.updateEnrollee(enrollee);
			return new ResponseEntity<String>("Enrollee Updated, id:" + id, HttpStatus.OK);
		} catch (EnrollmentException ex) {
			return new ResponseEntity<String>("Enrollee Not Found, id: " + id, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			throw new EnrollmentException();
		}

	}

	@DeleteMapping("/enrollee/{id}")
	public ResponseEntity<String> deleteEnrollee(@PathVariable long id) {

		try {
			enrolleeService.deleteEnrollee(id);

		} catch (Exception ex) {
			return new ResponseEntity<String>("Enrollee Not Found, id:" + id, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Enrollee Deleted, id: " + id, HttpStatus.OK);
	}

	@PostMapping("/enrollee/{id}/dependent")
	public ResponseEntity<String> insertEnrolleeDependent(@PathVariable long id, @RequestBody DependentVO dependent) {

		if (!EnrollmentUtil.validateDependent(dependent)) {
			return new ResponseEntity<String>("Invalid Enrollee Dependent Request ", HttpStatus.BAD_REQUEST);
		}

		try {
			enrolleeService.insertEnrolleeDependent(id, dependent);
			return new ResponseEntity<String>("Enrollee Added with dependent ", HttpStatus.CREATED);
		} catch (EnrollmentException ex) {
			return new ResponseEntity<String>("Enrollee Not Found, id: " + id, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			throw new EnrollmentException();
		}

	}

	@PutMapping("/enrollee/{id}/dependent/{did}")
	public ResponseEntity<String> updateEnrolleeDependent(@PathVariable long id, @PathVariable long did,
			@RequestBody DependentVO dependent) {

		dependent.setId(did);
		try {
			enrolleeService.updateEnrolleeDependent(id, dependent);
			return new ResponseEntity<String>("Enrollee Updated with Dependent", HttpStatus.OK);
		} catch (EnrollmentException ex) {
			return new ResponseEntity<String>(
					"Dependent Not Found with the combination enrollee id " + id + " and dependent Id:" + did,
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			throw new EnrollmentException();
		}

	}

	@DeleteMapping("/enrollee/{id}/dependent/{did}")
	public ResponseEntity<String> deleteEnrolleeDependent(@PathVariable long id, @PathVariable long did) {

		try {
			enrolleeService.deleteEnrolleeDependent(id, did);

		} catch (Exception ex) {
			return new ResponseEntity<String>(
					"Dependent Not Found with the combination enrollee id " + id + " and dependent Id:" + did,
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Enrollee Dependent Deleted ", HttpStatus.OK);
	}

	@ExceptionHandler({ Exception.class, EnrollmentException.class })
	public ResponseEntity<String> handleException(Exception ex) {
		ex.printStackTrace();

		return new ResponseEntity<String>("Exception Occured while processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
