package model.dataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamSQL extends AccessManager {
	public TeamSQL(){
		super("sports.accdb");
	}
	//該当するチームを出力
	public ArrayList<HashMap<String,String>> Team_Info(String m_Team){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT * FROM team_tbl");
		sb.append(" WHERE teamID = ");
		sb.append(m_Team); //文字結合

		String[] dbColums = {"teamID","teamName","teamreport","imgpass"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}

	//イベント文を掲載
	public ArrayList<HashMap<String,String>> Team_Event(String m_Team){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT EventBun,Link FROM Event_tbl");
		sb.append(" WHERE teamID = ");
		sb.append(m_Team);
		String[] dbColums = {"EventBun","Link"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}

	//カレンダーに情報を入れる
	public ArrayList<HashMap<String,String>> Karenda (String m_Team){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT * FROM Calender_tbl");
		sb.append(" WHERE teamID = ");
		sb.append(m_Team);
		String[] dbColums = {"Day","time","color"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}

}
