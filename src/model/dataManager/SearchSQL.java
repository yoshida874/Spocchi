package model.dataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchSQL extends AccessManager {
		public SearchSQL(){
			super("sports.accdb");
		}
		private static final String AVG = ",AVG(hyoukaten) ";
		private static final String AVG2 = " ORDER BY AVG(hyoukaten) desc";
		private static final String COUNT = " COUNT(kutikomiID) ";


		public ArrayList<HashMap<String,String>>  search(String m_Team){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT sisetu_tbl.sisetuname,sisetu_tbl.title,sisetu_tbl.imgpass,"
					+ "eigyoutime,teikyuday");
			sb.append(AVG);
			sb.append(" FROM sisetu_tbl,kutikomi_tbl,team_tbl");
			sb.append(" WHERE team_tbl.teamID = ");
			sb.append(m_Team);
			sb.append(" and sisetu_tbl.KensakuID = team_tbl.KensakuID");
			sb.append(" and sisetu_tbl.sisetuID = kutikomi_tbl.sisetuID");
			sb.append(" GROUP BY sisetu_tbl.sisetuname ");

			//文字結合

			String[] dbColums = {"sisetuName","title","imgpass","eigyoutime","teikyuday","AVG(hyoukaten)"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}

		//平均評価が高い順
		public ArrayList<HashMap<String,String>> AVG_search(String m_Team){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT sisetu_tbl.sisetuname,sisetu_tbl.title,sisetu_tbl.imgpass,"
					+ "eigyoutime,teikyuday");
			sb.append(AVG);
			sb.append(" FROM sisetu_tbl,kutikomi_tbl,team_tbl");
			sb.append(" WHERE team_tbl.teamID = " );
			sb.append(m_Team);
			sb.append(" and sisetu_tbl.KensakuID = team_tbl.KensakuID");
			sb.append(" and sisetu_tbl.sisetuID = kutikomi_tbl.sisetuID");
			sb.append(" GROUP BY sisetu_tbl.sisetuname ");
			sb.append(AVG2);

			//文字結合

			String[] dbColums = {"sisetuName","title","imgpass","eigyoutime","teikyuday","AVG(hyoukaten)"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}



		//ジャンル選択
		public ArrayList<HashMap<String,String>>  type_search(String m_Team,String m_Type){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT sisetu_tbl.sisetuname,sisetu_tbl.title,sisetu_tbl.imgpass,"
					+ "eigyoutime,teikyuday,sisetu_tbl.sisetuType");
			sb.append(AVG);
			sb.append(" FROM sisetu_tbl,kutikomi_tbl,team_tbl");
			sb.append(" WHERE team_tbl.teamID = ");
			sb.append(m_Team);
			sb.append(" and sisetu_tbl.KensakuID = team_tbl.KensakuID");
			sb.append(" and sisetu_tbl.sisetuID = kutikomi_tbl.sisetuID");
			sb.append(" and sisetu_tbl.sisetuType = '");
			sb.append(m_Type);
			sb.append("' GROUP BY sisetu_tbl.sisetuname ");


			//文字結合
			String[] dbColums = {"sisetuName","title","imgpass","eigyoutime","teikyuday","AVG(hyoukaten)"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}


		//すべて選択された場合
		public ArrayList<HashMap<String,String>>  all_search(String m_Team,String m_Type){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT sisetu_tbl.sisetuname,sisetu_tbl.title,sisetu_tbl.imgpass,"
					+ "eigyoutime,teikyuday,sisetu_tbl.sisetuType");
			sb.append(AVG);
			sb.append(" FROM sisetu_tbl,kutikomi_tbl,team_tbl");
			sb.append(" WHERE team_tbl.teamID = ");
			sb.append(m_Team);
			sb.append(" and sisetu_tbl.KensakuID = team_tbl.KensakuID");
			sb.append(" and sisetu_tbl.sisetuID = kutikomi_tbl.sisetuID");
			sb.append(" and sisetu_tbl.sisetuType = '");
			sb.append(m_Type);
			sb.append("' GROUP BY sisetu_tbl.sisetuname ");
			sb.append(AVG2);


			//文字結合

			String[] dbColums = {"sisetuName","title","imgpass","eigyoutime","teikyuday","AVG(hyoukaten)"};

			retData = select(sb.toString(),dbColums);

			return retData;
		}


		//施設IDをスポット画面に送る
		public String spot_send(String m_sisetu){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT * FROM sisetu_tbl");
			sb.append(" WHERE sisetuName = '");
			sb.append(m_sisetu);
			sb.append("'");

			String[] dbColums = {"sisetuID"};

			retData = select(sb.toString(),dbColums);

			return retData.get(0).get("sisetuID");
		}

		//使われていない
		//施設情報を抜き出す
		public ArrayList<HashMap<String,String>> sisetu_Info(String m_sisetuID){
			ArrayList<HashMap<String,String>> retData;
			StringBuilder sb = new StringBuilder("SELECT * FROM sisetu_tbl");
			sb.append(" WHERE sisetuID = ");
			sb.append(m_sisetuID);

			String[] dbColumns = {"sisetuName"};

			retData = select(sb.toString(),dbColumns);

			return retData;
		}
}
