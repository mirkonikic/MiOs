package networkingServer;

import java.util.GregorianCalendar;

public class Client {
	private int id;
	private String name = "default";
	private String ip_addr = "0.0.0.0";
	private Boolean hide = false;

	public Client() 
	{
		
	}

	public void setHide(Boolean phide) 
	{
		hide = phide;
	}
	
	public Boolean getHide()
	{
		return hide;
	}
	
	public String getIp_addr() 
	{
		return ip_addr;
	}
	
	public void setIp_addr(String ip) 
	{
		ip_addr = ip;
	}
	
	public int getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String p_name) 
	{
		name = p_name;
	}
	
	public void setId(int p_id) 
	{
		id = p_id;
	}
}
