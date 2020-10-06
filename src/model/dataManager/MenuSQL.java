package model.dataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuSQL extends AccessManager {
	public MenuSQL(){
		super("sports.accdb");
	}

	//イベント文を掲載
		public ArrayList<HashMap<String,String>> Team_Event(){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT EventBun,Link,Day FROM Event_tbl");
			sb.append(" ORDER BY Day desc");
			String[] dbColums = {"EventBun","Link"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}
}