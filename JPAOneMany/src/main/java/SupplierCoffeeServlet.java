

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Supplier;
import model.Coffee;

/**
 * Servlet implementation class SupplierCoffeeServlet
 */
@WebServlet("/SupplierCoffeeServlet")
public class SupplierCoffeeServlet extends HttpServlet {
	
	void add() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAOneMany");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Supplier s=new Supplier();
		
		s.setSupId(22);
		s.setSupName("JAY");
		s.setStreet("Garden");
		s.setCity("Mojito");
		s.setState("CA");
		s.setZip("95199");
		
		Coffee c1=new Coffee();
		c1.setCofName("Flavor A");
		c1.setPrice(new BigDecimal(200.0));
		//BigDecimal>>很大的小數點資料類型>>沒有容量限制
		//也可使用double但double有容量限制
		c1.setSales(10);
		c1.setTotal(5);
		c1.setSupplier(s);//將上面產生的Supplier物件當作關聯資料
		
		Coffee c2=new Coffee();
		c2.setCofName("Flavor B");
		c2.setPrice(new BigDecimal(100.0));
		c2.setSales(11);
		c2.setTotal(6);
		c2.setSupplier(s);
		
		//em.persist(c1);
		//em.persist(c2);
		
		ArrayList<Coffee> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		s.setCoffees(list);
		em.persist(s);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
	
	void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAOneMany");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query=em.createQuery("select c from Coffee c");
		List<Coffee> co=query.getResultList();
		System.out.println("Coffee:");
		for(Coffee s:co) {
			System.out.print(s);//coffee 物件
			System.out.print(s.getSupplier()+"\n");//supplier 物件
			//直接輸出object時系統會自動呼叫object的toString方法
			//原class中沒有撰寫toString方法>>直接列印出記憶體位址
			//回到class中增加一個toString方法>>override
		}
		em.close();
        emf.close();
        request.setAttribute("coffeeList", co);
        request.getRequestDispatcher("viewCoffee.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//add();
		//query();
		query(request, response);//顯示於網頁
		response.getWriter().append("Succeed");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
