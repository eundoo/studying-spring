package com.sample.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.service.ProductService;
import com.sample.vo.CartItem;
import com.sample.vo.Product;
import com.sample.vo.User;
import com.sample.web.annotation.LoginUser;
import com.sample.web.utils.SessionUtils;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LogManager.getLogger(HomeController.class);
	/*
	 * ProductService 인터페이스의 구현객체(ProductServiceImpl)가 주입된다.
	 */
	@Autowired
	private ProductService productService;
	
	/**
	 * 화면에 표시할거는 Model이라는 객체에 담아야한다.
	 * 모든 상품목록 요청을 처리하는 요청 핸들러 메소드 정의
	 * @param model 뷰페이지에 전달할 데이터를 담는 객체, HandlerAdapter객체가 Model을 생성해서 전달한다.
	 * @return	뷰 페이지의 이름
	 */
	@GetMapping("/list")
	public String products(Model model) {
		// 판매중인 전체 상품정보 조회하기
		List<Product> productList = productService.getAllProducts();
		
		// 뷰 페이지에 상품정보 목록 전달하기
		model.addAttribute("products", productList);
		
		// 뷰 페이지로 내부 이동하기
		// WEB-INF/views/product/list.jsp로 내부이동해서 JSP 실행시키기
		return "product/list";
	}
	//to {GET /product/list}: There is already 'productController' bean method이 오류는 @GetMapping주소가 중복된거임
	//실제 요청 URL : localhost/spring-mvc/product/detail?no=100
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int productNo, Model model) {
		logger.debug("details() 실행됨");
		logger.info("productNo" + productNo);
		
		Product product = productService.getProductDetail(productNo);
		
		model.addAttribute("product", product);
	
		logger.debug("details() 종료됨");
		return "product/detail"; //"WEB-INF/views/" + product/detail + ".jsp"
	}
	
	@GetMapping("/addCart")
	//	public String addCartItem(@RequestParam("no") int productNo , @LoginUser User user) 이렇게 쓰면 세션에서 로그인 된 유저를 불러옴
	//																			패키지랑 클래스를 만들어서 거기다가 뭐 해놓고 
	
	//	public String addCartItem(@RequestParam("no") int productNo , User user) 이렇게 쓰면 User빈 객체를 만들어서 요청객체에서 값꺼내서 넣음
	public String addCartItem(@RequestParam("no") int productNo, @LoginUser User user ) {
		logger.debug("addCartItem() 실행됨");
		logger.info("장바구니에 저장할 상품번호" + productNo);
		
		//이렇게 getAttribute로 걍 얻어올 수 있다.
		User user = (User) SessionUtils.getAttribute("LOGINED_USER");
		logger.info("로그인된 사용자 정보: " + user);
		if(user == null) {
			throw new RuntimeException("장바구니 담기는 로그인 후 사용가능한 서비스입니다.");
		}
		
		CartItem cartItem = new CartItem();  
		cartItem.setUserId(user.getId());
		cartItem.setProductNo(productNo);
		
		productService.addCartItem(cartItem);		
		
		logger.debug("addCartItem() 종료됨");
		return "redirect:cart";
	}
	
	@GetMapping("/cart")
	//@LoginUser가 있네? 세션에서 찾아야 겠다.
	//화면에 표출해야 하니까 Model이 필요하겠네 해서 Model
	public String cart(@LoginUser User user, Model model) {
		logger.debug("cart() 실행됨");
		logger.info("로그인된 사용자 정보: " + user);
		if (user == null) {
			throw new RuntimeException("장바구니 조회는 로그인 후 사용가능한 서비스 입니다.");
		}
		List<Map<String, Object>> items = productService.getMyCartItems(user.getId());
		logger.info("조회된 장바구니 아이템 목록: " + items);
		model.addAttribute("items", items);
		
		logger.debug("cart() 종료됨");
		return "product/cart";
	}
}
