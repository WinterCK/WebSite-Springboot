package com.cjk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Integer id ;
    private String userName ;
    private String books;
    
    @Min(value = 100, message = "低于100----禁止注册")
    private String money;
    private String password;
    
    public Account(String userName, String books,Integer id ) {
    	this.id = id;
    	this.userName = userName;
    	this.books = books;
    }

    public Account() {
    	
    }
    public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
    	return password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
    	this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", money=" + money +
                '}';
    }
}
