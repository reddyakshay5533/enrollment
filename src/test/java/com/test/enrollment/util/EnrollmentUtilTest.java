package com.test.enrollment.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.EnrolleeVO;

public class EnrollmentUtilTest {

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

	@Test
	public void testValidateEnrollee() {
		EnrolleeVO enrolleeVO = createEnrollee();
		boolean validate = EnrollmentUtil.validateEnrollee(enrolleeVO);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.TRUE });

	}

	@Test
	public void testValidateEnrollee_noDod() {
		EnrolleeVO enrolleeVO = createEnrollee();
		enrolleeVO.setDob(null);
		boolean validate = EnrollmentUtil.validateEnrollee(enrolleeVO);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.FALSE });

	}

	@Test
	public void testValidateEnrollee_noName() {
		EnrolleeVO enrolleeVO = createEnrollee();
		enrolleeVO.setName(null);
		boolean validate = EnrollmentUtil.validateEnrollee(enrolleeVO);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.FALSE });

	}

	@Test
	public void testValidateDependent() {
		DependentVO dependent = createDependent();
		boolean validate = EnrollmentUtil.validateDependent(dependent);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.TRUE });

	}

	@Test
	public void testValidateDependent_noId() {
		DependentVO dependent = createDependent();
		dependent.setId(0);
		boolean validate = EnrollmentUtil.validateDependent(dependent);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.FALSE });

	}

	@Test
	public void testValidateDependent_noName() {
		DependentVO dependent = createDependent();
		dependent.setName(null);
		boolean validate = EnrollmentUtil.validateDependent(dependent);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.FALSE });

	}

	@Test
	public void testValidateDependent_noDob() {
		DependentVO dependent = createDependent();
		dependent.setDob(null);
		boolean validate = EnrollmentUtil.validateDependent(dependent);
		assertArrayEquals(new Boolean[] { validate }, new Boolean[] { Boolean.FALSE });

	}

}
