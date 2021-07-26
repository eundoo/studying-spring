package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.service.ProductService;
import com.sample.vo.Product;
import com.sample.vo.Review;
import com.sample.vo.User;
import com.sample.web.annotation.LoginUser;
import com.sample.web.view.JsonView;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	/*
	 * ProductService 인터페이스의 구현객체(ProductServiceImpl)가 주입된다.
	 */
	@Autowired ProductService productService;
	
	
	//
	@Autowired JsonView jsonview;
	/**
	 * 모든 상품목록 요청을 처리하는 요청핸들러 메소드 정의
	 * @param model 뷰페이지에 전달할 데이터를 담는 객체, HadlerAdapter객체가 Model를 생성해서 전달함
	 * @return 뷰페이지의 이름
	 */
	@GetMapping("/list")
	public String products(Model model) {
		// 판매중인 전체 상품정보 조회하기
		List<Product> productList = productService.getAllProducts();
		
		// 뷰 페이지에 상품정보 목록 전달하기
		model.addAttribute("products", productList);
		
		// 뷰페이지로 내부이동하기
		// /WEB-INF/views/product/list.jsp로 내부이동해서 JSP 실행시키기
		return "product/list";
	}
	
	// 실제 요청 URL : localhost/spring-mvc/product/detail?no=100
	@GetMapping("/detail")
	
	//productNo를 넘겨받고 Model객체도 전달받음
	public String details(@RequestParam("no") int productNo, Model model) {
		// 상품번호에 해당하는 상품정보 조회
		Product product = productService.getProductDetail(productNo);
		
		// 뷰 페이지에 전달하기 위해서 Model객체에 저장하기
		model.addAttribute("product", product);
		
		return "product/detail";
	}
	
	//ModelAndView를 반환
	@GetMapping("/detail/json")
	public ModelAndView detailsJson(@RequestParam("no") int productNo) {
		Product product = productService.getProductDetail(productNo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", product);	//ModelAndView객체에 모델 (뷰에서 사용하는 데이터)를 담기 data라는 이름으로 담아둔거임
		mav.setView(jsonview);

		return mav;
	}
	
	@PostMapping("/addReview")
	public String addReview(String title, String content, int productNo, @LoginUser User user, RedirectAttributes redirectAttributes) {
		Review review = new Review(title, content, productNo, user.getId());
		productService.addReview(review);

		redirectAttributes.addAttribute("no", productNo);
		
		return "redirect:detail";
	}
	
	@GetMapping("/deleteReview")
	public String deleteReview(@RequestParam("no") int reviewNo, int productNo, @LoginUser User user, RedirectAttributes redirectAttributes) {
		productService.removeReview(reviewNo, user.getId());
		
		redirectAttributes.addAttribute("no", productNo);
		
		return "redirect:detail";
	}
}










