/*************************************************
 * ファイル名	: Main.java
 * 作成者		: 村上聖矢
 * 制作日		: 2018/11/10
 * 最終更新者	: なし
 * 最終更新日	: なし
 *
 * 更新履歴
 * 名前			日付		内容
 * 村上聖矢		2018/11/10	新規作成
 *
 ************************************************/

package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.BaseView;
import view.HomePageView;

/***********************************
 * 初期起動クラス
 ***********************************/
public class Main extends Application {

	/***********************************
	 * 画面の初期設定を行う
	 * @Param primaryStage
	 ***********************************/
	@Override
	public void start(Stage primaryStage) {
		try{
			BaseView.setSTAGE(primaryStage); //ステージを保持させる
			//最初のページの表示処理
			//BaseView bc = new BaseView();
			//bc.NextView( new BaseModel());
			HomePageView fpv = new HomePageView();
			fpv.NextView(null);
		}catch(Exception e){
			System.out.println("Main:start() 例外発生");
			e.printStackTrace();
		}
	}

	/***********************************
	 * 最初に呼ばれるメソッド
	 * @param args	//コマンドライン
	 ***********************************/
	public static void main(String[] args) {
		launch(args);
	}
}

