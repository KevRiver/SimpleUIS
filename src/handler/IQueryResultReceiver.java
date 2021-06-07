package handler;

import java.sql.*;
import java.util.*;

public interface IQueryResultReceiver {
	public void register();
	public void deregister();
	public void onQueryResultCallback(String errMessage, List<Map<String,Object>> resultData, ResultTableType type);
}
