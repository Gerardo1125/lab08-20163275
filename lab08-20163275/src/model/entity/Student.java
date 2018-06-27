package model.entity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;
import javax.jdo.annotations.*;

@PersistenceCapable (identityType=IdentityType.APPLICATION)
public class Student {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	
	@Persistent private String name;
	@Persistent private String phone;
	@Persistent private String email;
	@Persistent private Date date;
	@Persistent private String birth;
	@Persistent private String gender;
	@Persistent private String DNI;
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Student(String name, String phone, String email, String gender,String dato,String DNI) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.birth = dato;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
		this.date = new Date();
		this.DNI = DNI;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String dato) {
		this.birth = dato;
	}

	public String isGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getB() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = birth;
        try {

            Date date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
	}
}
