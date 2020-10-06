package view;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import controller.Kensaku.Event;
import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.SpotSend;
import controller.SendEvent.TeamSend;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.FxmlController;
import model.KensakuModel;
import model.dataManager.SearchSQL;

public class KensakuView extends BaseView<GridPane> {

	public KensakuView(){
		super("Kensaku.fxml");
	}

	public void Init(){

		MenuBar mb = (MenuBar)getObject("menuBar");

        //上記表示項目
        Menu homeMenu = new Menu("ホーム");
        Menu teamMn = new Menu("チーム紹介");
        Menu spotMn = new Menu("スポット一覧");

        //プルダウン項目
        MenuItem home = new MenuItem("トップページ");
        MenuItem line = new MenuItem("LINE追加");
        MenuItem Base = new MenuItem("福島ホープ");
        MenuItem Baske = new MenuItem("ファイヤーボンズ");
        MenuItem soccer = new MenuItem("ユナイテッドFC");
        MenuItem hocke = new MenuItem("フリーブレイズ");

        MenuItem Ba = new MenuItem("福島ホープ(開成山)");
        MenuItem Su = new MenuItem("ファイヤーボンズ(開成山)");
        MenuItem So = new MenuItem("ユナイテッドFC(あづま陸上競技場)");
        MenuItem Ho = new MenuItem("フリーブレイズ(磐梯熱海アイスアリーナ)");
        //プルダウン追加したいアイテムはここで宣言する
        mb.getMenus().clear();
        mb.getMenus().addAll(homeMenu, teamMn, spotMn);
        homeMenu.getItems().addAll(home,line);
        teamMn.getItems().addAll(Base, Baske, soccer, hocke);
        spotMn.getItems().addAll(Ba, Su, So, Ho);


        //検索条件の値
        ChoiceBox<String> team = (ChoiceBox<String>)getObject("team");
        team.getItems().add("福島ホープ");
        team.getItems().add("ファイヤーボンズ");
        team.getItems().add("ユナイデットFC");
        team.getItems().add("フリーブレイズ");


        ChoiceBox<String> sisetu = (ChoiceBox<String>)getObject("sisetu");
        sisetu.getItems().add("食事");
        sisetu.getItems().add("観光");
        sisetu.getItems().add("宿泊");

        ChoiceBox<String> hyouka = (ChoiceBox<String>)getObject("hyouka");
        hyouka.getItems().add("評価が高い順");

        //画面遷移
        Base.setOnAction(new TeamSend());
        Baske.setOnAction(new TeamSend());
        soccer.setOnAction(new TeamSend());
        hocke.setOnAction(new TeamSend());
        Ba.setOnAction(new KensakuSend());
        Su.setOnAction(new KensakuSend());
        So.setOnAction(new KensakuSend());
        Ho.setOnAction(new KensakuSend());
        home.setOnAction(new HomeSend());
        line.setOnAction(new LineSend());

        Button btn = (Button)getObject("btn");

        //検索
		btn.setOnAction(new Event(team,sisetu,hyouka));


		//ユーザ領域の取得
		ScrollPane sp = (ScrollPane)getObject("Scroll") ;
		VBox vbox = new VBox();
		sp.setContent(vbox);


		//前の画面のデータを取得する
		KensakuModel Ken= null;

		if(getParam() instanceof KensakuModel){
		Ken = (KensakuModel)getParam();
		SearchSQL ut = new SearchSQL();
		ArrayList<HashMap<String,String>> name = null;


		//SQL文の分岐
		if(Ken.getflag() == 1){
			name = ut.AVG_search(Ken.getKensaku());
		}else if(Ken.getflag()==2){
			name = ut.type_search(Ken.getKensaku(),Ken.getType());
		}else if(Ken.getflag()==3){
			name = ut.all_search(Ken.getKensaku(),Ken.getType());
		}
		else name = ut.search(Ken.getKensaku());


		//検索結果の表示
		for(int i=0 ; i<name.size(); i++){
			String LText = name.get(i).get("sisetuName");
			String hyoukaten = name.get(i).get("AVG(hyoukaten)");
			String title = name.get(i).get("title");
			String time = name.get(i).get("eigyoutime");

			//一つの施設領域の生成
			FxmlController<GridPane> fc = new FxmlController<GridPane>("DataOnce.fxml");
			fc.FxmlInit();
			Hyperlink Linkdata = (Hyperlink)fc.getObject("name");
			Label lbl_hyouka = (Label)fc.getObject("hyouka");
			Label lbl_title= (Label)fc.getObject("title");
			Label lbl_time = (Label)fc.getObject("time");

			//画像の設定
			ImageView img = (ImageView)fc.getObject("img");
		    StringBuilder FilePath = new StringBuilder(name.get(i).get("imgpass"));
		    Image image = new Image( Paths.get(FilePath.toString()).toUri().toString());
	     	img.setImage(image);


	     	lbl_title.setWrapText(true); //自動で改行する
	     	lbl_title.setFont(Font.font(16));	//フォントサイズの指定
	     	lbl_title.setMaxWidth(600);
	     	lbl_title.setMinWidth(600);


			Linkdata.setText(LText);
			lbl_hyouka.setText(hyoukaten);
			lbl_title.setText(title);
			lbl_time.setText(time);

			Linkdata.setOnAction(new SpotSend(Linkdata));
			vbox.getChildren().add(fc.getPane());

			}
		}
	}
}
