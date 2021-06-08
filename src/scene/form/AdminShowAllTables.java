package scene.form;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import handler.QueryConstants;
import query.sender.QuerySender;
import scene.Constants;
import scene.model.HorizontalBoxList;
import scene.model.ResultTableType;

public class AdminShowAllTables extends QueryForm{
	public AdminShowAllTables() {
		initList();
	}
	
	@Override
	protected void sendQuery() {
		String prof = QueryConstants.selectTable("Professor");
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(prof, "ProfessorTable", ResultTableType.TABLE);
		String stud = QueryConstants.selectTable("Student");
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(stud, "Student Table", ResultTableType.TABLE);
		String course = QueryConstants.selectTable("Course");
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(course, "ProfessorTable", ResultTableType.TABLE);
		String club = QueryConstants.selectTable("Club");
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(club, "Club", ResultTableType.TABLE);
		String maj = QueryConstants.selectTable("Major");
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(maj, "Maj", ResultTableType.TABLE);
		
	}
	
	@Override
	public void initList() {
		_paramNames = new ArrayList<>();
		_textFields = new ArrayList<>();
		_paramList = new HorizontalBoxList();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(_width, _height));
		this.setBackground(Color.WHITE);
		
		setTitle("Show Tables");
		
		addTitle();
		addParamForm();
		addSubmitButton();
	}
}
