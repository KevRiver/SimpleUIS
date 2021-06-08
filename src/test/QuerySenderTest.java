package test;

import java.util.*;

import qeury.result.mapper.*;

import java.sql.*;

import query.sender.QuerySender;

public class QuerySenderTest {
	
	public static void main(String[] args) {
		QuerySender querySender = QuerySender.getInstance();
		SimpleQueryResultReceiver receiver = new SimpleQueryResultReceiver();
		String queryString = "SELECT * FROM book";
		querySender.executeQueryStringAndBroadcastResult("testTitle", queryString, null);
	}

}
