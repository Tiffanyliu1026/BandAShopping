package demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Orderdetail;
@Controller
public class CheckOrderDeatilController {
	@RequestMapping(value = "check/{id}", method = RequestMethod.GET)
	public String check(@PathVariable("id") String orid, HttpSession session,ModelMap model) {
		int no=Integer.parseInt(orid);
		List<Orderdetail> orderdata=null;
		SessionFactory factory=null;
		String SQL = "FROM Orderdetail O WHERE O.order.orderno = :OrderNo";
		
		try {
			factory=new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		    throw new ExceptionInInitializerError(ex);
		}
		Session s=factory.openSession();
		Transaction ts=null;
		try {
			ts=s.beginTransaction();
			Query query = s.createQuery(SQL);
		 	query.setParameter("OrderNo",no);//設定條件>>使用者帳號
		 	orderdata =query.list();
		 	System.out.print(orderdata);
			ts.commit();
			} catch (HibernateException e) {
				if (ts!=null) ts.rollback();
				e.printStackTrace(); 
			} finally {
				s.close(); 
				factory.close();
			}
		model.addAttribute("checkdetails",orderdata);
		//model.addAttribute("orddetail",odsdetailss);
		return "checkdeatil";
	}
}
