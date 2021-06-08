package query.receiver;

import java.sql.*;
import java.util.*;

import scene.model.ResultTableType;

public interface IQueryResultReceiver {
	public void register();
	public void deregister();
	public void onQueryResultCallback(String errMessage, String queryTitle, List<Map<String,Object>> resultData, ResultTableType type);
}
