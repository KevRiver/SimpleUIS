
package scene.model;

import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import query.receiver.*;
import query.sender.*;

public class QueryResultBoard extends VerticalBoxList implements IQueryResultReceiver, ActionListener{
	private JButton _refreshBoardButton;
	
	public QueryResultBoard() {
		register();
		_refreshBoardButton = new JButton("Refresh");
	}
	
	@Override
	public void register() {
		QuerySender.getInstance().addSubscriber(this);
		
	}

	@Override
	public void deregister() {
		QuerySender.getInstance().removeSubscriber(this);
		
	}

	@Override
	public void onQueryResultCallback(String errMessage, String title, List<Map<String, Object>> resultData, ResultTableType type) {
		QueryResultTab tab = new QueryResultTab(errMessage, title, resultData, type);
		addItem(tab);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == _refreshBoardButton) {
			removeAllItemsAndRefresh();
		}
		
	}
	
	

}
