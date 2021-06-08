package scene;

import java.lang.*;
import java.util.*;
import java.util.List;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import javax.swing.*;

public class QueryForm extends VerticalBoxList{
	protected String _title;
	protected String _queryString;
	protected String _sqlFilePath;
	protected int _resultTableType;
	
	protected JLabel _titleLabel;
	
	protected HorizontalBoxList _paramList;
	protected List<String> _paramNames;
	protected List<JTextField> _textFields;
	
	protected JButton _submitButton;
	
	protected int _width = 575;
	protected int _height = 80;
	
	public QueryForm() {
		initList();
	}
	
	public void setTitle(String title) {
		_title = title;
	}
	
	public void setParamNames(List<String> paramNames) {
		_paramNames = paramNames;
	}
	
	protected void addTitle() {
		_titleLabel = new JLabel("Title");
		int titleWidth = Constants.PRIMARY_FRAME_WIDTH / 10;
		int titleHeight = Constants.PRIMARY_FRAME_HEIGHT / 20;
		_titleLabel.setPreferredSize(new Dimension(titleWidth, titleHeight));
		_titleLabel.setFont(new Font("Consolas", Font.BOLD, 28));
		_titleLabel.setForeground(Color.BLACK);
		
		_titleLabel.setAlignmentX(LEFT_ALIGNMENT);
		add(_titleLabel);
		add(Box.createVerticalStrut(_vStrut));
	}
	
	protected void addParamForm() {
		int paramCount = _paramNames.size();
		if(paramCount < 1) return;
		
		List<JComponent> params = new ArrayList<>();
		
		final int textFieldWidth = Constants.PRIMARY_FRAME_WIDTH / 12;
		final int textFieldHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
		final int textFieldFontSize = 12;
		final Font textFieldFont = new Font("Consolas", Font.PLAIN, textFieldFontSize);
		
		for(int i = 0; i < paramCount; i++) {
			JTextField paramInputField = new JTextField();
			paramInputField.setPreferredSize(new Dimension(textFieldWidth, textFieldHeight));
			paramInputField.setFont(textFieldFont);
			
			_textFields.add(paramInputField);
		}
		
		final int labelWidth = Constants.PRIMARY_FRAME_WIDTH / 24;
		final int labelHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
		final int labelFontSize = 10;
		final Font labelFont = new Font("Consolas", Font.BOLD, labelFontSize);
		for(int i = 0; i < paramCount; i++) {
			JLabel paramName = new JLabel(_paramNames.get(i) + " :");
			paramName.setPreferredSize(new Dimension(labelWidth, labelHeight));
			paramName.setFont(labelFont);
			paramName.setHorizontalAlignment(JLabel.RIGHT);
			params.add(paramName);
			params.add(_textFields.get(i));
		}
		
		_paramList.initListWithItems(params);
		
		//_paramList.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
		
		_paramList.setAlignmentX(LEFT_ALIGNMENT);
		this.add(_paramList);
		add(Box.createVerticalStrut(_vStrut));
	}
	
	protected void addSubmitButton() {
		_submitButton = new JButton("Submit");
		int buttonWidth = Constants.PRIMARY_FRAME_WIDTH / 13;
		int buttonHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
		_submitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		_submitButton.setFont(new Font("Consolas", Font.BOLD, 12));
		_submitButton.setOpaque(true);
		_submitButton.setBackground(Color.WHITE);
		_submitButton.setAlignmentX(LEFT_ALIGNMENT);
		_submitButton.setAlignmentY(JButton.CENTER);
		_submitButton.setForeground(Color.BLACK);
		
		add(_submitButton);
		add(Box.createVerticalStrut(_vStrut));
	}
	
	@Override
	protected void initList() {
		_title = "Title";
		
		_paramNames = new ArrayList<>();
//		_paramNames.add("fuck");
//		_paramNames.add("pussy");
//		_paramNames.add("dick");
		_textFields = new ArrayList<>();
		_paramList = new HorizontalBoxList();
		
		int paramListWidth = Constants.PRIMARY_FRAME_WIDTH / 20;
		int paramListHeight = Constants.PRIMARY_FRAME_HEIGHT / 28;
		_paramList.setPreferredSize(new Dimension(paramListWidth, paramListHeight));
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setPreferredSize(new Dimension(_width, _height));
		this.setBackground(Color.WHITE);
		
		addTitle();
		addParamForm();
		addSubmitButton();
	}
}
