//紹介ページ

package view;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import controller.SendEvent.HomeSend;
import controller.SendEvent.KensakuSend;
import controller.SendEvent.LineSend;
import controller.SendEvent.TeamSend;
import controller.toukou.ToukouSend;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.FxmlController;
import model.MapController;
import model.SisetuIDModel;
import model.Map.SpotMapDB;
import model.dataManager.ReviewSQL;
//BaseView<Paneの型を入れる(fxml)>
public class SpotView extends BaseView<GridPane> {
    /**
     * コンストラクタ
     */
    public SpotView(){
        super("Spot.fxml");
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

            //画面遷移
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

		if(getParam() instanceof SisetuIDModel){

			SisetuIDModel ID = (SisetuIDModel)getParam();
			ReviewSQL ut = new ReviewSQL();

			//データベースの値を入れる
			ArrayList<HashMap<String,String>> name;
			name = ut.Review_search(ID.getSisetuID());



			//Txt.setText(LText);

			Button btn = (Button)getObject("btn");
            btn.setOnAction(new ToukouSend(ID.getSisetuID()));

            //ユーザ領域の取得
    		ScrollPane sp = (ScrollPane)getObject("Scroll") ;
    		VBox vbox = new VBox();
    		sp.setContent(vbox);


    		for(int i=0 ; i<name.size(); i++){
    			String User = name.get(i).get("username");
    			String Title = name.get(i).get("kutikomi_tbl.title");
    			String Hyouka = name.get(i).get("hyoukaten");
    			String Bun = name.get(i).get("bun");

    			//一つの施設領域の生成
    			FxmlController<GridPane> fc = new FxmlController<GridPane>("ReviewOnce.fxml");
    			fc.FxmlInit();
    			Label user = (Label)fc.getObject("user");
    			Label title = (Label)fc.getObject("title");
    			Label hyouka = (Label)fc.getObject("hyouka");
    			Label review = (Label)fc.getObject("review");


    			title.setFont(Font.font(20));
    			title.setMaxWidth(570);
    			title.setMinWidth(570);

    			//レビューのレイアウト


    			review.setWrapText(true); //自動で改行する
    			review.setFont(Font.font(16));	//フォントサイズの指定
    			review.setMaxWidth(400);
    			review.setMinWidth(400);


    			user.setText(User);
    			title.setText(Title);
    			hyouka.setText(Hyouka);
    			review.setText(Bun);
    			vbox.getChildren().add(fc.getPane());

    		}


    		//店舗情報の登録
    		ScrollPane sp_info = (ScrollPane)getObject("Scroll_info") ;
    		VBox vb = new VBox();
    		sp_info.setContent(vb);

    		ArrayList<HashMap<String,String>> sisetu;
			sisetu = ut.Info_search(ID.getSisetuID());

			FxmlController<GridPane> fc = new FxmlController<GridPane>("TenpoOnce.fxml");
			fc.FxmlInit();
			Label title = (Label)fc.getObject("sisetu_title");
			Label time = (Label)fc.getObject("time");
			Label day = (Label)fc.getObject("day");

			title.setWrapText(true);
			title.setFont(Font.font(24));
			title.setMaxWidth(430);

			title.setText(sisetu.get(0).get("title"));
			day.setText(sisetu.get(0).get("teikyuday"));
			time.setText(sisetu.get(0).get("eigyoutime"));
			vb.getChildren().add(fc.getPane());


			Label sisetu_name = (Label)getObject("name");
			sisetu_name.setText(sisetu.get(0).get("sisetuName"));
			sisetu_name.setWrapText(true);



		     //ユーザ領域の取得
    		ScrollPane imgpane = (ScrollPane)getObject("img_pane") ;
    		HBox vbo = new HBox();
    		imgpane.setContent(vbo);

    		//画像設定
    		for(int i=0 ; i<name.size(); i++){
    			FxmlController<GridPane> fco = new FxmlController<GridPane>("ImgOnce.fxml");
    			fco.FxmlInit();
    			//画像が設定されている場合
    			if(!name.get(i).get("kutikomi_tbl.imgpass").equals("null")){
    			ImageView img = (ImageView)fco.getObject("img");
    			StringBuilder FilePath = new StringBuilder(name.get(i).get("kutikomi_tbl.imgpass"));
    			Image image = new Image( Paths.get(FilePath.toString()).toUri().toString());
    			img.setImage(image);

    			vbo.getChildren().add(fco.getPane());
    			}
    		}

    		//DB接続
    	      SpotMapDB sb = new SpotMapDB();
    	       ArrayList<HashMap<String,String>> i_do = null;
    	       String keido ,ido;
    	       i_do = sb.getmap_serch(ID.getSisetuID());
    	      // i_do = sb.getmap_serch(ken.getKensaku());
    	       ido = i_do.get(0).get("Ido");
    	       keido= i_do.get(0).get("Keido");
    	       double d_ido = Double.parseDouble(ido);
    	       double d_keido = Double.parseDouble(keido);
    	       //教科書P104
    	       //GridPaneに直接GoogleMapを入れる
    	        GridPane gp = getPane(); //GridPane取得
    	       MapController mc = new MapController(d_ido, d_keido);
    	      //GridPane.setConstraints(node,X(縦),Y(横)); <- GridPaneの座標
    	      GridPane.setConstraints(mc.getGoogleMapView(),2,2);
    	       gp.getChildren().add(mc.getGoogleMapView());

		}

    }
}

