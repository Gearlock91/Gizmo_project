package model;

public class User {
	
	private String name;
	private int age;
	private double weight;
	private int maxHeart;
	private String password;
	private String email;
	private String userName;
	
	public User(String name, String userName, String password, String email) {
		
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public int getAge() {
		return age;
		
	}
	public int getMaxHeart() {
		return maxHeart;
		
	}
	public double getWeight() {
		return weight;
		
	}
	public String getName() {
		return name;
		
	}
	public String getUserName() {
		return userName;
		
	}
	public String getEmail() {
		return email;
		
	}
	public String getPassword() {
		return password;
		
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setMaxHeart(int mHeartRate) {
		this.maxHeart = mHeartRate;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public void setPassword(String password){
        this.password = password;
    }
}
