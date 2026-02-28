package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IntermediateService;
import com.example.dao.IUserDao;
import com.example.model.Company;
import com.example.model.Graduation;
import com.example.model.User;
import com.example.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IntermediateService intermediateService;

	private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User getUserById(Integer id) {
		LOGGER.info("Started getUserById method");
		User user = userDao.getUserById(id);

		if (null != user) {
			user.setGraduationData(getGraduationData(id));

			if (null != user.getGraduationData()) {
				user.setCompanyData(getCompanyData(id));
			}
		}

		if (null != user) {
			LOGGER.info(user.toString());
		}

		LOGGER.info("Ended getUserById method");
		return user;
	}

	private Graduation getGraduationData(Integer id) {
		// calling graduation application to get user graduation data
		return intermediateService.getGraduationData(id);
	}

	private Company getCompanyData(Integer id) {
		// calling company application to get user company data
		return intermediateService.getCompanyData(id);
	}

}
