package model;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ProductModel {
	private List<Product> products;
	//產品清單
	public ProductModel() {
		//this.products = new ArrayList<Product>();
		//this.products.add(new Product("p01", "JBud Elite", "images/BB.jpg","bb", 20));
		//this.products.add(new Product("p02", "EdiMax Wifi", "images/powder.jpg","cc", 21));
		//this.products.add(new Product("p03", "Asus Laptop", "images/lipstick.jpg","dd", 22));
		from_mvDB();
	}

	public List<Product> findAll() {
		return this.products;
	}

	public Product find(String id) {
		for (Product product : this.products) {
			if (product.getPid().equalsIgnoreCase(id)) {
				return product;
			}
		}
		return null;
	}
	 
	void  from_mvDB(){
   	  StandardServiceRegistry ssr = 
				  new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		  Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		  
		SessionFactory factory = meta.getSessionFactoryBuilder().build();  		
		Session sessionObj = null;//HttpSession
		Session session = null;
		session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			products= session.createQuery("FROM Product").list();
		    System.out.println("product list :"+products);
			tx.commit();
		
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
}
}
