//DataOnce.fxmlを作成して検索画面に表示する
package model;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class FxmlController<T extends Pane> {

	private Map<String,Node> m_objectList ;//オブジェクトの配列
	private final String PAGE_FILENAME ;	//fxmlのファイル名
	private T m_pane ;

	/***********************************
	 * コンストラクタ
	 * @param i_fileName //ファイル名
	 ***********************************/
	public FxmlController(String i_fileName ){
		PAGE_FILENAME = i_fileName ;
		m_objectList = new HashMap<String,Node>();
	}

	public void FxmlInit() {

		try {
		//ページを作成
		m_pane =  FXMLLoader.load(getClass().getResource( "../fxml/" + PAGE_FILENAME));

		//オブジェクトの取得
		ObjectSet(m_pane.getChildren());
		}catch(Exception e) {
			System.out.println("FxmlController:FxmlInit：エラー");
			e.printStackTrace();
		}

	}


	/***********************************
	 * ページの配置オブジェクトを取得する
	 * @param i_data	//ページのオブジェクト配列を取得(ページ変数.getChildren())
	 **********************************/
	public void ObjectSet( ObservableList<Node> i_data) {
		//データを配置
		for( Node object : i_data) {
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
}
