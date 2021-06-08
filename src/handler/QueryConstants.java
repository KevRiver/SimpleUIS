package handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import query.sender.QuerySender;

public class QueryConstants {
	/** 관리자 기능 **/
	/*
	 * ● 데이터베이스 초기화 기능
		● 데이터베이스에 포함된 모든 테이블에 대한 입력/삭제/변경 기능단, 삭제/변경은 “1개”의 고정된 특정 조건이 아닌 “조건식”을 입력 받아서 삭제/변경하는 방식으로 구현해야 함
		● 전체 테이블 보기 : 모든 테이블의 내용을 보여주는 기능

	 * */
	// ● 데이터베이스 초기화 기능
	public static final void initializeDB() {
		// Launch initializeDB
		QuerySender.getInstance().executeSQLFile("SQLFile/initializeDB.sql");
	}
	
	
	// ● 데이터베이스에 포함된 모든 테이블에 대한 입력/삭제/변경 기능단, 삭제/변경은 “1개”의 고정된 특정 조건이 아닌 “조건식”을 입력 받아서 삭제/변경하는 방식으로 구현해야 함
	// valueFormat Example : (%d, %s, %s, %s, %s)
	public static final String insertValue(String tableName, List<Object> list) {
		StringBuilder query
		= new StringBuilder(String.format("INSERT INTO %s VALUES(", tableName));
		
		for (int i = 0; i < list.size(); i++) {
			Object item = list.get(i);
			if (item instanceof String)
				query.append("'" + item.toString() + "'");
			else if (item instanceof Integer)
				query.append((int)(item));
			else if (item == null)
				query.append("null");
			
			if (i < list.size() - 1)
				query.append(", ");
		}
		query.append(");");
		
		return query.toString();
	}
	
	// INSERT INTO Professor VALUES(%d, %s, %s, %s, %s);
	public static final String updateValue(String tableName, Map<String, Object> columnDataMap, String condExpression) {
		// UPDATE table_name SET name = '테스트 변경', country = '대한민국' WHERE id = 1105;
		StringBuilder query
		= new StringBuilder(String.format("UPDATE %s SET", tableName));
		
		int sizeChk = 0;
		Iterator<String> iter = columnDataMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next().toString();
			Object value = columnDataMap.get(key);
			query.append(String.format(" %s = ", key));
			
			if (value instanceof String)
				query.append("'" + value.toString() + "'");
			else if (value instanceof Integer)
				query.append((int)value);
			else if (value == null)
				query.append("null");
			
			if (sizeChk < columnDataMap.size() - 1)
				query.append(",");
			sizeChk++;
		}
		
		query.append(String.format(" WHERE %s;", condExpression));
		return query.toString();
	}
	
	public static final String deleteValue(String tableName, String condExpression) {
		StringBuilder query
		= new StringBuilder(String.format("DELETE FROM %s WHERE %s;", tableName, condExpression));
		
		return query.toString();
	}
	
	
	public static final String selectTable(String tableName) {
		StringBuilder query = new StringBuilder(String.format("SELECT * FROM %s;", tableName));
		
		return query.toString();
	}
	
	
	// 교수 기능
	// 입력된 연도/학기에 본인이 강의했던 과목에 대한 모든 정보를 보여주는 기능
	public static final String professorFunc1(int professorNumber, int year, int univSemester) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course WHERE courseNumber IN ");
		query.append(String.format("(SELECT Course_courseNumber FROM CourseHistory where ", year));
		query.append(String.format("(year = %d AND univSemester = %d AND Professor_professorNumber = %d));", year, univSemester, professorNumber));
		
		return query.toString();
	}
	
	// 위에서 표시된 과목 정보 중에서 하나를 “클릭”하면 해당 과목을 수강하는 (혹은 수강했던) 모든 학생에 대한 정보를 보여주는 기능
	public static final String professorFunc2(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Student where (studentNumber IN ");
		query.append(String.format("(SELECT Student_studentNumber FROM CourseHistory where (Course_courseNumber = %d)));", professorNumber));
			
		return query.toString();
	}
	
	// ● 현재 본인이 “지도”하는 학생에 대한 정보를 보여주는 기능
	public static final String professorFunc3(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Student where (studentNumber IN ");
		query.append(String.format("(SELECT Student_studentNumber FROM Professor_has_Student where Professor_professorNumber = %d));", professorNumber));
				
		return query.toString();
	}
	
	// ● 위에서 표시된 학생 정보 중에서 하나를 “클릭”하면 해당 학생이 수강했던 (혹은 수강하고 있는) 모든 과목에 대한 “성적” 정보를 보여주는 기능
	public static final String professorFunc4(int studentNumber) {
		StringBuilder query = new StringBuilder(
				"SELECT CourseHistory.year, CourseHistory.univSemester, Course.courseName, CourseHistory.grade,");
		query.append(" CourseHistory.allPoint, CourseHistory.attendancePoint, CourseHistory.midtermExamPoint,");
		query.append(" CourseHistory.finalExamPoint, CourseHistory.etcPoint FROM Course, CourseHistory");
		query.append(String.format(" where CourseHistory.Student_studentNumber = %d", studentNumber));
		query.append(" AND Course.courseNumber = CourseHistory.Course_courseNumber;");
		
		return query.toString();
	}
	
	// ● 본인이 소속된 학과에 대한 정보(학과장 정보 포함)를 보여주는 기능
	public static final String professorFunc5(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Major WHERE majorNumber");
		query.append(String.format(" IN (SELECT Major_majorNumber FROM Professor_has_Major WHERE (Professor_professorNumber = %d));", professorNumber));
		
		return query.toString();
	}
	
	// ● 현재 학기에 대한 “강의 시간표” 표시 기능 : 현재 학기에 강의하는 과목을 시간표 형태로 표시함. 시간표는 요일/교시
	public static final String professorFunc6(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course Where courseNumber IN ");
		query.append(String.format("(SELECT DISTINCT Course_courseNumber From CourseHistory where((Professor_professorNumber = %d) AND", professorNumber));
		query.append(String.format(" year = (SELECT DISTINCT year FROM Professor_has_Student where (Professor_professorNumber = %d)) AND", professorNumber));
		query.append(String.format(" univSemester = (SELECT DISTINCT univSemester FROM Professor_has_Student where (Professor_professorNumber = %d))));", professorNumber));
		
		return query.toString();
	}
	
	// ● 현재 본인이 강의하는 과목에 대한 성적 입력 기능 - 학생번호와 학생이름을 보여줌
	public static final String professorFunc7_showStudent(int professorNumber, int courseNumber) {
		StringBuilder query = new StringBuilder("SELECT studentNumber, studentName FROM Student WHERE studentNumber IN");
		query.append(String.format(" (SELECT Student_studentNumber FROM CourseHistory WHERE Professor_professorNumber = %d", professorNumber));
		query.append(String.format(" AND Course_courseNumber = %d);", courseNumber));
		
		return query.toString();
	}
	
	// ● 현재 본인이 강의하는 과목에 대한 성적 입력 기능 - 성적 입력
	public static final String professorFunc7_update(
			int professorNumber,
			int studentNumber,
			int courseNumber,
			int attendancePoint,
			int midtermExamPoint,
			int finalExamPoint,
			int etcPoint,
			int allPoint,
			String grade) {
		StringBuilder query = new StringBuilder("UPDATE CourseHistory SET");
		query.append(String.format(" attendancePoint = %d, midtermExamPoint = %d, finalExamPoint = %d, etcPoint = %d, allPoint = %d, grade = '%s'",
				attendancePoint, midtermExamPoint, finalExamPoint, etcPoint, allPoint, grade));
		query.append(String.format(" WHERE (Student_studentNumber = %d AND Professor_professorNumber = %d AND Course_courseNumber = %d);",
				studentNumber, professorNumber, courseNumber));
		
		return query.toString();
	}
	
	
	// 학생 기능
	// ● 입력된 연도/학기에 본인이 수강했던 과목에 대한 모든 정보를 보여주는 기능
	public static final String studentFunc1(int studentNumber, int year, int univSemester) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course where courseNumber IN (SELECT Course_courseNumber from CourseHistory");
		query.append(String.format(" WHERE (year = %d AND univSemester = %d AND Student_studentNumber = %d));", year, univSemester, studentNumber));
		
		return query.toString();
	}
	
	// ● 현재 학기에 본인이 수강하는 모든 과목을 시간표 형태로 표시하는 기능
	public static final String studentFunc2(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course Where courseNumber IN");
		query.append(String.format(" (SELECT DISTINCT Course_courseNumber From CourseHistory where((Student_studentNumber = %d) AND", studentNumber));
		query.append(String.format(" year = (SELECT DISTINCT year FROM Professor_has_Student where (Student_studentNumber = %d)) AND", studentNumber));
		query.append(String.format(" univSemester = (SELECT DISTINCT univSemester FROM Professor_has_Student where (Student_studentNumber = %d))));", studentNumber));
		
		return query.toString();
	}

	// ● 본인이 소속된 동아리에 대한 정보를 보여주는 기능
	public static final String studentFunc3_showClub(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Club Where clubNumber IN");
		query.append(String.format(" (SELECT Club_clubNumber FROM Club_has_Student Where Student_studentNumber = %d);", studentNumber));
		
		return query.toString();
	}
	
	// 단, 동아리 회장의 경우에는 동아리에 “속한” 모든 학생들에 대한 정보를 보여주는 기능이 구현되어야 함
	public static final String studentFunc3_showAllClubMemberInfo(int leaderStudentNumber) {
		/*회장이면 button 활성화 등으로 설정 모든 학생 정보 출력*/
		// studentFunc3_show를 호출해서 Club 정보를 얻고, studentNumber와 leaderStudentNumber가 같은 경우 이 메소드 호출하면 됨
		StringBuilder query = new StringBuilder("SELECT * FROM Student WHERE studentNumber IN");
		query.append(" (SELECT Student_studentNumber FROM Club_has_Student WHERE Club_clubNumber IN");
		query.append(String.format(" (SELECT clubNumber FROM Club Where Student_leaderStudentNumber = %d));", leaderStudentNumber));
		
		return query.toString();
	}	
	
	// ● 본인의 성적표를 보여주는 기능 : 과목번호/과목명/취득학점/평점은 반드시 표시되어야 하며, GPA (grade point average)도 표시되어야 한다.
	public static final String studentFunc4(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT Course.courseNumber, Course.courseName, Course.courseGradeNumber, CourseHistory.grade FROM Course, CourseHistory");
		query.append(String.format(" Where CourseHistory.Student_studentNumber = %d AND Course.courseNumber = CourseHistory.Course_courseNumber;", studentNumber));
		
		return query.toString();
	}
	
	// 학기당 수강학점 10학점 제한을 위해 수강내역에 있는 총 학점을 구함
	/*
	 * SELECT SUM(courseGradeNumber) FROM Course WHERE courseNumber IN
(SELECT Course_courseNumber FROM CourseHistory WHERE Student_studentNumber = 1 AND year = 2021 AND univSemester = 1);
	 * */
	public static final String getAllGradeNumber(int studentNumber, int year, int univSemester) {
		StringBuilder query = new StringBuilder("SELECT SUM(courseGradeNumber) FROM Course WHERE courseNumber IN");
		query.append(String.format(" (SELECT Course_courseNumber FROM CourseHistory WHERE Student_studentNumber = %d AND year = %d AND univSemester = %d);",
				studentNumber, year, univSemester));
		return query.toString();
	}
}