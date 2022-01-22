package demo.controller;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.BnaMember;
import model.Item;
import model.Order;
import model.Orderdetail;
import model.Product;
import model.ProductModel;

@Controller
public class OrderlistController {
	@RequestMapping(value="/orderlist")
		public String orderquery(Model model,HttpSession httpSession){
		BnaMember b=new BnaMember();
		b=(BnaMember)httpSession.getAttribute("himember");
		List<Order> orderdata=null;
		SessionFactory factory=null;
		String SQL = "FROM Order O WHERE O.userid = :Orderaccount";
		
		try {
			factory=new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
		Session session=factory.openSession();
		Transaction ts=null;
		try {
			ts=session.beginTransaction();
			Query query = session.createQuery(SQL);
		 	query.setParameter("Orderaccount",b.getUsername());//設定條件>>使用者帳號
		 	orderdata =query.list();
		 	System.out.print(orderdata);
			ts.commit();
			} catch (HibernateException e) {
				if (ts!=null) ts.rollback();
				e.printStackTrace(); 
			} finally {
				session.close(); 
				factory.close();
			}
		model.addAttribute("order",orderdata);
		//model.addAttribute("orddetail",odsdetailss);
		return "queryorder";
		}
}
