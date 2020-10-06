package view;


import controller.SendEvent.BackSend;
import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.TeamSend;
import controller.toukou.ImgEvent;
import controller.toukou.ToukouEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.ToukouModel;

public class ToukouView extends BaseView<GridPane> {
	/**
	 * コンストラクタ
	 */
	public ToukouView(){
		super("Toukou.fxml");
	}

	/**
	 * 初期化処理
	 */
	/* (非 Javadoc)
	 * @see view.BaseView#Init()
	 */
	public void Init() {
		ChoiceBox<String> cb = (ChoiceBox<String>)getObject("hyoukaten");
		for(int i=1; i<=5; i++){
			String strsh = String.valueOf(i);
			cb.getItems().add(strsh);
		}
		//FX:idにmenuBarとつけること!
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

	     homeMenu.getItems().addAll(home,line);
		 teamMn.getItems().addAll(Base, Baske, soccer, hocke);
		 spotMn.getItems().addAll(Ba, Su, So, Ho);
		 //メニューバーの上に表示される変数宣言
		 mb.getMenus().clear();
		 mb.getMenus().addAll(homeMenu, teamMn, spotMn);

		 //Sisetu_IDを取得
		 Label ltext = (Label)getObject("lbl");
		 ToukouModel Model = (ToukouModel)getParam();
		 String ID = Model.getSisetuID();


        //データベースに値を入れる
		Button Back = (Button)getObject("BackSend");
        Button sendButton = (Button)getObject("SendButton");
        TextField title = (TextField)getObject("title");
        TextField review_bun = (TextField)getObject("Review_bun");
        Label review_img = (Label)getObject("imglbl");
        TextField user_name = (TextField)getObject("input_name");
        ImageView imgView = (ImageView)getObject("imgview");

        //ファイルを開く
        Button btn = (Button)getObject("btn");
		btn.setOnAction(new ImgEvent(imgView));

		String bbb = null;


        sendButton.setOnAction(new ToukouEvent(ID,cb,title,review_bun,bbb,user_name));

        //画面遷移
        home.setOnAction(new HomeSend());
	    line.setOnAction(new LineSend());
        //Button backButton = (Button)getObject("BackSend");
        //backButton.setOnAction(new SpotSend());
        Base.setOnAction(new TeamSend());
        Baske.setOnAction(new TeamSend());
        soccer.setOnAction(new TeamSend());
        hocke.setOnAction(new TeamSend());
        Ba.setOnAction(new KensakuSend());
        Su.setOnAction(new KensakuSend());
		So.setOnAction(new KensakuSend());
		Ho.setOnAction(new KensakuSend());

		Back.setOnAction(new BackSend(ID));
		}


	}