package demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.BnaMember;

@Controller
public class MemberPageController {
	@RequestMapping(value = "/memberpage")
	  public String register(HttpSession httpSession,ModelMap model) {
		BnaMember bna = (BnaMember)httpSession.getAttribute("himember");//使用者
		model.addAttribute("_bnaMembers", bna);
		return "memberpage";
	  	}
	
	@RequestMapping("/changememberinfor")
	@ResponseBody
    public String addMember(@ModelAttribute("bnaMember") BnaMember bnaMember,HttpSession httpSession) {
		BnaMember bna = (BnaMember)httpSession.getAttribute("himember");//使用者
		SessionFactory factory=null;
		BnaMember data= null;
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
		 	Query<BnaMember> query = session.createQuery(SQL);
		 	query.setParameter("Busername",bna.getUsername());
		 	 //以上程式先將資料庫資料取出並儲存到List中
	         //再使用for迴圈與前端接收到的帳號密碼做比對
		 	data= query.list().get(0);
		 	System.out.print(data);
		 	if(data != null){
		 		data.setUsername(bna.getUsername());
		 		data.setPassword(bnaMember.getPassword());
		 		data.setName(bnaMember.getName());
		 		data.setPhone(bnaMember.getPhone());
		 		data.setAddress(bnaMember.getAddress());
	        		session.update(data); //儲存得到的物件m
	  		 		tx.commit();
	  		 		httpSession.setAttribute("himember", data);
	  		 		return "Modification completed!";
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
		 return "Some of your member's information are not fill in.Please try again.";
		 
    }
}
