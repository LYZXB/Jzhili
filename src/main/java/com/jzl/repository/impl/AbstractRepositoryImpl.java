package com.jzl.repository.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractRepositoryImpl {
	@Resource
	JdbcTemplate jdbcTemplate;
	
}
