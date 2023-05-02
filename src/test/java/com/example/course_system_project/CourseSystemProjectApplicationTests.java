package com.example.course_system_project;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.course_system_project.entity.StudentInfo;
import com.example.course_system_project.repository.CourseDao;
import com.example.course_system_project.repository.StudentDao;
import com.example.course_system_project.service.ifs.CourseSystemService;
import com.example.course_system_project.vo.CourseSystemRequest;

@SpringBootTest(classes = CourseSystemProjectApplication.class)
class CourseSystemProjectApplicationTests {
//自定義的dao, service 方法
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;

	@Autowired
	CourseSystemService courseSystemService;
//	
//	@Test 
//	public void addCourseListInfo() {
//		List<String> codeList = new ArrayList<>();
//		codeList.add("ELE27005");
//		
//		CourseSystemRequest test = new CourseSystemRequest(,"國文(下)",2,15,17,2);
//		courseSystemService.addCourseListInfo(test);
//
//	}

	@Test
	public void checkStudentID() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27001");
		codeList.add("ELE27002");
		CourseSystemRequest test = new CourseSystemRequest("102002",codeList);
		courseSystemService.studentEnrollClass(test);

	}
	
	@Test
	public void check() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27006");
		CourseSystemRequest test = new CourseSystemRequest("102002",codeList);
		courseSystemService.studentEnrollClass(test);

	}
	

	
	

}
