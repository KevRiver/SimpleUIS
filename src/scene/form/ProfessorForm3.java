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

public class ProfessorForm3 extends QueryForm{
	public ProfessorForm3() {
		initList();
	}
	
	@Override
	public void initList() {
		_paramNames = new ArrayList<>();
		_textFields = new ArrayList<>();
		_paramList = new HorizontalBoxList();
		
		int paramListWidth = Constants.PRIMARY_FRAME_WIDTH / 20;
		int paramListHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
		_paramList.setPreferredSize(new Dimension(paramListWidth, paramListHeight));
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(_width, _height));
		this.setBackground(Color.WHITE);
		
		setTitle("Mentee");
		_paramNames.add("Pid");
		
		addTitle();
		addParamForm();
		addSubmitButton();
	}
	
	@Override
	protected void sendQuery() {
		try {
			int pid = Integer.parseInt(_textFields.get(0).getText());
			String str = QueryConstants.professorFunc3(pid);
			QuerySender.getInstance().executeQueryStringAndBroadcastResult(str, _title, ResultTableType.TABLE);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return;
		}
		
	}
}
