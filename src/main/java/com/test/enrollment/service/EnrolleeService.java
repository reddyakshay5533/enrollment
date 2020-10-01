package com.test.enrollment.service;

import java.text.ParseException;

import com.test.enrollment.exception.EnrollmentException;
import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.EnrolleeVO;

public interface EnrolleeService {

	long insertEnrollee(EnrolleeVO enrollee) throws Exception;

	void updateEnrollee(EnrolleeVO enrollee) throws EnrollmentException, ParseException;

	void deleteEnrollee(long id);

	void insertEnrolleeDependent(long id, DependentVO dependent) throws EnrollmentException, ParseException;

	void updateEnrolleeDependent(long id, DependentVO dependent) throws EnrollmentException, ParseException;

	void deleteEnrolleeDependent(long id, long did);

}
