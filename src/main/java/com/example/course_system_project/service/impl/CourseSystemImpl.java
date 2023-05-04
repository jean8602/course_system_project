package com.example.course_system_project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.course_system_project.constants.RtnResponse;
import com.example.course_system_project.entity.CourseInfo;
import com.example.course_system_project.entity.StudentInfo;
import com.example.course_system_project.repository.CourseDao;
import com.example.course_system_project.repository.StudentDao;
import com.example.course_system_project.service.ifs.CourseSystemService;
import com.example.course_system_project.vo.CourseSystemRequest;
import com.example.course_system_project.vo.CourseSystemResponse;
import com.example.course_system_project.vo.StudentEnrollInfoCheck;
import com.fasterxml.jackson.core.sym.Name;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.xdevapi.Result;

@Service
public class CourseSystemImpl implements CourseSystemService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;

	@Override
	// 新增課程資訊
	public CourseSystemResponse addCourseListInfo(CourseSystemRequest courseSystemRequest) {
		// 判斷課程代碼、課程名稱、星期、開始時間、結束時間、學分數

		String courseIdPattern = "\\w{8}";

		CourseInfo courseInfo = courseSystemRequest.getCourseInfo();
		String courseID = courseInfo.getCourseID();
		String courseName = courseInfo.getCourseName();
		int weekday = courseInfo.getWeekday();
		int startTime = courseInfo.getStartTime();
		int endTime = courseInfo.getEndTime();
		int courseUnit = courseInfo.getCourseUnit();

		/* Rtn response 各說明寫法？是否能自訂或是都用一樣的 */
		// 課程代碼不得為空必須有內容 代碼長度格式限制
		if (!StringUtils.hasText(courseID) && !courseID.matches(courseIdPattern)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		// 課程名稱不得為空必須有內容
		if (!StringUtils.hasText(courseName)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		// 星期限制於1~5僅限平日上課
		if (weekday < 1 || weekday > 5) {
			return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
		}
		// 開始時間為8點起 最後一堂課的時間為晚上7點 //***開始時間不能比結束時間晚！
		if (startTime < 8 || endTime > 19 || startTime >= endTime) {
			return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
		}
		// 學分數最低為1 最高為3
		if (courseUnit < 1 && courseUnit > 3) {
			return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
		}
		// 新增課程時課程代碼不得重複
		if (courseDao.existsById(courseID)) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}

		courseDao.save(courseInfo);
		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage(), courseInfo);
	}

	@Override
	public CourseSystemResponse addStudentListInfo(CourseSystemRequest courseSystemRequest) {
		// 學生學號(PK)student ID、學生姓名student name、選課程代碼course ID (a,b,c 字串
		String studentIDPattern = "^[0-9]{6}";
		String studentID = courseSystemRequest.getStudentID();
		String studentName = courseSystemRequest.getStudentName();
		// 學號不得為空必須有內容，學號只能是數字且限定6碼
		if (!StringUtils.hasText(studentID) || !studentID.matches(studentIDPattern)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		// 學生姓名不得為空必須有內容
		if (!StringUtils.hasText(studentName)) {
			return new CourseSystemResponse(RtnResponse.INFO_ERROR.getCode());
		}
		// 學號不得重複
		if (studentDao.existsById(studentID)) {
			return new CourseSystemResponse(RtnResponse.DUPLICATE_INFO.getCode());
		}
		StudentInfo studentInfo = new StudentInfo(studentID, studentName);
		studentDao.save(studentInfo);
		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());

	}

	@Override
	public CourseSystemResponse studentEnrollClass(CourseSystemRequest courseSystemRequest) {
		// 怎麼把東西丟進來 這些東西怎麼用
		// 從學生資料表裡面去撈所有欄位的資料
		Optional<StudentInfo> studentIdExistResult = studentDao.findById(courseSystemRequest.getStudentID());
		// sid name code
		// 確認輸入之學號是否已在學生清單內
		if (!studentIdExistResult.isPresent()) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		// 一開始現有學生資料庫的課程代碼為空白
		if (!StringUtils.hasText(studentIdExistResult.get().getCourseID())) {
			// （1) 學生還沒選過，學生預選課程代碼 撈資料庫比對該課代碼是否有存在課程清單內
			List<String> selectingCode = courseSystemRequest.getCourseID();
			if (CollectionUtils.isEmpty(selectingCode)) {
				return new CourseSystemResponse(RtnResponse.DATA_ERROR.getMessage());
			}
			List<CourseInfo> selectCourseInfoResult = courseDao.findAllById(selectingCode);
			// 開始選課
			// 判斷時間是否有在同一天與衝堂 同一天才有衝堂的可能
			for (int i = 0; i < selectCourseInfoResult.size(); i++) {
				for (int j = i + 1; j < selectCourseInfoResult.size(); j++) {
					if (selectCourseInfoResult.get(i).getWeekday() == selectCourseInfoResult.get(j).getWeekday()) {
						if (selectCourseInfoResult.get(i).getStartTime() >= selectCourseInfoResult.get(j).getEndTime()
								|| selectCourseInfoResult.get(i).getEndTime() <= selectCourseInfoResult.get(j)
										.getStartTime()) {
							continue;
						}
						// 假如有衝堂就跳出錯誤訊息
						return new CourseSystemResponse(RtnResponse.DATA_ERROR.getMessage());
					}
					// 判斷課程名稱是否有重複
					if (selectCourseInfoResult.get(i).getCourseName()
							.equals(selectCourseInfoResult.get(j).getCourseName())) {
						return new CourseSystemResponse(RtnResponse.DUPLICATE_INFO.getMessage());
					}
					// 判斷單堂學生人數是否有超過3人能否用下面的遍歷方式去判斷

				}

			}

			// 判斷選擇的所有課程之個別學生人數是否有超過3人
			for (CourseInfo item : selectCourseInfoResult) { // a01,a02,a03個別的學生人數
				if (item.getStudentNumber() >= 3) {
					return new CourseSystemResponse(RtnResponse.DATA_ERROR.getMessage());
				}
				// 判斷學分數加總是否有超過
				int totalCourseUnits = 0;
				totalCourseUnits += item.getCourseUnit();
				if (totalCourseUnits >= 10) {
					return new CourseSystemResponse(RtnResponse.DATA_ERROR.getMessage());
				}
				// 課程人數＋1
				item.setStudentNumber(item.getStudentNumber() + 1);
				courseDao.save(item);
			}

			// 把符合條件的選課代碼存入學生資料庫
			// 因為輸入的課程代碼為list要先將他轉為字串才可以存入
			// [a, b, c]
			// a,b,c
			String finalCourseCode = selectingCode.toString().replace("[", "").replace("]", "").replace(" ", "");
			// 把課程代碼存入
			studentIdExistResult.get().setCourseID(finalCourseCode);

			studentDao.save(studentIdExistResult.get());
			return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());
		}
		// ==============================================================================
		// 2. 學生已選過課
		// 輸入學號，找出已選課之課程代碼 存入list撈這些資料去切字串
		String existCode = studentIdExistResult.get().getCourseID();

		// 假使學生有選過課，課程代碼非空白
		if (StringUtils.hasText(existCode)) {
			String[] speratedResult = existCode.split(",");
			ArrayList<String> selectedCourse = new ArrayList<String>();
			for (String s : speratedResult) {
				selectedCourse.add(s); // 27001 27002
			}
			// 學生輸入要選的多筆課程代碼
			List<String> secondselectCode = courseSystemRequest.getCourseID();
			// 再次輸入的課程代碼與資料庫比對
			List<CourseInfo> secondselectinfo = courseDao.findAllById(secondselectCode);
			List<CourseInfo> existselect = courseDao.findAllById(selectedCourse);
			// 跟已選的課程代碼比較是否有重複//準備要選的課程代碼是否有重複 比對
			for (CourseInfo item : secondselectinfo) {
				for (CourseInfo item2 : existselect) {
					if (item.getCourseID().equals(item2.getCourseID())) {
						return new CourseSystemResponse(RtnResponse.DUPLICATE_INFO.getMessage());
					}
					if (item.getCourseName().equals(item2.getCourseName())) {
						return new CourseSystemResponse(RtnResponse.DUPLICATE_INFO.getMessage());
					}
					// 開始選課, 根據輸入的課程代碼與已存在的課程比對學生人數是否有超過
					if (item.getStudentNumber() >= 3) {
						return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
					}
					// 開始選課, 根據輸入的課程代碼與已存在的課程比對時間是否有衝堂
					if (item.getWeekday() == item2.getWeekday()) {
						if (item.getStartTime() >= item2.getEndTime() || item.getEndTime() <= item2.getStartTime()) {
							continue;
						}
						return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
					}
				}
			}
			// 學分數加總
			int totalUnit = 0;
			for (CourseInfo item : secondselectinfo) {
				totalUnit += item.getCourseUnit();
			}
			for (CourseInfo item2 : existselect) {
				totalUnit += item2.getCourseUnit();
			}
			if (totalUnit >= 10) {
				return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
			}
			// 課程資料庫學生人數要＋1
			for (CourseInfo result : secondselectinfo) {
				result.setStudentNumber(result.getStudentNumber() + 1);
				courseDao.save(result);
			}
			// 將第二次結果加入
			StringBuffer sb = new StringBuffer();
			for (String code : selectedCourse) {
				sb.append(code);
				sb.append(",");
			}
			for (int i = 0; i < secondselectCode.size(); i++) {
				if (i == secondselectCode.size() - 1) {
					sb.append(secondselectCode.get(i));
					break;
				}
				sb.append(secondselectCode.get(i));
				sb.append(",");
			}
//			for (String code : secondselectCode) {
//				sb.append(",");
//				sb.append(code);
//			}
			String finalCourseCode2 = sb.toString();
//			String finalCourseCode2 = secondselectCode.toString().replace("[", "").replace("]", "").replace(" ", "");
			studentIdExistResult.get().setCourseID(finalCourseCode2);
			studentDao.save(studentIdExistResult.get());

		}

		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());

	}

	@Override
	public CourseSystemResponse studentDropClass(CourseSystemRequest courseSystemRequest) {
		// 學生輸入學號之確認資料
		Optional<StudentInfo> checkStudentInfo = studentDao.findById(courseSystemRequest.getStudentID());
		// 確認輸入之學號是否已在學生清單內
		if (!checkStudentInfo.isPresent()) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		// 確認學生資料庫裡該學生的課程代碼不為空白 才可以退選
		if (!StringUtils.hasText(checkStudentInfo.get().getCourseID())) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		// 輸入的課程代碼 可以輸入多筆退選代號
		List<String> inputCourseCode = courseSystemRequest.getCourseID();
		// 要退選輸入之課程代碼與課程資料比對
		List<CourseInfo> dropCourseInfo = courseDao.findAllById(inputCourseCode);

		// 輸入之課程代碼是否存在於資料庫 如果沒有存在就不能退選
		if (CollectionUtils.isEmpty(dropCourseInfo)) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}

		// 輸入之課程代碼是否和學生資料庫課程代碼重複 如果沒有就不能退選
		for (CourseInfo item : dropCourseInfo) {
			if (!checkStudentInfo.get().getCourseID().contains(item.getCourseID())) {
				return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
			}
			// 學生清單裡面的選課代碼資料要移除,學生已選課代碼裡面是001,007,008之字串 用字串方式移除退選代碼
			String str = checkStudentInfo.get().getCourseID();
			List<String> list = new ArrayList<>(Arrays.asList(str.split(",")));
			list.remove(item.getCourseID());
			str = String.join(",", list);
			checkStudentInfo.get().setCourseID(str);
			studentDao.save(checkStudentInfo.get());
		}

		// 課程清單裡的學生數要扣除
		for (CourseInfo result : dropCourseInfo) {
			result.setStudentNumber(result.getStudentNumber() - 1);
			courseDao.save(result);
		}

		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());
	}

	@Override
	public CourseSystemResponse findByCourseID(CourseSystemRequest courseSystemRequest) {
		List<String> courseId = courseSystemRequest.getCourseID();
		if (CollectionUtils.isEmpty(courseId)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		List<CourseInfo> result = courseDao.findAllById(courseId);
		if (CollectionUtils.isEmpty(result)) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage(), result);
	}

	@Override
	public CourseSystemResponse findByCourseName(CourseSystemRequest courseSystemRequest) {
		String courseName = courseSystemRequest.getCourseName();
		if (!StringUtils.hasText(courseName)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		List<CourseInfo> result = courseDao.findAllByCourseName(courseName);
		if (CollectionUtils.isEmpty(result)) {
			return new CourseSystemResponse(RtnResponse.DATA_ERROR.getMessage());
		}

		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage(), result);
	}

	@Override
	public CourseSystemResponse studentEnrollInfoCheck(CourseSystemRequest courseSystemRequest) {
		String studentID = courseSystemRequest.getStudentID();
		if (!StringUtils.hasText(studentID)) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		List<StudentEnrollInfoCheck> result = studentDao.studentEnrollInfoCheck(studentID);
		if (CollectionUtils.isEmpty(result)) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		return new CourseSystemResponse(result);
	}

	@Override
	public CourseSystemResponse deleteCourseInfo(CourseSystemRequest courseSystemRequest) {
		// 輸入課程代碼 確認不得為空、存在於資料庫
		List<String> courseNum = courseSystemRequest.getCourseID();
		if (CollectionUtils.isEmpty(courseNum)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		List<CourseInfo> courseInfo = courseDao.findAllById(courseNum);
		if (CollectionUtils.isEmpty(courseInfo)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		// 確認課程人數為0才可以刪除
		for (CourseInfo item : courseInfo) {
			if (item.getStudentNumber() != 0) {
				return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
			}

		}
		courseDao.deleteAllById(courseNum);
		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());
	}

	@Override
	public CourseSystemResponse deleteStudentInfo(CourseSystemRequest courseSystemRequest) {
		// 確認學生學號不為空、存在於資料庫
		String studentNum = courseSystemRequest.getStudentID();
		if (!StringUtils.hasText(studentNum)) {
			return new CourseSystemResponse(RtnResponse.CANNOT_EMPTY.getMessage());
		}
		Optional<StudentInfo> res = studentDao.findById(studentNum);
		// 確認這筆學生是否已在資料庫
		if (!res.isPresent()) {
			return new CourseSystemResponse(RtnResponse.NOT_FOUND.getMessage());
		}
		// 確認學生選課代碼空白才可以刪除學生資料
		if (StringUtils.hasText(res.get().getCourseID())) {
			return new CourseSystemResponse(RtnResponse.INFO_ERROR.getMessage());
		}
		studentDao.deleteById(studentNum);
		return new CourseSystemResponse(RtnResponse.SUCCESSFUL.getMessage());
	}

}
