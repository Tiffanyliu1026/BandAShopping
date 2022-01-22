package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bnaMember")
public class BnaMember {
	@Id
	private int mid;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String address;
	public BnaMember() {
		super();
	}
	public BnaMember(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public BnaMember(String password, String name, String phone, String address) {
		super();
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public BnaMember(int mid, String username, String password, String name, String phone, String address) {
		super();
		this.mid = mid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
