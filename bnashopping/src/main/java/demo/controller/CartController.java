package demo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.*;

@Controller
@RequestMapping(value = "cart")
public class CartController {
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "viewCart";
	}

	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") String pid, HttpSession session) {
		ProductModel productModel = new ProductModel();
		if (session.getAttribute("cart") == null) {
			//判斷sesion的物件變數cart是否有資料 >>表購物籃狀態
			List<Item> cart = new ArrayList<Item>();
			//如果空的建立一個新的購物籃
			cart.add(new Item(productModel.find(pid), 1));
			//加入購物車 >> 將產品編號資料找出
			session.setAttribute("cart", cart);
			//再加入session中
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			//如果有資料則將物件取出
			int index = this.exists(pid, cart);//查詢是否有此id在List中
			if (index == -1) {
				cart.add(new Item(productModel.find(pid), 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
		//後端轉向到cart/index的Mapping >>再由Mapping轉到jsp
		//假設未來顯示畫面jsp更動只要改一個地方/cart/index
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") String id, HttpSession session) {
		ProductModel productModel = new ProductModel();
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(id, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index";
	}

	private int exists(String id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getPid().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}
}
