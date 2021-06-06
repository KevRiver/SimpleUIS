package scene;

import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.*;

public class MainScene extends JFrame implements IScene{
	private GridBagPanel _leftPanel;
	private JButton _backButton;
	private GridBagPanel _rightPanel;
	
	public MainScene() {
		super();
		
		super.setPreferredSize(new Dimension(Constants.PRIMARY_FRAME_WIDTH, Constants.PRIMARY_FRAME_HEIGHT));
		
		super.setLayout(new GridLayout(1, 2));
		
		initLeftPanel();
		
		initRightPanel();
		
		add(_leftPanel);
		add(_rightPanel);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		super.pack();
		
		super.setVisible(false);
	}
	
	private void initLeftPanel() {
		_leftPanel = new GridBagPanel();
		int panelWidth = Constants.PRIMARY_FRAME_WIDTH / 2;
		int panelHeight = Constants.PRIMARY_FRAME_HEIGHT;
		_leftPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		_leftPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 5, Color.BLUE));
		
		JButton backButton = new JButton("TestButton");
		int backButtonWidth = Constants.PRIMARY_FRAME_WIDTH / 10;
		int backButtonHeight = Constants.PRIMARY_FRAME_HEIGHT / 20;
		backButton.setPreferredSize(new Dimension(backButtonWidth, backButtonHeight));
		backButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4, false));
		int backButtonAlignment = GridBagConstraints.FIRST_LINE_START;
		_leftPanel.addComponentAtGrid(backButton, new Point(0, 0), new Dimension(1,1), new Point2D.Double(1.0, 0.0), backButtonAlignment, false);
		
		JLabel leftTitle = new JLabel("Query Forms");
		int leftTitleWidth = Constants.PRIMARY_FRAME_WIDTH / 4;
		int leftTitleHeight = Constants.PRIMARY_FRAME_HEIGHT / 20;
		leftTitle.setPreferredSize(new Dimension(leftTitleWidth, leftTitleHeight));
		int fontSize = 32;
		leftTitle.setFont(new Font("Verdana", Font.BOLD, fontSize));
		leftTitle.setForeground(Color.BLACK);
		leftTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		int leftTitleAlignment = GridBagConstraints.LAST_LINE_START;
		_leftPanel.addComponentAtGrid(leftTitle, new Point(0, 1), new Dimension(1, 1), new Point2D.Double(1.0, 0.0), leftTitleAlignment, false);
		
		GridBagPanel testPanel = new GridBagPanel();
		testPanel.setBackground(Color.BLACK);
		QueryForm queryForm = new QueryForm();
		testPanel.addComponentAtGrid(queryForm, new Point(0,0), new Dimension(1,1), new Point2D.Double(1.0, 0.0), GridBagConstraints.PAGE_START, false);
		
		GridBagPanel testPanel2 = new GridBagPanel();
		testPanel2.setBackground(Color.BLACK);
		QueryForm queryForm2 = new QueryForm();
		testPanel.addComponentAtGrid(queryForm2, new Point(0,1), new Dimension(1,1), new Point2D.Double(1.0, 0.1), GridBagConstraints.PAGE_START, false);
		
		JScrollPane scrollPane = new JScrollPane(testPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		int scrollPaneAlignment = GridBagConstraints.FIRST_LINE_START;
		_leftPanel.addComponentAtGrid(scrollPane, new Point(0, 2), new Dimension(1,1), new Point2D.Double(1.0, 1.0), scrollPaneAlignment, true);
	}
	
	private void initRightPanel() {
		_rightPanel = new GridBagPanel();
		int panelWidth = Constants.PRIMARY_FRAME_WIDTH / 2;
		int panelHeight = Constants.PRIMARY_FRAME_HEIGHT;
		_rightPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		_rightPanel.setBorder(BorderFactory.createMatteBorder(10, 5, 10, 10, Color.BLUE));
		
		JLabel rightTitle = new JLabel("Results");
		int width = Constants.PRIMARY_FRAME_WIDTH / 4;
		int height = Constants.PRIMARY_FRAME_HEIGHT / 20;
		rightTitle.setPreferredSize(new Dimension(width, height));
		int fontSize = 32;
		rightTitle.setFont(new Font("Verdana", Font.BOLD, fontSize));
		rightTitle.setForeground(Color.BLACK);
		rightTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		int alignment = GridBagConstraints.LAST_LINE_START;
		_rightPanel.addComponentAtGrid(rightTitle, new Point(0, 0), new Dimension(1, 1), new Point2D.Double(1.0, 0.0), alignment, false);
		
		GridBagPanel testPanel = new GridBagPanel();
		testPanel.setBackground(Color.BLACK);
		QueryForm queryForm = new QueryForm();
		testPanel.addComponentAtGrid(queryForm, new Point(0,0), new Dimension(1,1), new Point2D.Double(1.0, 0.0), GridBagConstraints.FIRST_LINE_START, false);
		
		JScrollPane scrollPane = new JScrollPane(testPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		int scrollPaneAlignment = GridBagConstraints.FIRST_LINE_START;
		_rightPanel.addComponentAtGrid(scrollPane, new Point(0, 1), new Dimension(1,1), new Point2D.Double(1.0, 1.0), scrollPaneAlignment, true);
	}
	@Override
	public void displayScene() {
		// TODO Auto-generated method stub
		super.setVisible(true);
	}
	
	@Override
	public void hideScene() {
		super.setVisible(false);
	}
	
}
