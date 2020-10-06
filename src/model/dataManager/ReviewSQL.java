package model.dataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewSQL extends AccessManager {
	public ReviewSQL(){
		super("sports.accdb");
	}

	public  ArrayList<HashMap<String,String>> Review_search(String m_SisetuID){

		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT * FROM kutikomi_tbl,sisetu_tbl");
		sb.append(" WHERE sisetu_tbl.sisetuID = ");
		sb.append(m_SisetuID);
		sb.append(" and kutikomi_tbl.sisetuID = ");
		sb.append(m_SisetuID);
		String[] dbColums = {"sisetuName","bun","kutikomi_tbl.imgpass","username","kutikomi_tbl.title","hyoukaten"};

		retData = select(sb.toString(),dbColums);

		return retData;

	}

	public ArrayList<HashMap<String,String>> Info_search(String m_SisetuID){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT * FROM sisetu_tbl");
		sb.append(" WHERE sisetuID = ");
		sb.append(m_SisetuID);
		String[] dbColums = {"title","teikyuday","eigyoutime","sisetuName"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}
}