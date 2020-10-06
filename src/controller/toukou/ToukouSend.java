package controller.toukou;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.ToukouModel;
import view.ToukouView;

public class ToukouSend implements EventHandler<ActionEvent> {
	String m_ID;

	public ToukouSend(String i_ID){
		m_ID = i_ID;
	}

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {

		ToukouModel fpvm = new ToukouModel();
		fpvm.setSisetuID(m_ID);
		ToukouView fpv = new ToukouView();
		fpv.NextView(fpvm);

	}
}
