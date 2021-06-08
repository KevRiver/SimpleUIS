package handler;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;

public class SimpleQueryResultReceiver implements IQueryResultReceiver{
	
	public SimpleQueryResultReceiver(){
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
	public void onQueryResultCallback(String errMessage, String queryTitle, List<Map<String, Object>> resultData, ResultTableType type) {
		if(!errMessage.isEmpty()) {
			System.out.println(errMessage);
			return;
		}
		
		int rowCount = resultData.size();
		Map<String, Object> firstRow = resultData.get(0);
		List<String> columnNames = firstRow.keySet()
				.stream()
				.sorted((k1, k2) -> ((Integer)firstRow.get(k1)).compareTo(((Integer)firstRow.get(k2))))
				.collect(Collectors.toList());
		
		for(var columnName: columnNames) {
			System.out.print(columnName + "\t");
		}
		System.out.print("\n");
		
		for(int i = 1; i < rowCount; i++) {
			for(var columnName: columnNames) {
				Object data = resultData.get(i).get(columnName);
				if (data == null)
					System.out.print("" + "\t");
				else
					System.out.print(data.toString() + "\t");
			}
			System.out.print("\n");
		}
	}	
}
