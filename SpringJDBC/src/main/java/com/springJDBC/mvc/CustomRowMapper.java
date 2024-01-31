package com.springJDBC.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springJDBC.mvc.model.Student;

public class CustomRowMapper implements RowMapper<Student> {
	   @Override
	   public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
	       return new Student(
	    		   rs.getLong("id"),
                   rs.getString("name"),
                   rs.getString("roll_no"),
                   rs.getString("email_id")
	       );
	   }
	}