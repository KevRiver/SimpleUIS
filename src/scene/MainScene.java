package scene;

import java.util.*;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.*;

import scene.form.AdminInitDBForm;
import scene.form.AdminShowAllTables;
import scene.form.ProfessorForm3;
import scene.form.QueryForm;
import scene.model.QueryResultBoard;
import scene.model.VerticalBoxList;

public class MainScene extends JFrame implements IScene, ActionListener{
	private VerticalBoxList _leftPanel;
	private JButton _backButton;
	private VerticalBoxList _rightPanel;
	private QueryResultBoard _board;
	private JButton _resultBoardRefreshButton;
	
	private List<JComponent> _queryForms;
	
	class RefreshActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if((JButton)e.getSource() == _resultBoardRefreshButton) {
				_board.refreshBoard();
			}
			
		}
	}
	
	public MainScene() {
		super();
		
		super.setPreferredSize(new Dimension(Constants.PRIMARY_FRAME_WIDTH, Constants.PRIMARY_FRAME_HEIGHT));
		
		super.setLayout(new GridLayout(1, 2));
		
		setAdminQueryForms();
		
		initLeftPanelWithVList();
		
		initRightPanel();
		
		add(_leftPanel);
		add(_rightPanel);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		super.pack();
		
		super.setVisible(false);
	}
	
private void setAdminQueryForms() {
	_queryForms = new ArrayList<>();
	_queryForms.add(new AdminInitDBForm());
	_queryForms.add(new AdminShowAllTables());
}

private void setProfessorQueryForms() {
	_queryForms = new ArrayList<>();
	_queryForms.add(new ProfessorForm3());
}

private void setStudentQueryForms() {
	
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
		leftTitle.setFont(new Font("Consolas", Font.BOLD, fontSize));
		leftTitle.setForeground(Color.BLACK);
		leftTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		leftPanelItems.add(leftTitle);
		
		VerticalBoxList queryFormList = new VerticalBoxList();
		queryFormList.setCellPadding(0, 10);
		queryFormList.activateFiller(true);
		queryFormList.setBackground(Color.BLACK);
		queryFormList.initListWithItems(_queryForms);
		
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
		_rightPanel = new VerticalBoxList();
		_rightPanel.setCellPadding(5, 5);
		_rightPanel.setBackground(Color.WHITE);
		_rightPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 5, Color.BLUE));
				
		List<JComponent> rightPanelItems = new ArrayList<>();
		
		_resultBoardRefreshButton = new JButton("Refresh");
		_resultBoardRefreshButton.addActionListener(new RefreshActionListener());
		rightPanelItems.add(_resultBoardRefreshButton);
		
		JLabel rightTitle = new JLabel("Results Board");
		int width = Constants.PRIMARY_FRAME_WIDTH / 4;
		int height = Constants.PRIMARY_FRAME_HEIGHT / 20;
		rightTitle.setPreferredSize(new Dimension(width, height));
		int fontSize = 32;
		rightTitle.setFont(new Font("Consolas", Font.BOLD, fontSize));
		rightTitle.setForeground(Color.BLACK);
		rightTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		rightPanelItems.add(rightTitle);
		
		_board = new QueryResultBoard();
		_board.setOpaque(true);
		_board.setBackground(Color.BLACK);
		
		JScrollPane scrollPane = new JScrollPane(_board);
		int scrollPaneWidth = Constants.PRIMARY_FRAME_WIDTH / 2 - 32;
		int scrollPaneHeight = 550;
		scrollPane.setPreferredSize(new Dimension(scrollPaneWidth, scrollPaneHeight));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightPanelItems.add(scrollPane);
		
		_rightPanel.initListWithItems(rightPanelItems);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == _resultBoardRefreshButton) {
			_board.refreshBoard();
		}
		
	}
	
}
