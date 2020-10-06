package controller.toukou;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ImgEvent implements EventHandler<ActionEvent>{
	ImageView m_img;

	public ImgEvent(ImageView i_img){
		m_img = i_img;

	}


	public void handle(ActionEvent event){


		FileChooser fc = new FileChooser();
		fc.setTitle("Open File");

		//拡張子の指定
		fc.getExtensionFilters().addAll(
				  new FileChooser.ExtensionFilter("イメージファイル", "*.jpg", "*.png")
				 );

		//初期位置の指定
		fc.setInitialDirectory( new File("../../../bin/image"));


		//日付の取得
		Date date = new Date();
		LocalDateTime ldt = LocalDateTime.now();
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd-ss-SSS");
		String date_format = dateformat.format(date);

		date_format = date_format + ".jpg";

		//保存処理
		File file = fc.showSaveDialog(null);
		//保存する場所の指定
		File fileout = new File("../../../bin/image" + date_format);



		if(file != null) {

			System.out.println(file.getPath());
		}


		//imageViewに画像をセット
		 Image image = new Image( Paths.get(file.getPath().toString()).toUri().toString());
		 m_img.setImage(image);

		 //投稿イベントに値を渡す
		 ToukouEvent.pass_in = file.getPath();
		 ToukouEvent.pass_out = fileout.getPath();
	}


}
