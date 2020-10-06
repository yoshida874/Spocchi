/*************************************************
 * ファイル名	: AccessManager.java
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

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**********************************************************************
 * Accessデータベースへの基本操作を行うクラス
**********************************************************************/
public class AccessManager extends DataManager {

	private String m_ConnectStr ;	//接続文字列
	private java.sql.Statement m_Stmt ;
	private java.sql.Connection m_Conn ;

	/******************************************************************
	 * コンストラクタ
	 * 	@param i_FileName	//MDBファイル名
	*****************************************************************/
	protected AccessManager( String i_FileName )
	{

		try{
			//ドライバ読み込み
			Class.forName("jstels.jdbc.mdb.MDBDriver2");
		}catch(Exception e){

		}
		//接続のドライバーを作成
		StringBuilder sbConnectStr = new StringBuilder("jdbc:jstels:mdb:") ;
		//アプリケーションのディレクトリのパスを取得
		String appDir = System.getProperty("user.dir");
		//アプリケーションからmdbファイルまでのパス
		String mdbPath = "\\media\\access\\" ;
		//文字結合
		m_ConnectStr = sbConnectStr.append(appDir).append(mdbPath).append(i_FileName).toString();


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
			boolean IsRun = connect() ;

			if(IsRun){
			    //クエリ実行
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
	protected boolean connect()
	{
		try{
			//接続
		    m_Conn = DriverManager.getConnection( m_ConnectStr );
		    //実行するためのステートメントオブジェクトを作成する。
		    m_Stmt = m_Conn.createStatement();
		    return true;
		}catch(Exception e){
			return false ;
		}
	}

	/******************************************************************
	 * データベース切断
	 *****************************************************************/
	@Override
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
	/******************************************************************
	 * selectのｓql文を実行する時に使用する
	 * @param i_SQL ,		//実行したいsql文
	 * @return 成功 true , 失敗 false
	 *****************************************************************/
	protected boolean insert(String i_SQL) {
		try{

			//DB接続
			boolean IsRun = connect() ;

			if(IsRun){
			    //クエリ実行
			    m_Stmt.execute(i_SQL);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  finally {
			//メンバ変数のclose処理
			close();
		}
		return true ;

	}

}
