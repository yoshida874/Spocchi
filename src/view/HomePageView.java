package view;



import java.util.ArrayList;
import java.util.HashMap;

import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.LinkSend;
import controller.SendEvent.TeamSend;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.FxmlController;
import model.dataManager.MenuSQL;



//BaseView<Paneの型を入れる(fxml)>
public class HomePageView extends BaseView<GridPane> {

    static int count = 0 ;

    /**
     * コンストラクタ
     */
    public HomePageView(){
        super("Home_.fxml");
    }

    /**
     * 初期化処理
     */
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

         ScrollPane sp = (ScrollPane)getObject("slid");

         //timeline実行
          Timeline timer = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                  //sp.setPrefwidth(String.valueOf(Integer.parseInt(sp.getPrefwidth()) + ));
                  //scrollpaneの値に動かす分の値を足す
                  //変数.メソッド名();
                  double getHvalue = sp.getHvalue();
                  getHvalue = sp.getHvalue() + 0.335;

                  sp.setHvalue(getHvalue);
                  count++ ;

                  //最後にいったら０にする
                  if( count >= 4 && sp.getHvalue() >= sp.getHmax())    {
                      sp.setHvalue(0);
                      count = 0 ;
                  }

                }
            }));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();


         //画面遷移
        line.setOnAction(new LineSend());
        Base.setOnAction(new TeamSend());
        Baske.setOnAction(new TeamSend());
        soccer.setOnAction(new TeamSend());
        hocke.setOnAction(new TeamSend());
        Ba.setOnAction(new KensakuSend());
        Su.setOnAction(new KensakuSend());
        So.setOnAction(new KensakuSend());
        Ho.setOnAction(new KensakuSend());
        home.setOnAction(new HomeSend());

		//イベント文の取得
		ArrayList<HashMap<String,String>> event;


        ScrollPane sp2 = (ScrollPane)getObject("news") ;
		VBox vbox = new VBox();
		sp2.setContent(vbox);

		MenuSQL db = new MenuSQL();
		event =  db.Team_Event();

		for(int i=0 ; i<5; i++){
			FxmlController<GridPane> fc = new FxmlController<GridPane>("HomeOnce.fxml");
			fc.FxmlInit();
			String event_bun = event.get(i).get("EventBun");
			String link_txt = event.get(i).get("Link");
			Label news = (Label)fc.getObject("event");
			Hyperlink link = (Hyperlink)fc.getObject("link");

			news.setText(event_bun);
			link.setText(link_txt);
			link.setOnAction(new LinkSend(link_txt));

			//ラベルの書式設定
			news.setWrapText(true); //自動で改行する
			news.setFont(Font.font(20));	//フォントサイズの指定
			news.setMaxWidth(960);
			news.setMinWidth(960);



			link.setWrapText(true); //自動で改行する
			link.setFont(Font.font(20));	//フォントサイズの指定
			link.setMaxWidth(950);
			link.setMinWidth(950);

			vbox.getChildren().add(fc.getPane());
			}
    }
}