package scene.form;

import handler.QueryConstants;
import query.sender.QuerySender;

public class ProfessorForm1 extends QueryForm{
	public ProfessorForm1() {
			setTitle("Professor 1");
			_paramNames.add("Pid");
			_paramNames.add("Year");
			_paramNames.add("Semester");
	}
	
	@Override
	protected void sendQuery() {
		int pid = Integer.parseInt(_textFields.get(0).getText());
		int year = Integer.parseInt(_textFields.get(1).getText());
		int semester = Integer.parseInt(_textFields.get(2).getText());
		
		String queryString = QueryConstants.professorFunc1(pid, year, semester);
		
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(queryString, _title, null);
	}
}
