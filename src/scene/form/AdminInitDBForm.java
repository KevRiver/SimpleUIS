package scene.form;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import handler.QueryConstants;
import query.sender.QuerySender;
import scene.Constants;
import scene.model.HorizontalBoxList;

public class AdminInitDBForm extends QueryForm{
	
	public AdminInitDBForm() {
		initList();
	}
	
	@Override
	protected void sendQuery() {
		QueryConstants.initializeDB();
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
		
		setTitle("InitDB");
//		_paramNames.add("Table");
//		_paramNames.add("Format");
//		_paramNames.add("Values");
		
		addTitle();
		addParamForm();
		addSubmitButton();
	}

}
