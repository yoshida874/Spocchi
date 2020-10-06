package view;

import java.nio.file.Paths;

import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.TeamSend;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LineQRView extends BaseView<GridPane> {

	public LineQRView(){
		super("LineQR.fxml");
	}

	public void Init(){

		//FX:idにmenuBarとつけること!
		 MenuBar mb = (MenuBar)getObject("menuBar");        //上記表示項目
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
	     homeMenu.getItems().addAll(home,line);
		 teamMn.getItems().addAll(Base, Baske, soccer, hocke);
		 spotMn.getItems().addAll(Ba, Su, So, Ho);

		 //メニューバーの上に表示される変数宣言
		 mb.getMenus().clear();
		 mb.getMenus().addAll(homeMenu, teamMn, spotMn);

		 //画面遷移
		 Button backbtn = (Button)getObject("BackButton");
		 home.setOnAction(new HomeSend());
	     line.setOnAction(new LineSend());
	     Base.setOnAction(new TeamSend());
	     Baske.setOnAction(new TeamSend());
	     soccer.setOnAction(new TeamSend());
	     hocke.setOnAction(new TeamSend());
	     Ba.setOnAction(new KensakuSend());
	     Su.setOnAction(new KensakuSend());
	     So.setOnAction(new KensakuSend());
	     Ho.setOnAction(new KensakuSend());

	     ImageView img = (ImageView)getObject("LineImage");
		 StringBuilder FilePath = new StringBuilder("media/image/L.png");
	     Image image = new Image( Paths.get(FilePath.toString()).toUri().toString());
	     img.setImage(image);
	}




}