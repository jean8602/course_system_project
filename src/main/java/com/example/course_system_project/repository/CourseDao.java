package com.example.course_system_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course_system_project.entity.CourseInfo;

public interface CourseDao extends JpaRepository<CourseInfo, String>  {
	
	
	public List<CourseInfo> findAllByCourseName(String name);

}
