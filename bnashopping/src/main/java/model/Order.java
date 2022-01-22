package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="`order`")
public class Order implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderno")
	private int orderno;
	@Column(name = "userid", length = 10, precision =0) 
	private String userid;
	@OneToMany(mappedBy="order" ,  cascade = CascadeType.ALL)
	private List<Orderdetail> orderdetails = new ArrayList<>();
	private LocalDate date;
	private int ordersum;
	
	public Order() {
		super();
	}
	public Order(String userid) {
		super();
		this.userid = userid;
	}
	public Order(int orderno, String userid) {
		super();
		this.orderno = orderno;
		this.userid = userid;
	}
	public Order(int orderno, String userid,List<Orderdetail> orderdetails) {
		super();
		this.orderno = orderno;
		this.userid = userid;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getOrdersum() {
		return ordersum;
	}
	public void setOrdersum(int ordersum) {
		this.ordersum = ordersum;
	}
	
	
}
