package demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.ProductModel;

@Controller
@RequestMapping(value = "/productcart")
public class ProductCartController {
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		ProductModel productModel = new ProductModel();
		//productModel產品畫面基本資料
		modelMap.put("products", productModel.findAll());
		return "index";//jsp頁面
	}
}
