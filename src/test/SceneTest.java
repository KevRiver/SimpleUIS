package test;

import handler.QueryConstants;
import query.sender.QuerySender;
import scene.MainScene;
import scene.model.ResultTableType;

public class SceneTest {

	public static void main(String[] args) {
		MainScene testScene = new MainScene();
		testScene.displayScene();
		QuerySender.getInstance().executeQueryStringAndBroadcastResult(QueryConstants.selectTable("Professor"), "Show Table",ResultTableType.TABLE);
	}

}
