package model.entity;
import java.util.Date;
import java.util.TimeZone;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Resource {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long
	id;
	@Persistent private String name;
	@Persistent private Date date;
	@Persistent private boolean status;
	public Resource(String name,boolean status){
		this.name=name;
		this.status = status;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		this.date=new Date();
	}
	public Resource(){
	}
	public Long getId() {
		return id;
	}
	public String isId() {
		String td = String.valueOf(this.id);
		return td;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
