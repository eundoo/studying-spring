package com.sample.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemListDto {

	private List<CartItemDto> items;
	
	public int getTotalAmount() {
		return items.stream().mapToInt(cartItemDto -> cartItemDto.getAmount()).sum();
	}
	
	public int getTotalPrice() {
		return items.stream().mapToInt(cartItemDto -> cartItemDto.getPrice()*cartItemDto.getAmount()).sum();
	}
	
	public int getTotalOrderPrice() {
		return items.stream().mapToInt(cartItemDto -> cartItemDto.getOrderPrice()).sum();
	}
	
	
}
