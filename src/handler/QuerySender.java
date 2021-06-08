package handler;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;



public class QuerySender {
	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "madang";
	private static final String DB_USER = "madang";
	private static final String DB_PASSWORD = "madang";
	
	private static QuerySender _instance = null;
	
	private Connection _connection;
	private boolean _isConnected;
	
	private ArrayList<IQueryResultReceiver> _subscribers;
	private ScriptRunner _sqlScriptRunner;
	
	private QuerySender() {
		_isConnected = false;
		try {
		tryToConnect();
		_isConnected = true;
		}
		catch(ClassNotFoundException e) {
			System.out.println("fail to load JDBC driver");
			System.out.println(e.getStackTrace());
		}
		catch(SQLException e) {
			System.out.println("fail to connect to database");
			System.out.println(e.getStackTrace());
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		finally {
			if(!_isConnected) {
				System.out.println("fail to initialize QuerySender");
				System.exit(1); // abnormal terminate
			}
			_subscribers = new ArrayList<>();
			_sqlScriptRunner = new ScriptRunner(_connection);
		}
	}
	
	private void tryToConnect() throws ClassNotFoundException, SQLException, Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			_connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?&serverTimezone=Asia/Seoul&useSSL=false", DB_USER, DB_PASSWORD);
			System.out.println("successful connection to database");
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC driver load failed");
			throw e;
		}
		catch(SQLException e) {
			System.out.println("fail to connect to database");
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public static QuerySender getInstance() {
		if(_instance == null) _instance = new QuerySender();
		return _instance;
	}
	
	public void addSubscriber(IQueryResultReceiver subscriber) {
		_subscribers.add(subscriber);
	}
	
	public void removeSubscriber(IQueryResultReceiver subscriber) {
		_subscribers.remove(subscriber);
	}
	
	public void clearSubscribers() {
		_subscribers.clear();
	}
	
	public void executeQueryStringAndBroadcastResult(String queryString, String queryTitle, ResultTableType type) {
		String errMessage = "";
		List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
		try{
			resultData = queryStringToResultData(queryString);
		}
		catch(SQLException e) {
			errMessage = e.getMessage();
		}
		finally {
			for(var subscriber: _subscribers) {
				subscriber.onQueryResultCallback(errMessage, queryTitle, resultData, type);
			}
		}
	}
	
	private List<Map<String, Object>> queryStringToResultData(String query) throws SQLException{
		try (
		        Statement statement = _connection.createStatement();
		        ResultSet resultSet = statement.executeQuery(query);
		    ) {
		        ResultSetMetaData metaData = resultSet.getMetaData();

		        List<Map<String, Object>> rows = new ArrayList<>();
		        Map<String, Object> firstRow = new LinkedHashMap<>();
		        
		        int columnCount = metaData.getColumnCount();
		        String[] columnNames = new String[columnCount];
		        for (int i = 0; i < columnCount; i++) {
		        	String columnName = metaData.getColumnLabel(i + 1);
		            columnNames[i] = columnName;
		            firstRow.put(columnName, Integer.valueOf(i + 1));
		        }
		        rows.add(firstRow);
		        
		        while (resultSet.next()) {
		            Map<String, Object> row = new LinkedHashMap<>();
		            for (int i = 0; i < columnCount; i++)
		                row.put(columnNames[i], resultSet.getObject(i + 1));
		            rows.add(row);
		        }
		        return rows;
		    } 
			catch (SQLException ex) {
		        System.out.println(ex.toString());
		        throw ex;
		    }
	}
	
	public void executeSQLFile(final String sqlFilePath) {
		try {
			ScriptRunner sqlScriptRunner = new ScriptRunner(_connection);
			Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFilePath), "utf-8"));
			sqlScriptRunner.runScript(reader);
		}
		catch(FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.toString());
		}
	}
	
}
