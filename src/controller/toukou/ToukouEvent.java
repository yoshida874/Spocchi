package controller.toukou;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.ToukouModel;
import model.dataManager.ToukouSQL;

public class ToukouEvent implements EventHandler<ActionEvent>{
	String       m_sisetuID;
	ChoiceBox m_hyoukaten;
	TextField m_title;
	TextField m_bun;
	String m_img;
	TextField m_user;
	public static String pass_in;
	public static String pass_out;


	public ToukouEvent(String i_sisetuID,ChoiceBox i_hyoukaten,TextField i_title,TextField i_bun,String i_img,TextField i_user){
		m_sisetuID = i_sisetuID;
		m_hyoukaten = i_hyoukaten;
		m_title = i_title;
		m_bun = i_bun;
		m_img = i_img;
		m_user = i_user;
	}

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event){
			try {
		//ページ遷移

		ToukouSQL kutikomiTab = new ToukouSQL();
		//ChoiceBoxの値をString型に変換
		String str_hyouka = (String) m_hyoukaten.getValue();
		//TextFieldの値を変換
		String str_bun = m_bun.getText();
		String str_title = m_title.getText();


		//画像ファイルが挿入されていた場合
		if(pass_out != null){
			@SuppressWarnings("resource")
			FileChannel inCh;
			FileChannel outCh;
			try {
				//ファイルをコピーする
				inCh = new FileInputStream(pass_in).getChannel();
				outCh = new FileOutputStream(pass_out).getChannel();
				inCh.transferTo(0,inCh.size(),outCh);

			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


		//レビュー文と評価点がnullでないときページ遷移
		if(!(str_hyouka==null) && !(str_bun.equals("")) && !(str_title.equals(""))){
		kutikomiTab.addKuticomi(Integer.parseInt(m_sisetuID),Integer.parseInt(str_hyouka),
				m_title.getText(),str_bun, pass_out, m_user.getText());
		Alert alrt = new Alert(AlertType.INFORMATION);
		alrt.setTitle("投稿成功");
		alrt.setHeaderText("投稿が完了しました");
		alrt.setContentText("");//アラートを作成
		alrt.showAndWait(); //表示
		}
		else{
		Alert alrt = new Alert(AlertType.ERROR);
		alrt.setTitle("エラー");
		alrt.setHeaderText("投稿できません");
		alrt.setContentText("必要項目を入力して下さい");//アラートを作成
		alrt.showAndWait(); //表示
			}
				}catch (Exception e) {
						System.out.println("エラー：MainView::createPage()");
						e.printStackTrace();
		}
		}

	private ToukouModel getParam() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	}
