package com.example.course_system_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.course_system_project.service.ifs.CourseSystemService;
import com.example.course_system_project.vo.CourseSystemRequest;
import com.example.course_system_project.vo.CourseSystemResponse;


@RestController
public class CourseSystemController {
	@Autowired CourseSystemService courseSystemService;
	
	@PostMapping(value = "add_CourseList_Info")
	public CourseSystemResponse addCourseListInfo(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.addCourseListInfo(courseSystemRequest);
		
	}
	
	@PostMapping(value = "add_StudentList_Info")
	public CourseSystemResponse addStudentListInfo(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.addStudentListInfo(courseSystemRequest);
		
	}

	
	@PostMapping(value = "student_EnrollClass")
	public CourseSystemResponse studentEnrollClass(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.studentEnrollClass(courseSystemRequest);
		
	}
	
	@PostMapping(value = "findByCourseID")
	public CourseSystemResponse findByCourseID(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.findByCourseID(courseSystemRequest);
		
	}
	
	@PostMapping(value = "findByCourseName")
	public CourseSystemResponse findByCourseName(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.findByCourseName(courseSystemRequest);
		
	}
	
	@PostMapping(value = "studentEnrollInfoCheck")
	public CourseSystemResponse studentEnrollInfoCheck(@RequestBody CourseSystemRequest courseSystemRequest) {
		return courseSystemService.studentEnrollInfoCheck(courseSystemRequest);
		
	}
}
