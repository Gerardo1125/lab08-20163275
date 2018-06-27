package model.entity;
import java.util.Date;
import java.util.TimeZone;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long
	id;
	@Persistent private String nameRole;
	@Persistent private String nameResource;
	@Persistent private boolean status;
	@Persistent private Date date;
	
	public Access(String name,String nameR,boolean status){
		this.nameRole=name;
		this.nameResource = nameR;
		this.status = status;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		this.date=new Date();
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Access(){
	}
	public Long getId() {
		return id;
	}
	public String isId() {
		String td = String.valueOf(this.id);
		return td;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return nameRole;
	}
	public void setName(String name) {
		this.nameRole = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNameR() {
		return nameResource;
	}
	public void setNameR(String nameR) {
		this.nameResource = nameR;
	}
}
