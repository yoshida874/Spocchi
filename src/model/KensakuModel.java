package model;

public class KensakuModel extends BaseModel{


	static	private String m_UserName ;
	private int m_Flag ;
	private String m_Type;


	public String getKensaku() {
		return m_UserName;
	}

	public void setKensaku(String i_UserName) {
		this.m_UserName = i_UserName;
	}

	public int getflag() {
		return m_Flag;
	}

	public void setflag(int i_Flag) {
		this.m_Flag = i_Flag;
	}

	public String getType() {
		return m_Type;
	}

	public void setType(String i_Type) {
		this.m_Type = i_Type;
	}



}