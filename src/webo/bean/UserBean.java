package webo.bean;

public class UserBean {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String middleName;
	private String birthday;
	private int id;
	public boolean valid;
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setMiddleName(String middleName){
		this.middleName=middleName;
	}
	public String getMiddleName(){
		return this.middleName;
	}
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public String getBirthday(){
		return this.birthday;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String CUS_FNAME){
		this.firstName = CUS_FNAME;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setLastName(String CUS_LNAME){
		this.lastName = CUS_LNAME;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String CUS_PASS){
		this.password = CUS_PASS;
	}
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String CUS_USERN){
		this.username = CUS_USERN;
	}
	public boolean isValid(){
		return valid;
	}
	public void setValid(boolean valid){
		this.valid = valid;
	}

}
