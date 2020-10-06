package controller.SendEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import model.SisetuIDModel;
import model.dataManager.SearchSQL;
import view.SpotView;

public class SpotSend implements EventHandler<ActionEvent> {
	Hyperlink m_link;

	public SpotSend(Hyperlink i_link){
		m_link =i_link;
	}

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {
		SearchSQL se = new SearchSQL();

		//IDをモデルに渡す
		 String ID = se.spot_send(m_link.getText());

		SisetuIDModel fpvm = new SisetuIDModel();
		fpvm.setSisetuID(ID);
		SpotView fpv = new SpotView();
		fpv.NextView(fpvm);
	}

}

