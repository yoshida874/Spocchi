package controller.SendEvent;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.KensakuModel;
import model.dataManager.SearchSQL;
import view.KensakuView;

public class KensakuSend implements EventHandler<ActionEvent> {

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {
		SearchSQL ut = new SearchSQL();
		MenuItem item = (MenuItem) event.getSource();
		String[] foo = {"福島ホープ(開成山)","ファイヤーボンズ(開成山)","ユナイテッドFC(あづま陸上競技場)",
				"フリーブレイズ(磐梯熱海アイスアリーナ)"};

		String team_ID = null;

		for(int i=0; i < foo.length; i++){
			if(item.getText().equals(foo[i])){
				i+=1;
			 team_ID = new Integer(i).toString();
			}
		}
		KensakuModel fpvm = new KensakuModel();
		fpvm.setKensaku(team_ID);
		KensakuView qrv = new KensakuView();
		qrv.NextView(fpvm);
	}

}
