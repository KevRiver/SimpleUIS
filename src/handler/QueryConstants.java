package handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import query.sender.QuerySender;

public class QueryConstants {
	/** ������ ��� **/
	/*
	 * �� �����ͺ��̽� �ʱ�ȭ ���
		�� �����ͺ��̽��� ���Ե� ��� ���̺� ���� �Է�/����/���� ��ɴ�, ����/������ ��1������ ������ Ư�� ������ �ƴ� �����ǽġ��� �Է� �޾Ƽ� ����/�����ϴ� ������� �����ؾ� ��
		�� ��ü ���̺� ���� : ��� ���̺��� ������ �����ִ� ���

	 * */
	// �� �����ͺ��̽� �ʱ�ȭ ���
	public static final void initializeDB() {
		// Launch initializeDB
		QuerySender.getInstance().executeSQLFile("SQLFile/initializeDB.sql");
	}
	
	
	// �� �����ͺ��̽��� ���Ե� ��� ���̺� ���� �Է�/����/���� ��ɴ�, ����/������ ��1������ ������ Ư�� ������ �ƴ� �����ǽġ��� �Է� �޾Ƽ� ����/�����ϴ� ������� �����ؾ� ��
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
		// UPDATE table_name SET name = '�׽�Ʈ ����', country = '���ѹα�' WHERE id = 1105;
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
	
	
	// ���� ���
	// �Էµ� ����/�б⿡ ������ �����ߴ� ���� ���� ��� ������ �����ִ� ���
	public static final String professorFunc1(int professorNumber, int year, int univSemester) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course WHERE courseNumber IN ");
		query.append(String.format("(SELECT Course_courseNumber FROM CourseHistory where ", year));
		query.append(String.format("(year = %d AND univSemester = %d AND Professor_professorNumber = %d));", year, univSemester, professorNumber));
		
		return query.toString();
	}
	
	// ������ ǥ�õ� ���� ���� �߿��� �ϳ��� ��Ŭ�����ϸ� �ش� ������ �����ϴ� (Ȥ�� �����ߴ�) ��� �л��� ���� ������ �����ִ� ���
	public static final String professorFunc2(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Student where (studentNumber IN ");
		query.append(String.format("(SELECT Student_studentNumber FROM CourseHistory where (Course_courseNumber = %d)));", professorNumber));
			
		return query.toString();
	}
	
	// �� ���� ������ ���������ϴ� �л��� ���� ������ �����ִ� ���
	public static final String professorFunc3(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Student where (studentNumber IN ");
		query.append(String.format("(SELECT Student_studentNumber FROM Professor_has_Student where Professor_professorNumber = %d));", professorNumber));
				
		return query.toString();
	}
	
	// �� ������ ǥ�õ� �л� ���� �߿��� �ϳ��� ��Ŭ�����ϸ� �ش� �л��� �����ߴ� (Ȥ�� �����ϰ� �ִ�) ��� ���� ���� �������� ������ �����ִ� ���
	public static final String professorFunc4(int studentNumber) {
		StringBuilder query = new StringBuilder(
				"SELECT CourseHistory.year, CourseHistory.univSemester, Course.courseName, CourseHistory.grade,");
		query.append(" CourseHistory.allPoint, CourseHistory.attendancePoint, CourseHistory.midtermExamPoint,");
		query.append(" CourseHistory.finalExamPoint, CourseHistory.etcPoint FROM Course, CourseHistory");
		query.append(String.format(" where CourseHistory.Student_studentNumber = %d", studentNumber));
		query.append(" AND Course.courseNumber = CourseHistory.Course_courseNumber;");
		
		return query.toString();
	}
	
	// �� ������ �Ҽӵ� �а��� ���� ����(�а��� ���� ����)�� �����ִ� ���
	public static final String professorFunc5(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Major WHERE majorNumber");
		query.append(String.format(" IN (SELECT Major_majorNumber FROM Professor_has_Major WHERE (Professor_professorNumber = %d));", professorNumber));
		
		return query.toString();
	}
	
	// �� ���� �б⿡ ���� ������ �ð�ǥ�� ǥ�� ��� : ���� �б⿡ �����ϴ� ������ �ð�ǥ ���·� ǥ����. �ð�ǥ�� ����/����
	public static final String professorFunc6(int professorNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course Where courseNumber IN ");
		query.append(String.format("(SELECT DISTINCT Course_courseNumber From CourseHistory where((Professor_professorNumber = %d) AND", professorNumber));
		query.append(String.format(" year = (SELECT DISTINCT year FROM Professor_has_Student where (Professor_professorNumber = %d)) AND", professorNumber));
		query.append(String.format(" univSemester = (SELECT DISTINCT univSemester FROM Professor_has_Student where (Professor_professorNumber = %d))));", professorNumber));
		
		return query.toString();
	}
	
	// �� ���� ������ �����ϴ� ���� ���� ���� �Է� ��� - �л���ȣ�� �л��̸��� ������
	public static final String professorFunc7_showStudent(int professorNumber, int courseNumber) {
		StringBuilder query = new StringBuilder("SELECT studentNumber, studentName FROM Student WHERE studentNumber IN");
		query.append(String.format(" (SELECT Student_studentNumber FROM CourseHistory WHERE Professor_professorNumber = %d", professorNumber));
		query.append(String.format(" AND Course_courseNumber = %d);", courseNumber));
		
		return query.toString();
	}
	
	// �� ���� ������ �����ϴ� ���� ���� ���� �Է� ��� - ���� �Է�
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
	
	
	// �л� ���
	// �� �Էµ� ����/�б⿡ ������ �����ߴ� ���� ���� ��� ������ �����ִ� ���
	public static final String studentFunc1(int studentNumber, int year, int univSemester) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course where courseNumber IN (SELECT Course_courseNumber from CourseHistory");
		query.append(String.format(" WHERE (year = %d AND univSemester = %d AND Student_studentNumber = %d));", year, univSemester, studentNumber));
		
		return query.toString();
	}
	
	// �� ���� �б⿡ ������ �����ϴ� ��� ������ �ð�ǥ ���·� ǥ���ϴ� ���
	public static final String studentFunc2(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Course Where courseNumber IN");
		query.append(String.format(" (SELECT DISTINCT Course_courseNumber From CourseHistory where((Student_studentNumber = %d) AND", studentNumber));
		query.append(String.format(" year = (SELECT DISTINCT year FROM Professor_has_Student where (Student_studentNumber = %d)) AND", studentNumber));
		query.append(String.format(" univSemester = (SELECT DISTINCT univSemester FROM Professor_has_Student where (Student_studentNumber = %d))));", studentNumber));
		
		return query.toString();
	}

	// �� ������ �Ҽӵ� ���Ƹ��� ���� ������ �����ִ� ���
	public static final String studentFunc3_showClub(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT * FROM Club Where clubNumber IN");
		query.append(String.format(" (SELECT Club_clubNumber FROM Club_has_Student Where Student_studentNumber = %d);", studentNumber));
		
		return query.toString();
	}
	
	// ��, ���Ƹ� ȸ���� ��쿡�� ���Ƹ��� �����ѡ� ��� �л��鿡 ���� ������ �����ִ� ����� �����Ǿ�� ��
	public static final String studentFunc3_showAllClubMemberInfo(int leaderStudentNumber) {
		/*ȸ���̸� button Ȱ��ȭ ������ ���� ��� �л� ���� ���*/
		// studentFunc3_show�� ȣ���ؼ� Club ������ ���, studentNumber�� leaderStudentNumber�� ���� ��� �� �޼ҵ� ȣ���ϸ� ��
		StringBuilder query = new StringBuilder("SELECT * FROM Student WHERE studentNumber IN");
		query.append(" (SELECT Student_studentNumber FROM Club_has_Student WHERE Club_clubNumber IN");
		query.append(String.format(" (SELECT clubNumber FROM Club Where Student_leaderStudentNumber = %d));", leaderStudentNumber));
		
		return query.toString();
	}	
	
	// �� ������ ����ǥ�� �����ִ� ��� : �����ȣ/�����/�������/������ �ݵ�� ǥ�õǾ�� �ϸ�, GPA (grade point average)�� ǥ�õǾ�� �Ѵ�.
	public static final String studentFunc4(int studentNumber) {
		StringBuilder query = new StringBuilder("SELECT Course.courseNumber, Course.courseName, Course.courseGradeNumber, CourseHistory.grade FROM Course, CourseHistory");
		query.append(String.format(" Where CourseHistory.Student_studentNumber = %d AND Course.courseNumber = CourseHistory.Course_courseNumber;", studentNumber));
		
		return query.toString();
	}
	
	// �б�� �������� 10���� ������ ���� ���������� �ִ� �� ������ ����
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