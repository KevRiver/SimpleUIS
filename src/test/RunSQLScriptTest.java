package test;

import query.sender.QuerySender;

public class RunSQLScriptTest {

	public static void main(String[] args) {
		QuerySender querySender = QuerySender.getInstance();
		SimpleQueryResultReceiver receiver = new SimpleQueryResultReceiver();
		
		querySender.executeSQLFile("SQLFile/initializeDB.sql");
		
		String queryString = "SELECT * FROM Professor";
		querySender.executeQueryStringAndBroadcastResult("testTitle", queryString, null);

	}

}
