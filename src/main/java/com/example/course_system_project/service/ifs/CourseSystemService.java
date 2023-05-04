package com.example.course_system_project.service.ifs;

import com.example.course_system_project.vo.CourseSystemRequest;
import com.example.course_system_project.vo.CourseSystemResponse;

public interface CourseSystemService {
	// 新增課程資訊
	public CourseSystemResponse addCourseListInfo(CourseSystemRequest courseSystemRequest);

	// 新增學生資訊
	public CourseSystemResponse addStudentListInfo(CourseSystemRequest courseSystemRequest);

	// 學生選課
	public CourseSystemResponse studentEnrollClass(CourseSystemRequest courseSystemRequest);

	// 學生退選
	public CourseSystemResponse studentDropClass(CourseSystemRequest courseSystemRequest);
	
	//課程資訊查詢
	//依課程代碼查詢
	public CourseSystemResponse findByCourseID (CourseSystemRequest courseSystemRequest);
	//依課程名稱查詢
	public CourseSystemResponse findByCourseName (CourseSystemRequest courseSystemRequest);
	//學生選課資訊查詢
	public CourseSystemResponse studentEnrollInfoCheck (CourseSystemRequest courseSystemRequest);
	//課程資料刪除
	public CourseSystemResponse deleteCourseInfo (CourseSystemRequest courseSystemRequest);
	//學生資料刪除
	public CourseSystemResponse deleteStudentInfo (CourseSystemRequest courseSystemRequest);

}
