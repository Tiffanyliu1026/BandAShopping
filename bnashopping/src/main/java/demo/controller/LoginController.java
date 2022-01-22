package demo.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.BnaMember;

@Controller
public class LoginController {
	@RequestMapping(value = "/loginpage")
	  public String login(ModelMap model) {
	  return "login";
	  }
	
	@RequestMapping("/check")
	@ResponseBody
	 public String checkAccount(@ModelAttribute("BnaMember") BnaMember mem,HttpSession httpSession) {
		//@ModelAttribute("bnaMember") 接收前端表單回傳資料 >>帳號密碼
		//以下先將資料庫資料取出
        List<BnaMember>  data= null;
        SessionFactory factory=null;
		 try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex);	        
	      }		
		
		  Session session = factory.openSession();
	      Transaction tx = null;
	      try {
		         tx = session.beginTransaction();
		         data =(List<BnaMember>)session.createQuery("FROM BnaMember").list(); 		       
		         tx.commit();
		         //以上程式先將資料庫資料取出並儲存到List中
		         //再使用for迴圈與前端接收到的帳號密碼做比對
		         for(BnaMember a : data) {
		        	  if(a.getUsername().equals(mem.getUsername()) && a.getPassword().equals(mem.getPassword())) 
		        	  {
		        		  httpSession.setAttribute("himember", a);
		        		  return "Success";
		        	  }        		  
		         }//如果資料相等則回傳Account Success
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		         factory.close();
		      }
        return "Failed";
    } 
	@RequestMapping("/account") //test
	@ResponseBody public List<BnaMember> generatehbAccount(Model model){
		List<BnaMember> data= null;
		SessionFactory sf=null;
		try {
		sf=new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
		}
		 Session session = sf.openSession();
	      Transaction tx = null;
	      try {
		         tx = session.beginTransaction();
		         data =(List<BnaMember>)session.createQuery("FROM BnaMember").list();//Class Model名稱		       
		         tx.commit();
		         return data;
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		         sf.close();
		      }
       return data;
       }
}
