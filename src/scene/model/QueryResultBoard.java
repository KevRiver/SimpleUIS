
package scene.model;

import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import query.receiver.*;
import query.sender.*;

public class QueryResultBoard extends VerticalBoxList implements IQueryResultReceiver{
	
	public QueryResultBoard() {
		register();
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
	
	public void refreshBoard() {
		removeAllItemsAndRefresh();
	}

}
