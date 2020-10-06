package view;



import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.LinkSend;
import controller.SendEvent.TeamSend;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.FxmlController;
import model.MapController;
import model.MenuModel;
import model.Map.TeamMapDB;
import model.dataManager.TeamSQL;

public class TeamView extends BaseView<GridPane>  {

	public TeamView(){
		super("Team.fxml");
	}

public void Init(){

	GridPane mainpane = (GridPane)getPane().lookup("#pane");
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
     homeMenu.getItems().addAll(home,line);
     teamMn.getItems().addAll(Base, Baske, soccer, hocke);
     spotMn.getItems().addAll(Ba, Su, So, Ho);
     //メニューバーの上に表示される変数宣言
     mb.getMenus().clear();
     mb.getMenus().addAll(homeMenu, teamMn, spotMn);

     //メニューバーの画面遷移
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



	if(getParam() instanceof MenuModel){
		MenuModel Menu = (MenuModel)getParam();
		TeamSQL ut = new TeamSQL();


		//紹介文を取得
		ArrayList<HashMap<String,String>> syoukai;
		syoukai = ut.Team_Info(Menu.getMenu());
		String syoukai_bun = syoukai.get(0).get("teamreport");
		String Title = syoukai.get(0).get("teamName");
		//scrollPaneの階層の下を取得
		Label txt = (Label) getObject("team");
		Label title = (Label)getObject("title");

		title.setText(Title);

		txt.setText(syoukai_bun);


		//ラベルの書式設定
		txt.setWrapText(true); //自動で改行する
		txt.setFont(Font.font(14));	//フォントサイズの指定
		txt.setMaxWidth(500);
		txt.setMinWidth(500);





	     //カレンダーの設定
	     DatePicker vitamin = new DatePicker(LocalDate.now());

	     ArrayList<HashMap<String,String>> date;
	     date = ut.Karenda(Menu.getMenu());
	     Map<String, String> map = new HashMap();
	     //くそコード　訂正したい
	     Map<String,String> zio = new HashMap();

	     //くそこーどその２
	     for(int i=0 ; i<date.size(); i++){
	    	 map.put(date.get(i).get("Day"),date.get(i).get("time"));
	    	 zio.put(date.get(i).get("Day"),date.get(i).get("color"));
	     }

	     final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
	         @Override
	         public DateCell call(final DatePicker datePicker)
	         {
	             return new DateCell() {
	                 @Override
	                 public void updateItem(LocalDate item, boolean empty)
	                 {
	                     super.updateItem(item, empty);

	                     int you = 0;
	                     for (Map.Entry<String, String> entry : map.entrySet()) {
	                         if (item.toString().equals(entry.getKey())) {

	                        	 String time =date.get(you).get("color");

	                        	 if("2".equals(zio.get(item.toString()))){
		                         	setStyle("-fx-background-color: #ff4444;");
		                            setTooltip(new Tooltip(map.get(item.toString())));
	                        	 }
	                        	 else{
	                        		 setStyle("-fx-background-color: #ffff00;");
	                        	 }
	                         }
	                         you++;
	                     }
	                 }
	             };
	         }
	     };

	     vitamin.setDayCellFactory(dayCellFactory);

	     DatePickerSkin skin = new DatePickerSkin(vitamin);
	     Node calendar = skin.getPopupContent();
	     //カレンダーの位置調整
	     //DatePickerについて検索しておく
	     mainpane.setConstraints(calendar,2,3);
	     mainpane.getChildren().add(calendar);


		//画像の設定
		ImageView img = (ImageView)getObject("img");
		StringBuilder FilePath = new StringBuilder(syoukai.get(0).get("imgpass"));
		Image image = new Image( Paths.get(FilePath.toString()).toUri().toString());
		img.setImage(image);


		//MAP表示
		TeamMapDB md = new TeamMapDB();
		 ArrayList<HashMap<String,String>> i_do = null; //i_do初期化
		 String keido ,ido; //ido,keidoをstring型に

		 i_do = md.getmap_serch(Menu.getMenu());
		 ido = i_do.get(0).get("Ido");
		 keido= i_do.get(0).get("Keido");

		 //String型からdouble型へ型変換
		 double d_ido = Double.parseDouble(ido);
		 double d_keido = Double.parseDouble(keido);

		MapController mc = new MapController(d_ido, d_keido);
		 //教科書P104
		 //GridPaneに直接GoogleMapを入れる。ここでgetPaneでエラー出たら↑の<Pane>をGridPaneにしてください
		 GridPane gp = getPane(); //GridPane取得

		//GridPane.setConstraints(node,X(縦),Y(横)); <- GridPaneの座標
		 GridPane.setConstraints(mc.getGoogleMapView(),2,1);
		 gp.getChildren().add(mc.getGoogleMapView());



		//イベント文の取得
		ArrayList<HashMap<String,String>> event;

		//ユーザ領域の取得
		ScrollPane sp = (ScrollPane)getObject("news") ;
		VBox vbox = new VBox();
		sp.setContent(vbox);


		//一つの施設領域の生成
		event = ut.Team_Event(syoukai.get(0).get("teamID"));

		for(int i=0 ; i<event.size(); i++){
			FxmlController<GridPane> fc = new FxmlController<GridPane>("EventOnce.fxml");
			fc.FxmlInit();
			String event_bun = event.get(i).get("EventBun");
			String link = event.get(i).get("Link");
			Label news_txt = (Label)fc.getObject("event");
			Hyperlink link_txt = (Hyperlink)fc.getObject("link");

			news_txt.setText(event_bun);
			link_txt.setText(link);
			link_txt.setOnAction(new LinkSend(link));

			//link_txt.setText(link);

			//ラベルの書式設定
			news_txt.setWrapText(true); //自動で改行する
			news_txt.setFont(Font.font(16));	//フォントサイズの指定
			news_txt.setMaxWidth(500);
			news_txt.setMinWidth(500);



			link_txt.setWrapText(true); //自動で改行する
			link_txt.setFont(Font.font(16));	//フォントサイズの指定
			link_txt.setMaxWidth(500);
			link_txt.setMinWidth(500);

			vbox.getChildren().add(fc.getPane());
			}
		}
     }
}
