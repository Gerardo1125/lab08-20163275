package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.jdo.annotations.*;

@PersistenceCapable (identityType=IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String role;
	@Persistent private String email;
	@Persistent private Date date;
	@Persistent private String birth;
	@Persistent private String gender;
	@Persistent private boolean status;
	public User(String role ,String email,String birth,String gender,boolean status) {
		this.role = role;
		this.email = email;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		this.date = new Date();
		this.gender = gender;
		this.birth = birth;
		this.status = status;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
