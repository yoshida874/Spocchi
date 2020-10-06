package model.dataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ToukouSQL extends AccessManager {
	public ToukouSQL(){
		super("sports.accdb");
	}
	public static final String SISETU = "sisetuID";
	public static final String HYOUKATEN = "hyoukaten" ;
	public static final String IMG 		= "imgpass" ;
	public static final String BUN        = "bun" ;
	public static final String USERNAME   = "username" ;
	public static final String TITLE      ="title";

	public ArrayList<HashMap<String,String>> selectAll(String i_ID){
		ArrayList<HashMap<String,String>> retData;
		StringBuilder sb = new StringBuilder("SELECT * FROM Event_tbl");
		sb.append(" WHERE EventID = ");
		sb.append(i_ID); //文字結合

		String[] dbColums = {"EventID","TeamID","EventBun"};

		retData = select(sb.toString(),dbColums);

		return retData;
	}



	public void addKuticomi(int m_sisetu, int m_hyoukaten,String m_title,String m_bun,String m_img,String m_user){
		StringBuilder Sql = new StringBuilder("INSERT INTO ").append("kutikomi_tbl").append("(");
		Sql.append(SISETU);
		Sql.append(",").append(HYOUKATEN);
		Sql.append(",").append(TITLE);
		Sql.append(",").append(BUN);
		Sql.append(",").append(IMG);
		Sql.append(",").append(USERNAME);
		Sql.append(")Values(") ;
		Sql.append(m_sisetu);
		Sql.append(",").append(m_hyoukaten);
		Sql.append(",'").append(m_title);
		Sql.append("','").append(m_bun);
		Sql.append("','").append(m_img);
		Sql.append("','").append(m_user);
		Sql.append("')");
		System.out.println(Sql.toString());
		insert(Sql.toString());

	}
}
