package controller.SendEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.HomePageView;

public class HomeSend implements EventHandler<ActionEvent> {



	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {
		//最初の画面にページ遷移(戻る)
		HomePageView fpv = new HomePageView();
		fpv.NextView(null);

	}

}
