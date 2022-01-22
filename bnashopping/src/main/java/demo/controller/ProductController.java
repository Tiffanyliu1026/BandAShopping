package demo.controller;
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
import org.springframework.web.bind.annotation.ResponseBody;

import model.Product;
import model.ProductModel;
import model.BnaMember;

@Controller
public class ProductController {
	
	public BnaMember _bnaMember;
	
	@RequestMapping(value = "/product") //網址名稱
    public String showproduct(Model model,HttpSession httpSession) {     
		_bnaMember = (BnaMember)httpSession.getAttribute("himember");
		model.addAttribute("_bnaMembers", _bnaMember);
        return "product"; //指向jsp檔案名稱    
	}
	
	@RequestMapping(value="/productdisplay")
	@ResponseBody public List<Product> productPicPath(Model model){
		List<Product> p=null;
		SessionFactory sf=null;
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
			p =(List<Product>)s.createQuery("FROM Product").list();
	         //"FROM Picture" >> Class Model物件
			ts.commit();
			return p;
			} catch (HibernateException e) {
				if (ts!=null) ts.rollback();
				e.printStackTrace(); 
			} finally {
				s.close(); 
				sf.close();
			}
		return p;
		}
	
	
}
