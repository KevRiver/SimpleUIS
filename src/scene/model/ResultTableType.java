package scene.model;

public enum ResultTableType {
	TABLE,
	COURSE_EXTEND_TO_STUDENT, // 과목 id로 학생 테이블 만듬
	STUDENT_EXTEND_TO_GRADE, // 지도학생 id -> 지도 학생 성적
	COURSE_EXTEND_TO_GRADE_EDITOR,
	GRADE_EDITOR, // 성적입력표
	TIME_TABLE // 시간표
}
