/*************************************************
 * ファイル名	: DBManager.java
 * 作成者		: 村上聖矢
 * 制作日		: 2018/11/12
 * 最終更新者	: なし
 * 最終更新日	: なし
 *
 * 更新履歴
 * 名前			日付		内容
 * 村上聖矢		2018/11/12	新規作成
 *
 ************************************************/
package model.dataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**********************************************************************
 * データベースへの基本操作を行うクラス
**********************************************************************/
public class DBManager extends DataManager {

	private StringBuilder m_Url;	//接続URL
	private String m_User;			//接続ユーザ
	private String m_Pass;			//接続パスワード
	private Connection m_Conn ;	//データベース
	private Statement m_Stmt ;		//ステートメント

	/******************************************************************
	 * コンストラクタ
	 * @param i_HostName , 	//ホスト名(IPアドレス可)
	 * @param i_DBName , 	//使用するデータベース名
	 * @param i_User , 		//接続するmysqlユーザ
	 * @param i_Pass 		//接続するmysqlユーザのパスワード
	 *****************************************************************/
	protected DBManager(StringBuilder i_HostName , StringBuilder i_DBName , String i_User , String i_Pass )
	{
		String GetParameter = "?autoReconnect=true&useSSL=false" ;
		//m_Url = "jdbc:mysql://" + i_HostName +":3306/" + i_DBName + GetParameter ;
		m_Url = new StringBuilder("jdbc:mysql://");
		m_Url.append(i_HostName).append(":3306/").append(i_DBName).append(GetParameter);
		m_User = i_User ;
		m_Pass = i_Pass ;
		m_Conn = null ;
		m_Stmt = null ;
	}

	/******************************************************************
	 * selectのｓql文を実行する時に使用する
	 * @param i_SQL ,		//実行したいsql文
	 * @param i_Columns 	//取得するカラム名全て
	 * @return 結果を返却する HashMap<カラム名、データ>
	 *****************************************************************/
	@Override
	protected ArrayList<HashMap<String,String>> select(String i_SQL ,String[] i_Columns )
	{
		ResultSet rs = null ;
		ArrayList<HashMap<String,String>> SqlRecords = new ArrayList<HashMap<String,String>>() ;
		try{

			//DB接続
			connect();

			// SQLを実行して結果を取得
			rs = m_Stmt.executeQuery(i_SQL);

			// 取得した行数分ループ
			while (rs.next()) {
				HashMap<String,String> sqlRecord = new HashMap<String,String>() ;

				//列分ループ
				for( String Column : i_Columns){
					//カラム１つずつ追加
					sqlRecord.put(Column, rs.getString(Column));
					System.out.println(Column + ":" + rs.getString(Column));
				}
				//１レコード追加
				SqlRecords.add(sqlRecord);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				// close処理
				if (rs != null) {
					rs.close();
				}
				//メンバ変数のclose処理
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return SqlRecords ;
	}

	/******************************************************************
	 * データベース接続
	 * @return 成功ならtrue, 失敗ならfalse
	 *****************************************************************/
	@Override
	protected boolean connect() {
		try {
			// データベースへ接続
			m_Conn = DriverManager.getConnection(m_Url.toString(), m_User, m_Pass);
			// ステートメントオブジェクトを生成
			m_Stmt = m_Conn.createStatement();
			return true ;
		}catch (SQLException e) {
			e.printStackTrace();
			close();
			return false ;

		} catch (Exception e) {
			e.printStackTrace();
			close();
			return false ;
		}
	}

	/******************************************************************
	 * 用途   :データベース切断
	 *****************************************************************/
	protected void close(){
		try {

			// close処理
			if (m_Stmt != null) {
				m_Stmt.close();
				m_Stmt = null ;
			}

			// close処理
			if (m_Conn != null) {
				m_Conn.close();
				m_Conn = null ;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean insert(String i_SQL) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
