package com.example.course_system_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course_system_project.entity.StudentInfo;

public interface StudentDao extends JpaRepository<StudentInfo, String>{
	
	public StudentInfo findByCourseID (String courseID);

}
