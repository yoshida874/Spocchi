package model.Map;

import java.util.ArrayList;
import java.util.HashMap;

import model.dataManager.AccessManager;

public class SpotMapDB extends AccessManager {
		public SpotMapDB(){
			super("sports.accdb");
		}

	/*************************
	 *  XとYの値を持ってくる
	 *************************/

		public ArrayList<HashMap<String,String>> getmap_serch(String i_Spot){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder(" SELECT * FROM sisetu_tbl ");
			sb.append(" WHERE sisetuID = ").append(i_Spot);

			String[] dbColums = {"Keido","Ido","sisetuName"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}

	}
