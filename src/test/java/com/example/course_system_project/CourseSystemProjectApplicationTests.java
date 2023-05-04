package com.example.course_system_project;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.course_system_project.constants.RtnResponse;
import com.example.course_system_project.entity.CourseInfo;
import com.example.course_system_project.entity.StudentInfo;
import com.example.course_system_project.repository.CourseDao;
import com.example.course_system_project.repository.StudentDao;
import com.example.course_system_project.service.ifs.CourseSystemService;
import com.example.course_system_project.vo.CourseSystemRequest;
import com.example.course_system_project.vo.CourseSystemResponse;

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
		CourseSystemRequest test = new CourseSystemRequest("102002", codeList);
		courseSystemService.studentEnrollClass(test);

	}

	@Test
	public void check() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27006");
		CourseSystemRequest test = new CourseSystemRequest("102002", codeList);
		courseSystemService.studentEnrollClass(test);

	}

	@Test
	public void checkdrop() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27007");
		CourseSystemRequest test = new CourseSystemRequest("102002", codeList);
		courseSystemService.studentDropClass(test);

	}

	@Test
	public void checkcourse() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27007");
		CourseSystemRequest test = new CourseSystemRequest(codeList);
		courseSystemService.findByCourseID(test);

	}
	@Test
	public void deletecourse() {
		List<String> codeList = new ArrayList<>();
		codeList.add("ELE27003");
		CourseSystemRequest test = new CourseSystemRequest(codeList);
		courseSystemService.deleteCourseInfo(test);

	}
	@Test
	public void deletestudent() {
		String sid = "102003";
		CourseSystemRequest test = new CourseSystemRequest(sid);
		courseSystemService.deleteStudentInfo(test);

	}

	@Test
	public void addCourseListInfoTest() {
		List<String> codeList = new ArrayList<>();
		codeList.add("");
		CourseSystemRequest courseIdEmpty = new CourseSystemRequest(codeList, "文化研究導論", 1, 8, 10, 2);
		CourseSystemResponse res = courseSystemService.addCourseListInfo(courseIdEmpty);
		Assert.isTrue(res.getMessage().equalsIgnoreCase(RtnResponse.CANNOT_EMPTY.getMessage()),"錯誤");
	}

}
