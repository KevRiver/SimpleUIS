package scene;

import java.util.*;
import java.util.List;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.*;

public class MainScene extends JFrame implements IScene{
	private VerticalBoxList _leftPanel;
	private JButton _backButton;
	private JPanel _rightPanel;
	private ArrayList<QueryForm> _queryForms;
	
	public MainScene() {
		super();
		
		super.setPreferredSize(new Dimension(Constants.PRIMARY_FRAME_WIDTH, Constants.PRIMARY_FRAME_HEIGHT));
		
		super.setLayout(new GridLayout(1, 2));
		
		initLeftPanelWithVList();
		
		initRightPanel();
		
		add(_leftPanel);
		add(_rightPanel);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		super.pack();
		
		super.setVisible(false);
	}

	private void initLeftPanelWithVList() {
		_leftPanel = new VerticalBoxList();
		_leftPanel.setCellPadding(5, 5);
		_leftPanel.setBackground(Color.WHITE);
		_leftPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 5, Color.BLUE));
		
		List<JComponent> leftPanelItems = new ArrayList<>();
		
		JButton backButton = new JButton("Home");
		int backBtnFontSize = 12;
		backButton.setFont(new Font("Consolas", Font.BOLD, backBtnFontSize));
		backButton.setOpaque(true);
		backButton.setBackground(Color.WHITE);
		backButton.setForeground(Color.BLACK);
		int backButtonWidth = Constants.PRIMARY_FRAME_WIDTH / 32;
		int backButtonHeight = Constants.PRIMARY_FRAME_HEIGHT / 24;
		backButton.setPreferredSize(new Dimension(backButtonWidth, backButtonHeight));
		leftPanelItems.add(backButton);
		
		JLabel leftTitle = new JLabel("Query Forms");
		int leftTitleWidth = Constants.PRIMARY_FRAME_WIDTH / 4;
		int leftTitleHeight = Constants.PRIMARY_FRAME_HEIGHT / 20;
		leftTitle.setPreferredSize(new Dimension(leftTitleWidth, leftTitleHeight));
		int fontSize = 32;
		leftTitle.setFont(new Font("Verdana", Font.BOLD, fontSize));
		leftTitle.setForeground(Color.BLACK);
		leftTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		leftPanelItems.add(leftTitle);
		
		
		List<JComponent> queryForms = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			queryForms.add(new QueryForm());
		}
		
		VerticalBoxList queryFormList = new VerticalBoxList();
		queryFormList.setCellPadding(0, 10);
		queryFormList.setBackground(Color.BLACK);
		queryFormList.initListWithItems(queryForms);
		
		JScrollPane scrollPane = new JScrollPane();
		int scrollPaneWidth = Constants.PRIMARY_FRAME_WIDTH / 2 - 55;
		int scrollPaneHeight = 565;
		scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
		scrollPane.setViewportView(queryFormList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		leftPanelItems.add(scrollPane);
		
		_leftPanel.initListWithItems(leftPanelItems);
	}
	
	private void initRightPanel() {
		_rightPanel = new GridBagPanel();
		int panelWidth = Constants.PRIMARY_FRAME_WIDTH / 2;
		int panelHeight = Constants.PRIMARY_FRAME_HEIGHT;
		_rightPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		((GridBagPanel)_rightPanel).setGridCellPadding(5, 5, 5, 5);
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
		((GridBagPanel)_rightPanel).addComponentAtGrid(rightTitle, new Point(0, 0), new Dimension(1, 1), new Point2D.Double(1.0, 0.0), alignment, GridBagConstraints.NONE);
		
		JPanel testPanel = new JPanel();
		testPanel.setBackground(Color.BLACK);
		
		JScrollPane scrollPane = new JScrollPane(testPanel);
		int scrollPaneWidth = Constants.PRIMARY_FRAME_WIDTH / 2 - 32;
		int scrollPaneHeight = 550;
		scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		int scrollPaneAlignment = GridBagConstraints.FIRST_LINE_START;
		((GridBagPanel)_rightPanel).addComponentAtGrid(scrollPane, new Point(0, 1), new Dimension(1,1), new Point2D.Double(1.0, 1.0), scrollPaneAlignment, GridBagConstraints.VERTICAL);
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
