package com.test.enrollment.util;

import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.EnrolleeVO;

public class EnrollmentUtil {

	public static boolean validateEnrollee(EnrolleeVO enrollee) {
		boolean validate = true;

		if (enrollee.getDob() == null || enrollee.getName() == null) {
			validate = false;
		}

		return validate;
	}

	public static boolean validateDependent(DependentVO dependent) {
		boolean validate = true;

		if (dependent.getId() == 0 || dependent.getDob() == null || dependent.getName() == null) {
			validate = false;
		}

		return validate;
	}
}
