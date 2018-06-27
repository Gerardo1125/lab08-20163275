package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.jdo.annotations.*;

@PersistenceCapable (identityType=IdentityType.APPLICATION)
public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String name;
	@Persistent private boolean status;
	@Persistent private Date date;
	public Role(String name,boolean status) {
		this.name = name;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		this.date = new Date();
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
