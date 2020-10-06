package controller.SendEvent;

import java.awt.Desktop;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LinkSend implements EventHandler<ActionEvent> {
	String m_link;

	public LinkSend(String i_link){
		m_link =i_link;
	}

	/**
	 * ボタンクリック時の処理
	 */
	@Override
	public void handle(ActionEvent event) {
		String uriString = m_link; // 開くURL
		Desktop desktop = Desktop.getDesktop();
		try{
		  URI uri = new URI( uriString );
		  desktop.browse( uri );
		}catch( Exception e ){
		  e.printStackTrace();
		}
	}
}
