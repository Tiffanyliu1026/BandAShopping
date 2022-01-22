package demo.controller;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import model.BnaMember;

@Controller
public class MemberController {
	@RequestMapping(value = "/register")
	  public String register(ModelMap model) {
	  return "memberform";
	  }
	
	@RequestMapping("/addmember")
	@ResponseBody
    public String addMember(@ModelAttribute("bnaMember") BnaMember bnaMember) {
		SessionFactory factory=null;
		List<BnaMember> data= null;
		String SQL = "FROM BnaMember B WHERE B.username = :Busername";
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
		 	Query query = session.createQuery(SQL);
		 	query.setParameter("Busername",bnaMember.getUsername());
		 	 //以上程式先將資料庫資料取出並儲存到List中
	         //再使用for迴圈與前端接收到的帳號密碼做比對
		 	data= query.list();
		 	System.out.print(data);
		 	if(data.size() == 0){
	        		session.persist(bnaMember); //儲存得到的物件m
	  		 		tx.commit();
	  		 		return "Registration successful!";
	        }
	         //如果資料相等則回傳Account Success
		 	} catch (Exception e) {
		 	if (tx!=null)
        	 tx.rollback();
         	System.out.println("Hibernate Error:"+e.getMessage());
		 	} finally {
		 		session.close();
		 		factory.close();
		 	}
		 
		 //return "Try again";
		 return "Sorry,the account already exists.";
		 
    }
}
