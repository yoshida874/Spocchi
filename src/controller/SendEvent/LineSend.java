package controller.SendEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.LineQRView;

public class LineSend implements EventHandler<ActionEvent> {

	/**
	 * ボタンクリック時の処理
	 */

	@Override
	public void handle(ActionEvent event) {

		//LineQRView(QRコード画面)にページ遷移
		LineQRView qrv = new LineQRView();
		qrv.NextView(null);

	}




}
