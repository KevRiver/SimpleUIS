package test;

import java.util.*;
import java.sql.*;
import handler.*;

public class QuerySenderTest {
	
	public static void main(String[] args) {
		QuerySender querySender = QuerySender.getInstance();
		SimpleQueryResultReceiver receiver = new SimpleQueryResultReceiver();
		String queryString = "SELECT * FROM book";
		querySender.executeQueryStringAndBroadcastResult(queryString, null);
	}

}
