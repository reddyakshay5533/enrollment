package com.test.enrollment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enrollment.exception.EnrollmentException;
import com.test.enrollment.model.Dependent;
import com.test.enrollment.model.DependentVO;
import com.test.enrollment.model.Enrollee;
import com.test.enrollment.model.EnrolleeVO;
import com.test.enrollment.repository.DependentRepository;
import com.test.enrollment.repository.EnrolleeRepository;
import com.test.enrollment.service.EnrolleeService;

@Service("enrolleeService")
public class EnrollmentServiceImpl implements EnrolleeService {

	public static final SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");

	@Autowired
	EnrolleeRepository enrolleeRepo;

	@Autowired
	DependentRepository dependentRepo;

	@Override
	public long insertEnrollee(EnrolleeVO enrollee) throws Exception {

		Enrollee enrolleeDTO = new Enrollee();
		enrolleeDTO.setActivationStatus(enrollee.getActivationStatus());
		enrolleeDTO.setName(enrollee.getName());
		enrolleeDTO.setPhonenum(enrollee.getPhonenum());
		enrolleeDTO.setDob(formatter.parse(enrollee.getDob()));
		if (enrollee.getDependents() != null) {
			Set<Dependent> dependents = new HashSet<>();
			for (DependentVO dependentVO : enrollee.getDependents()) {
				Dependent dependent = new Dependent();
				dependent.setDid(dependentVO.getId());
				dependent.setName(dependentVO.getName());
				dependent.setEnrollee(enrolleeDTO);
				dependent.setDob(formatter.parse(dependentVO.getDob()));
				dependents.add(dependent);

			}
			enrolleeDTO.setDependents(dependents);
		}
		enrolleeDTO = enrolleeRepo.save(enrolleeDTO);
		return enrolleeDTO.getEnrolleeId();
	}

	@Override
	public void updateEnrollee(EnrolleeVO enrolleeVO) throws EnrollmentException, ParseException {

		Optional<Enrollee> enrolleeDBO = enrolleeRepo.findById(enrolleeVO.getId());

		if (!enrolleeDBO.isPresent()) {
			throw new EnrollmentException("enrollee not found");
		}

		Enrollee enrolleeDTO = enrolleeDBO.get();
		if (enrolleeVO.getActivationStatus() != null) {
			enrolleeDTO.setActivationStatus(enrolleeVO.getActivationStatus());
		}
		if (enrolleeVO.getName() != null) {
			enrolleeDTO.setName(enrolleeVO.getName());
		}
		if (enrolleeVO.getPhonenum() != null) {
			enrolleeDTO.setPhonenum(enrolleeVO.getPhonenum());
		}
		if (enrolleeVO.getDob() != null) {
			enrolleeDTO.setDob(formatter.parse(enrolleeVO.getDob()));
		}
		if (enrolleeVO.getDependents() != null) {
			Set<Dependent> dependents = enrolleeDTO.getDependents();
			for (DependentVO dependentVO : enrolleeVO.getDependents()) {
				Dependent dependent = new Dependent();
				dependent.setDid(dependentVO.getId());
				dependent.setName(dependent.getName());
				dependent.setEnrollee(enrolleeDTO);
				dependent.setDob(formatter.parse(dependentVO.getDob()));
				dependents.add(dependent);

			}
			enrolleeDTO.setDependents(dependents);
		}
		enrolleeDTO.setEnrolleeId(enrolleeVO.getId());
		enrolleeDTO = enrolleeRepo.save(enrolleeDTO);
	}

	@Override
	public void deleteEnrollee(long id) {

		enrolleeRepo.deleteById(id);

	}

	@Override
	public void insertEnrolleeDependent(long id, DependentVO dependentVO) throws EnrollmentException, ParseException {

		Optional<Enrollee> enrolleeDBO = enrolleeRepo.findById(id);

		if (!enrolleeDBO.isPresent()) {
			throw new EnrollmentException("enrollee not found");
		}

		Dependent dependent = new Dependent();
		dependent.setDid(dependentVO.getId());
		dependent.setName(dependentVO.getName());
		dependent.setEnrollee(enrolleeDBO.get());
		dependent.setDob(formatter.parse(dependentVO.getDob()));

		dependentRepo.save(dependent);

	}

	@Override
	public void updateEnrolleeDependent(long id, DependentVO dependentVO) throws EnrollmentException, ParseException {
		Dependent dependent = dependentRepo.findDependentEnrollee(id, dependentVO.getId());
		if (dependent == null) {
			throw new EnrollmentException("Dependent Not Found");
		}
		if (dependentVO.getName() != null) {
			dependent.setName(dependentVO.getName());
		}
		if (dependentVO.getDob() != null) {
			dependent.setDob(formatter.parse(dependentVO.getDob()));
		}
		dependentRepo.save(dependent);

	}

	@Override
	public void deleteEnrolleeDependent(long id, long did) {
		Dependent dependent = dependentRepo.findDependentEnrollee(id, did);
		if (dependent == null) {
			throw new EnrollmentException("Dependent Not Found");
		}
		dependentRepo.delete(dependent);

	}

}
