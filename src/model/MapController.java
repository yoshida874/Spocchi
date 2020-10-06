package model;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

/*README
 * GMapsFX-2.10.0.jarを参照ライブラリに追加すること
 *
 * プログラミング参考サイト
 * https://rterp.wordpress.com/2014/04/25/gmapsfx-add-google-maps-to-your-javafx-application/
 * https://www.programcreek.com/java-api-examples/?api=com.lynden.gmapsfx.javascript.object.MapTypeIdEnum
 * https://github.com/rterp/GMapsFX/issues/149
 *
 * API取得方法参考サイト
 * https://design-plus1.com/tcd-w/2018/08/google-maps-platform-api.html
 */

/**************************************************
 * GoogleMap接続用クラス
 *************************************************/
//MapComponentInitializedListenerを継承する。

public class MapController implements MapComponentInitializedListener{
	GoogleMapView gmv ;	//fxmlに追加するオブジェクト（コントローラー）
	GoogleMap map;		//googlemapの操作するクラス
	double Keido,Ido;
	String sisetuName;

	/***********************************************
	 * コンストラクタ
	 **********************************************/
	public MapController(double inputX, double inputY) {
		//GooglemapVIew(使用言語　英語：en-US　日本語:JP,GoogleのAPIキー）
		//gmv = new GoogleMapView("JP", "GoogleのAPIキー");
		//gmv = new GoogleMapView("JP","AIzaSyDo0xZZhb9lOLz_Tu03xPf69Wu8ostWuTE");
		//gmv = new GoogleMapView("JP","AIzaSyCz5MMgd0lRhXJia58bM3dBKmbsyi0j40U ");

//仮表示↓ 自分たちが作ったapiキーだと１日１回しか表示ができない。発表の時とかに先生のapiキーが渡されるらしい
		gmv = new GoogleMapView("JP","AIzaSyA2K31NmkxRCEBy5zRqZTQTUZy6xDSSvuQ");
		//gmv = new GoogleMapView();
		//googleMapを操作するクラスの設定
		//(MapComponentInitializedListenerを継承したクラスを入れる）
		gmv.addMapInializedListener(this);

		//更新（初期化）
		Ido = inputX;
		Keido = inputY;

	}


	/***********************************************
	 * GoogleMapViewを返す。（Paneのchildrenに追加できる。
	 * @return GoogleMapViewを返す
	 **********************************************/
	public GoogleMapView getGoogleMapView() {
		return gmv;
	}

	@Override
	/***********************************************
	 * マップの初期化処理
	 **********************************************/
	public void mapInitialized()  {

		// TODO 自動生成されたメソッド・スタブ
		 //Set the initial properties of the map.
	    MapOptions mapOptions = new MapOptions();

	    //マップの初期設定
	 // mapOptions.center(new LatLong(緯度,経度))
	   mapOptions.center(new LatLong(Ido,Keido))//表示マップの中心地点
	   			.mapType(MapTypeIdEnum .ROADMAP)					//マップの表示方法
	            .overviewMapControl(false)
	            .panControl(false)
	            .rotateControl(false)
	            .scaleControl(false)
	            .streetViewControl(false)
	            .zoomControl(false)
	            .zoom(15);										//拡大縮小のサイズ
	    map = gmv.createMap(mapOptions);

	   /* TeamMapDB  tmb;
	    tmp = new TeamMapDB();
	    ArrayList<HashMap<String, String>>  sisetuName;
	    sisetuName = spb.getmap_serch("1");
*/
	  //ピンを表示する
	    //Add a marker to the map
	  MarkerOptions markerOptions = new MarkerOptions();
	   markerOptions.position( new LatLong(Ido,Keido) )
	                .visible(Boolean.TRUE)
	                .title("sisetuName");
	                //施設名うまく表示されなかったらそっと消してください
	              //  .title(sisetuName.get(0).get("sisetuName"));
	    Marker marker = new Marker( markerOptions );

	    //表示するピンの追加
	    map.addMarker(marker);
	}
}
