package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	private String pid;
	private String pname;
	private String pimage;
	private String pdecript;
	private int price;
	
	public Product() {
		super();
	}

	public Product(String pid, String pname, String pimage, String pdecript, int price) {
	super();
	this.pid = pid;
	this.pname = pname;
	this.pimage = pimage;
	this.pdecript = pdecript;
	this.price = price;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdecript() {
		return pdecript;
	}

	public void setPdecript(String pdecript) {
		this.pdecript = pdecript;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

}
