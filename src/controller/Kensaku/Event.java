package controller.Kensaku;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import model.KensakuModel;
import view.KensakuView;

public class Event implements EventHandler<ActionEvent> {
	ChoiceBox m_Team;
	ChoiceBox m_Type;
	ChoiceBox m_hyouka;


	public Event(ChoiceBox i_Team, ChoiceBox i_Type, ChoiceBox i_hyouka) {
		m_Team = i_Team;
		m_Type = i_Type;
		m_hyouka = i_hyouka;
	}

	/**
	 * ボタンクリック時の処理
	 */

	@Override
	public void handle(ActionEvent event) {

		KensakuModel fpvm = new KensakuModel();
		String str_team = (String) m_Team.getValue();
		String str_type = (String) m_Type.getValue();
		String switch_hyouka =(String) m_hyouka.getValue();


		//くそこーど
		//評価点に値が入っているか
		if(!(switch_hyouka == null)){
			fpvm.setflag(1);
			//Type判定
			if(!(str_type == null)){
				fpvm.setflag(3);
				if(str_type=="食事"){
					str_type = "IN";
					fpvm.setType(str_type);
						}
				else if(str_type=="観光"){
					str_type = "GO";
					fpvm.setType(str_type);
						}
				else if(str_type=="宿泊"){
					str_type = "HT";
					fpvm.setType(str_type);
				}
			}

		}else {
			//評価点に値が入っていない
			fpvm.setflag(0);
			if(!(str_type == null)){
				fpvm.setflag(2);
				if(str_type=="食事"){
					str_type = "IN";
					fpvm.setType(str_type);
						}
				else if(str_type=="観光"){
					str_type = "GO";
					fpvm.setType(str_type);
						}
				else if(str_type=="宿泊"){
					str_type = "HT";
					fpvm.setType(str_type);
				}
			}
		}




		if(str_team==null){
			str_team = fpvm.getKensaku();
		}
		else if(str_team=="福島ホープ"){
			str_team = "1";
		}
		else if(str_team=="ファイヤーボンズ"){
			str_team = "2";
		}
		else if(str_team=="ユナイデットFC"){
			str_team = "3";
		}else str_team = "4";

		//遷移パラメータ
		fpvm.setKensaku(str_team);
		KensakuView qrv = new KensakuView();

		qrv.NextView(fpvm);
		}
}