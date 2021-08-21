package com.sample.service;

import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.vo.Product;

//new로 뭘 생성하면 안됨 이유는 spring안에 만들어져 있는 객체 중에서 테스트를 해야되는거아니야
/*
 * @RunWith
 * 		- 단위 테스트 케이스 실행시 같이 실행될 클래스를 지정한다.
 * 		- 보통 단위 테스트 케이스 실행을 지원하는 헬프 클래스가 지정된다.
 * 		- SpringJUnit4ClassRunner는 jUnit으로 스프링 컨테이너에서 생성된 빈을 테스트 할 수 있도록
 * 			지원한다.
 */
@RunWith(SpringJUnit4ClassRunner.class)

/*
 * @ContextConfiguration
 * 		- locations로 지정한 스프링 빈 설정정보를 로딩해서 스프링 컨테이너를 생성한다.
 * 		- 생성된 스프링 컨테이너는 스프링 빈 설정정보를 분석해서 객체를 생성하고, 스프링의 빈으로 추가한다.
 */
@ContextConfiguration(locations = "classpath:/spring/context-mybatis.xml")
public class TestProductService {

	//ProductService에 있는 기능들을 테스트 하고싶음
	//getAllProductList insertNewProduct addProduct를 테스트 할거임
	
	//먼저 ProductService를 불러와야 될거 아니야
	@Autowired
	ProductService productService;
	
	/*
	 * @Test
	 * 		- 단위 테스트 케이스에 정의된 메소드 단위 테스트에 참여하게 한다.
	 * 
	 * @Ignore
	 * 		- 단위 테스트 대상에서 제외시킨다.
	 */
	@Test
	@Ignore
	public void testConfig() {
		//단언하기 - assertNotNull(객체) 해당 객체가 null이 아닐 거라고 확신한다.!!
		//							   해당 객체가 null이면 이 테스트는 실패가 된다.
		Assert.assertNotNull(productService);
	}
	
	//getAllProducts가 잘 돌아가는지 확인하기 -> 실제 디비에서 디비에 저장되있는 것들을 잘 갖고오나 테스트
	@Test
	public void testGetAllProducts() {
		List<Product> products = productService.getAllProductList();
		Assert.assertEquals(34, products.size());
	}
	
	@Test
	public void testAddProductByNo() {
		Product prevProduct = productService.getProductDetail(31);
		productService.addProduct(31, 10);
		Product nextProduct = productService.getProductDetail(31);
		
		//prevProduct에서 재고량을 10개 증가시키면 .addProduct로 10개 증가시킨
		//nextProduct랑 같을것이다.!!!!
		//Assert.assertEquals(예상값, 결과값)
		Assert.assertEquals(prevProduct.getStock() + 10, nextProduct.getStock())
	}
}
