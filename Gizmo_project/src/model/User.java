package model;

import dao.ActivityDAO;

public class User {
	
	private String name;
	private int age;
	private double weight;
	private int maxHeart = 0;
	private char[] password;
	private String email;
	private String userName;
	private int uId;
	private double maxDist;
	private double height;
	
	private boolean active;

	public User(String email,String name, String userName, char[] password) {
		
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	public User(String email,String name, String userName, char[] password, int uId, double weight, int age, double height) {
		
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.uId = uId;
		this.weight = weight;
		this.age = age;
		this.height = height;
	}
	public int getAge() {
		return age;
		
	}
	public int getMaxHeart() {
		if(ActivityDAO.getInstance().size() == 0) {
			return maxHeart = 0;
		}
			
		else {
			maxHeart = (int)ActivityDAO.getInstance().get(0).getMaxHeartActivity();	
			for(int i = 0; i < ActivityDAO.getInstance().size(); i++) {
				int heart = (int)ActivityDAO.getInstance().get(i).getMaxHeartActivity();
				if(heart > maxHeart)
					maxHeart = heart;
			}
		}
		

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
	public char[] getPassword() {
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
    public void setPassword(char[] password){
        this.password = password;
    }
    
    public int getUid() {
    	return uId;
    }
    
    public void setActive() {
    	active = true;
    }
    
    public boolean getActive() {
    	return active;
    }
    
    public double getMaxDistance() {
    	
    	if(ActivityDAO.getInstance().size() == 0) {
			return maxDist = 0;
		}
    	else {
    		maxDist = (int)ActivityDAO.getInstance().get(0).getDistance();	
    		for(int i = 0; i < ActivityDAO.getInstance().size(); i++) {
    			int dist = (int)ActivityDAO.getInstance().get(i).getDistance();
    			if(dist > maxDist)
    				maxDist = dist;
    		}
    	}
    	

		return maxDist;
    }
    
    public double getHeight() {
    	return height;
    }
    
    public void setHeight(double height) {
    	this.height = height;
    }
}
