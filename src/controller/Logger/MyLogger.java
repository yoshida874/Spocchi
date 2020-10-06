/*************************************************
 * ファイル名	: MyLogger.java
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
package controller.Logger;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**********************************************************************
 * ログ管理クラス
**********************************************************************/
public class MyLogger extends Logger {

	//変数
	static String m_LastWaiterTime ; //最後にログ出力した時間
	static final String XML_FILENAME = "log4j.xml" ;
	static boolean m_initFlg = false ;

	/******************************************************************
	 * コンストラクタ
	 *****************************************************************/
	public MyLogger() {
		super("");
		init();
	}

	/******************************************************************
	 * 初期化
	 *****************************************************************/
	public static void init(){
		URL url = Thread.currentThread().getContextClassLoader().getResource(XML_FILENAME);
		setLogFileName() ;
		DOMConfigurator.configure(url);
		m_initFlg = true ;
	}

	/******************************************************************
	 * ログ出力するファイル名を決定する
	 *****************************************************************/
	public static void setLogFileName(){
		 //カレンダーを生成
        Calendar cal = Calendar.getInstance();
      //フォーマットを設定して出力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        m_LastWaiterTime = sdf.format(cal.getTime());
		System.setProperty("today", "/log_" + m_LastWaiterTime );
	}

	/******************************************************************
	 * ログファイル名を変更するタイミングを確認
	 * @return ログファイル名を変更するならtrue,しないならfalse
	 *****************************************************************/
	public static boolean isLogFileReName(){

        Calendar cal = Calendar.getInstance();      //フォーマットを設定して出力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String nowDate = sdf.format(cal.getTime());
        if(m_LastWaiterTime != null && !m_LastWaiterTime.equals(nowDate)){
        	return true ;
        }
		return false ;
	}

	/******************************************************************
	 * ロガーのインスタンスを生成する
	 * @param  i_nowClass	//ログを出力するクラス
	 * @return ロガーのインスタンス
	 *****************************************************************/
	public static Logger getMyInstance(Class i_nowClass){

		try{
			//初期化がされていなかったら
			if(!m_initFlg){
				init() ;
			}

			//ログファイル名の変更があるか確認
			if(isLogFileReName()){
				setLogFileName();
			}

			return getLogger(i_nowClass);

		}catch( Exception e){
			//ログの作成を失敗した場合nullを返す
			return null ;
		}

	}

}
