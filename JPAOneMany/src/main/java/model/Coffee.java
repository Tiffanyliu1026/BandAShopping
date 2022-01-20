package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the coffees database table.
 * 
 */
@Entity
@Table(name="coffees")
@NamedQuery(name="Coffee.findAll", query="SELECT c FROM Coffee c")
public class Coffee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COF_NAME")
	private String cofName;

	private BigDecimal price;

	private int sales;

	private int total;

	//bi-directional many-to-one association to Supplier
	//bi-directional>>雙向的意思
	@ManyToOne //多咖啡對一廠商
	@JoinColumn(name="SUP_ID") //關聯欄位
	private Supplier supplier; 
	//宣告產生一個supplier物件>>擷取supplier物件中的ID非直接讀取Supplier中的ID

	public Coffee() {
	}

	public String getCofName() {
		return this.cofName;
	}

	public void setCofName(String cofName) {
		this.cofName = cofName;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSales() {
		return this.sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String toString() {
        return  this.cofName+" : "+ this.price;  	
   }

}