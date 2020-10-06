package model.Map;

import java.util.ArrayList;
import java.util.HashMap;

import model.dataManager.AccessManager;

public class TeamMapDB extends AccessManager {
	public TeamMapDB(){
		super("sports.accdb");
	}

/*************************
 *  XとYの値を持ってくる
 *************************/

	public ArrayList<HashMap<String,String>> getmap_serch(String i_Team){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder(" SELECT * FROM team_tbl ");
		sb.append(" WHERE teamID = ").append(i_Team);

		String[] dbColums = {"Keido","Ido","SisetuName"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}

}
