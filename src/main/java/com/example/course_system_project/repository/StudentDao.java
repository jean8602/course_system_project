package com.example.course_system_project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.course_system_project.entity.StudentInfo;
import com.example.course_system_project.vo.CourseSystemRequest;
import com.example.course_system_project.vo.CourseSystemResponse;
import com.example.course_system_project.vo.StudentEnrollInfoCheck;

public interface StudentDao extends JpaRepository<StudentInfo, String>{
	@Transactional
	@Modifying
	@Query("select new com.example.course_system_project.vo.StudentEnrollInfoCheck(s.studentID, s.studentName, c.courseID, c.courseName, c.weekday, c.startTime, c.endTime, c.courseUnit)"
			+ " from CourseInfo c join StudentInfo s on s.courseID"
			+ " like concat ('%', c.courseID, '%')"
			+ " where s.studentID = :newNumber")
	public List<StudentEnrollInfoCheck> studentEnrollInfoCheck (@Param("newNumber") String newNumber);

}
