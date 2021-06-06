package scene;

import java.lang.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import javax.swing.*;

public class QueryForm extends GridBagPanel{
	public Point _gridPosition;
	public Dimension _size;
	public Point2D.Double _weight;
	public int alignment;
	public boolean fill;
	
	private JLabel _title;
	private JButton _submitButton;
	private int _width = 580;
	private int _height = 90;
	
	private StringBuilder _queryStringBuilder;
	
	public QueryForm() {
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(_width, _height));
		this.setGridCellPadding(5, 5, 5, 5);
		
		_title = new JLabel("Title");
		int titleWidth = Constants.PRIMARY_FRAME_WIDTH / 8;
		int titleHeight = Constants.PRIMARY_FRAME_HEIGHT / 30;
		_title.setPreferredSize(new Dimension(titleWidth, titleHeight));
		_title.setFont(new Font("Consolas", Font.BOLD, 24));
		_title.setAlignmentX(JLabel.LEFT);
		_title.setAlignmentY(JLabel.CENTER);
		_title.setForeground(Color.BLACK);
		int titleAlignment = GridBagConstraints.FIRST_LINE_START;
		addComponentAtGrid(_title, new Point(0,0), new Dimension(1,1), new Point2D.Double(1.0, 0.1), titleAlignment, false);
		
		_submitButton = new JButton("Submit");
		int buttonWidth = Constants.PRIMARY_FRAME_WIDTH / 13;
		int buttonHeight = Constants.PRIMARY_FRAME_HEIGHT / 24;
		_submitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		_submitButton.setFont(new Font("Consolas", Font.BOLD, 14));
		_submitButton.setAlignmentX(JButton.LEFT);
		_submitButton.setAlignmentY(JButton.CENTER);
		_submitButton.setForeground(Color.BLACK);
		int submitButtonAlignment = GridBagConstraints.LINE_END;
		addComponentAtGrid(_submitButton, new Point(0,1), new Dimension(1,1), new Point2D.Double(0.0, 0.1), submitButtonAlignment, false);
		
		_queryStringBuilder = new StringBuilder();
	}
}
