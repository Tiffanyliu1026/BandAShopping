package demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.BnaMember;
import model.Item;
import model.Order;
import model.Orderdetail;
import model.ProductModel;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@RequestMapping(method = RequestMethod.GET)
	public String orderlist(HttpSession httpSession,ModelMap model) {
		BnaMember bna = (BnaMember)httpSession.getAttribute("himember");//使用者
		LocalDate todaysDate = LocalDate.now();
		SessionFactory sf =new Configuration().configure().buildSessionFactory();
		Session s=sf.openSession();
		List<Order> odd=new ArrayList<>();
		List<Orderdetail> oddlist=new ArrayList<>();
		try {
		s.beginTransaction();
		Order o=new Order();
		//o.setOrderno(0);//使用者編號
		o.setUserid(bna.getUsername());//使用者帳號名稱
		o.setDate(todaysDate);
		List<Item> cart = (List<Item>) httpSession.getAttribute("cart");//購物車
		int sum=0;
		for(Item i:cart)
		{
			Orderdetail od=new Orderdetail();
			od.setOrder(o);
			od.setPid(i.getProduct().getPid());
			od.setPname(i.getProduct().getPname());
			od.setQuantity(i.getQuantity());
			od.setPrice(i.getProduct().getPrice());
			od.setSubtotal(i.getQuantity()*i.getProduct().getPrice());
			sum += od.getSubtotal();
			/*private Order order;
			private int pid;
			private String pname;
			private int quantity;*/
			o.getOrderdetails().add(od);
			oddlist.add(od);
			System.out.print(o);
			//s.save(od);
		}
		o.setOrdersum(sum);
		odd.add(o);
		s.persist(o);
		s.getTransaction().commit();
		}catch(Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}finally {
			s.close(); 
			sf.close();
			}
		/*SessionFactory sf=null;
		try {
			sf=new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
		Session s=sf.openSession();
		Transaction ts=null;
		try {
			ts=s.beginTransaction();
			s.persist(o);
			ts.commit();
			} catch (HibernateException e) {
			if (ts!=null) ts.rollback();
			e.printStackTrace(); 
			} finally {
			s.clear();
			s.close(); 
			sf.close();
			}*/
		//modelMap.put("order",o);
		model.addAttribute("order",odd);
		model.addAttribute("orderlistss",oddlist);
		httpSession.removeAttribute("cart");
		return "vieworder";//jsp頁面
	}
}
