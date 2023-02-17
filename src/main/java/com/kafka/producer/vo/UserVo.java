package com.kafka.producer.vo;

public class UserVo {

	private String id;
    private String dpt;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	
	public UserVo(String id, String dpt) {
		super();
		this.id = id;
		this.dpt = dpt;
	}
	
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", dpt=" + dpt + "]";
	}
    
    
    
}
