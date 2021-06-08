package scene.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class QueryResultTab extends VerticalBoxList {
	private CloseButton _closeButton;
	private JLabel _titleLabel;
	private JLabel _errMessageLabel;
	private QueryResultTable _resultTable;
	
	public QueryResultTab(String errMessage, String title, List<Map<String, Object>> results, ResultTableType type) {
		_closeButton = new CloseButton((VerticalBoxList)((JComponent)this).getParent(), (JComponent)this);
				
		_items.add(_closeButton);
		
		_titleLabel = new JLabel(title);
		_items.add(_titleLabel);
		
		if(!errMessage.isEmpty()) {
			_errMessageLabel = new JLabel(errMessage);
			_items.add(_errMessageLabel);
		}
		else {
			_resultTable = new QueryResultTable();
			try {
			_resultTable.initListWithQueryResult(results, type);
			_items.add(_resultTable);
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
		
		
	}
	
	
}
