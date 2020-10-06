package controller.SendEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SisetuIDModel;
import view.SpotView;

public class BackSend implements EventHandler<ActionEvent> {
	String m_ID;


	public BackSend(String i_ID) {
		// TODO 自動生成されたコンストラクター・スタブ
		m_ID = i_ID;

	}

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {


		//最初の画面にページ遷移(戻る)
		SpotView fpv = new SpotView();
		SisetuIDModel model = new SisetuIDModel();
		model.setSisetuID(m_ID);
		fpv.NextView(model);

	}

}