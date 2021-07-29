package com.sample.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.dto.CartItemDto;
import com.sample.dto.CartItemListDto;
import com.sample.service.CartService;
import com.sample.vo.CartItem;
import com.sample.vo.User;
import com.sample.web.annotation.LoginUser;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired CartService cartService;
	
	// 실제 요청 URL : localhost/spring-mvc/product/addCart?no=32
	@GetMapping("/add")
	public String addCartItem(@RequestParam("no") int productNo, @LoginUser User user) {
		CartItem cartItem = new CartItem();
		cartItem.setUserId(user.getId());
		cartItem.setProductNo(productNo);
		
		cartService.addCartItem(cartItem);		
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String carts(@LoginUser User user, Model model) {
		List<CartItemDto> items = cartService.getMyCartItems(user.getId());
		CartItemListDto cartItemListDto = new CartItemListDto();
		cartItemListDto.setItems(items);
		model.addAttribute("cartItemList", cartItemListDto);		
		
		return "cart/list";
	}
	
	@GetMapping("/delete")
	public String deleteCartItem(@RequestParam("no") int itemNo, @LoginUser User user) {
		cartService.removeCartItem(itemNo, user.getId());
		
		return "redirect:list";
	}
}
