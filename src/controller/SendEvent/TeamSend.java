package controller.SendEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.MenuModel;
import model.dataManager.TeamSQL;
import view.TeamView;

public class TeamSend implements EventHandler<ActionEvent> {



	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {
		TeamSQL ut = new TeamSQL();
		MenuItem item = (MenuItem) event.getSource();
		String[] foo = {"ファイヤーボンズ","福島ホープ","ユナイテッドFC","フリーブレイズ"};

		String team_ID = null;

		for(int i=0; i < foo.length; i++){
			if(item.getText().equals(foo[i])){
				i++;
			 team_ID = new Integer(i).toString();
			}
		}
		ut.Team_Info(team_ID);
		MenuModel fpvm = new MenuModel();
		fpvm.setUserName(team_ID);
		TeamView qrv = new TeamView();
		qrv.NextView(fpvm);
	}

}