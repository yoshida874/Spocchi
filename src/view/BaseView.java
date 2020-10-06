/*************************************************
 * ファイル名	: BaseVew.java
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


package view;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.BaseModel;

/***********************************
 * ビューのベースとなるクラス
 ***********************************/
public abstract class BaseView<T extends Pane> {
	static private Stage	STAGE		;	//ステージクラス
	private BaseModel m_SendParam		 ;	//画面遷移時のパラメータ引き渡し用クラス
	private Map<String,Node> m_objectList ;//オブジェクトの配列
	private final String PAGE_FILENAME ;	//fxmlのファイル名
	private T m_pane ;						//ページのデータ
	/***********************************
	 * コンストラクタ
	 * @param i_fileName //ファイル名
	 ***********************************/
	public BaseView(String i_fileName ){
		PAGE_FILENAME = i_fileName ;
		m_objectList = new HashMap<String,Node>();
	}

	/***********************************
	 * ステージを設定する
	 * @param i_STAGE
	 ***********************************/
	static public void setSTAGE(Stage i_STAGE){
		STAGE=i_STAGE;
	}

	/***********************************
	 * ステージを返す
	 ***********************************/
	static public Stage getSTAGE(){
		return STAGE;
	}

	/***********************************
	 * 遷移時のパラメータを渡す
	 * @return m_SendParam
	 ***********************************/
	public BaseModel getParam(){
		return m_SendParam;
	}

	/***********************************
	 * ページ遷移処理
	 * @param i_Model //引き渡しをしたいデータ
	 ***********************************/
	public void NextView( BaseModel i_Data){
		try {

			//データを設定
			m_SendParam = i_Data ;

			//ページを作成
			m_pane =  FXMLLoader.load(getClass().getResource( "../fxml/" + PAGE_FILENAME));

			//Sceneの設定
			Scene scene = new Scene(m_pane, 1000, 600);
			//scene.getStylesheets().add("CSS/Sample.css");
			STAGE.setTitle("Sport");
			STAGE.setScene(scene);
			STAGE.show();

			//オブジェクトの取得
			ObjectSet(m_pane.getChildren());

			//初期化処理
			Init();

		} catch(Exception e) {
			System.out.println("BaseView:NextView() 例外発生");
			e.printStackTrace();
		}
	}

	/***********************************
	 * ページの配置オブジェクトを取得する
	 * @param i_date	//ページのオブジェクト配列を取得(ページ変数.getChildren())
	 **********************************/
	public void ObjectSet( ObservableList<Node> i_date) {
		//データを配置
		for( Node object : i_date) {
			m_objectList.put(object.getId(), object);
		}
	}

	/***********************************
	 * 配置オブジェクトを取得
	 * @param i_objectName	//fx:idの名前を指定
	 * @return 指定のオブジェクトを取得 取得できなければnullが返る
	 **********************************/
	public Node getObject(String i_objectName) {
		return m_objectList.get(i_objectName) ;
	}

	/***********************************
	 * ページのインスタンスを渡す
	 * @return
	 **********************************/
	public T getPane() {
		return m_pane ;
	}

	/***********************************
	 * 初期化処理
	 **********************************/
	 abstract public void Init();

}
