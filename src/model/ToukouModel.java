package model;

public class ToukouModel extends BaseModel{

	private String m_Img ;
	private String m_Sisetu;

	public String getImg() {
		return m_Img;
	}

	public void setimg(String i_Img) {
		this.m_Img = i_Img;
	}

	public String getSisetuID(){
		return m_Sisetu;
	}

	public void setSisetuID(String i_Sisetu) {
		this.m_Sisetu = i_Sisetu;
	}


}