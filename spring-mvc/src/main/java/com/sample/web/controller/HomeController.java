package com.sample.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.exception.SampleException;
import com.sample.service.UserService;
import com.sample.vo.User;
import com.sample.web.form.UserRegisterForm;
import com.sample.web.utils.SessionUtils;

/*
 * @Controller
 * 		- 클라이언트의 요청을 처리하는 컨트롤러 클래스임을 나타낸다.
 * 		- <context:component-scan /> 태그를 xml에 설정했을 때 자동으로 스프링 컨테이너의 빈으로 등록된다.
 */
@Controller
public class HomeController {
	//HomeController 클래스에 대한 로그를 출력하는 
	private static Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@ExceptionHandler(SampleException.class)
	public String handleSampleException(SampleException ex, Model model) {
		model.addAttribute("error", ex);	//model에 error라는 이름으로 담았으니까 form.jsp에서 error.로 찾을 수 있다.
		if("ERR_USER_001".equals(ex.getCode())) {
			return "form";	//redirect안하는 이유는 form으로 하면 정보를 다 담아서 줄 수 있기 때문에 여기 2-3시 강의 다시 듣기
		} else if("ERR_USER_002".equals(ex.getCode())) {
			return "loginform";
		}
		return "error/server";
	}
//	@ExceptionHandler(DuplicatedIdAndEmailexception.class)
//	public String handleDuplicatedIdAndEmailException() {
//		logger.error("##### 예외가 발생하였습니다.");
//		return null;
//	}
	//예외들 마다 이렇게 만들 수 있다.
	//UserNotFoundException이 RuntimeException과 Exception의 자손이다. 모든 Exception은 이 둘 중 하나 무조건 자손이다.

	/*
	 * @RequestMapping, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
	 * 		- 요청URL와 요청핸들러 메소드를 매핑시킨다.
	 * 		- 요청URL은 "/"로 시작한다.(절대경로 이런거 아님, 그냥 경로라는 것 나타내기 위해서 단순하게 붙인 것임)
	 * 		- @RequestMapping은 메소드, 클래스 두 군데 붙일 수 있고, 나머지는 메소드에만 붙일 수 있다.
	 * 		- 다양한 속성값을 가질 수 있다.
	 * 			* path 혹은 value
	 * 					요청핸들러 메소드와 매핑되는 요청 URL을 지정할 때 사용한다.
	 * 					2개 이상의 요청URL과 매핑되는 경우 배열형식으로 요청URL을 정의한다.
	 * 					작성예
	 * 						@RequestMapping("/home")
	 * 						@RequestMapping(value = "/home")
	 * 						@RequestMapping(path = "/home")
	 * 						@RequestMapping(value = {"/home"}) 
	 * 						@RequestMapping(path = {"/home"}) 
	 * 						@RequestMapping(value = {"/", "/home", "/main"}) 
	 * 						@RequestMapping(path = {"/", "/home", "/main"}) 
	 */
	
	/*
	 * Model
	 * 		- 뷰객체 혹은 뷰페이지(JSP)에 값을 전달할 때 사용하는 객체다.
	 * 		- 요청핸들러 메소드의 매개변수로 Model객체를 정의하면 HandlerAdapter가 
	 * 		  요청핸들러 호출할 때 Model객체를 전달해준다.
	 * 		- 요청핸들러 메소드에서 Model객체에 값 혹은 객체를 저장하면 HttpServletRequest객체의
	 * 		  속성으로 옮겨진다.(JSP 기반의 웹 애플리케이션에 한함)
	 */
	@GetMapping(path = {"/", "/home"})
	public String home() {
		logger.info("홈페이지 요청을 처리함");
		// 뷰 페이지에 값 전달하기
		
		return "home";	// /WEB-INF/views/home.jsp 경로에서 "/WEB-INF/views/"와 ".jsp"를 제외한 이름
	}
	/*
	 * 요청핸들러 메소드의 반환값
	 * 		- String 
	 * 			* 내부이동할 JSP페이지의 이름을 반환한다.
	 * 				내부이동할 JSP페이지의 이름은 /WEB-INF/views/ 폴더에 있는 jsp 파일의 파일명이다.
	 * 				내부이동할 JSP페이지가 /WEB-INF/views/home.jsp인 경우는 "home"다.
	 * 				내부이동할 JSP페이지가 /WEB-INF/views/board/list.jsp인 경우는 "board/list"다
	 * 				내부이동하는 경로를 적을 때는 "/"로 시작할 필요가 없음
	 * 				InternalResourView, JstlView가 해당 경로의 jsp로 내부이동시킨다.
	 * 			* 클라이언트가 재요청할 URL을 반환한다.
	 * 				재요청할 URL은 "redirect:"로 시작한다.
	 * 				"redirect:list"와 "redirect:/list"는 서로 다른 경로다.
	 * 				"redirect:list"는 상대주소표기법으로 작성된 경로다.
	 * 				"redirect:/list"는 절대주소표기법으로 작성된 경로다.
	 * 				RedirectView가 해당 URL을 재요청URL로 클라이언트에게 응답으로 보낸다.
	 * 			작성예
	 * 				@Controller
	 * 				@RequestMapping("/board")
	 * 				public class BoardController {
	 * 					
	 * 					@GetMapping("/list")
	 * 					public String boardList() {
	 * 						...
	 * 						return "board/list";	// 실제 jsp 경로는 /WEB-INF/views/board/list.jsp다
	 * 					}
	 * 			
	 * 				}
	 * 			
	 * 		- VO객체, List객체, Map객체
	 * 			* JSP 페이지로 이동하는 것이 아니다.
	 * 			* 요청 핸들러 메소드가 반환하는 값이 직접 응답으로 클라이언트에게 전달된다.
	 * 			* 주로 JSON, XML 형태로 변환되어서 전달된다.
	 * 			* 대부분의 경우, 컨트롤러 클래스에 @Controller 대신 @RestController이 지정되어 있다.
	 * 			작성예
	 * 				@RestController
	 * 				@RequestMapping("/board")
	 * 				public class BoardController {
	 * 
	 * 					@GetMapping("/detail")
	 * 					public Board getBoardDetail() {...}
	 * 
	 * 					@GetMapping("/list")
	 * 					public List<Board> getBoardList() {...}
	 * 				}
	 */
	
	//요청URL : localhost/spring-mvc/register
	//회원가입화면으로 내부이동하는 요청핸들러 메소드
	@GetMapping("/register")
	public String registerform() {
		//sysout은 이제 우리 인생에서 적을 일이 없고 이제부터 다 logger로 한다.
		logger.debug("registerform() 샐행됨");
		logger.info("회원가입폼 요청을 처리함");
		logger.debug("registerform() 종료됨");
		//jsp중에서 form으로 이동한다.
		return "form";
	}
	/*
	@PostMapping("/register")
	public String register(@RequestParam("id") String userId,
			@RequestParam("password") String userPassword,
			@RequestParam("passwordConfirm") String userPasswordConfirm,
			@RequestParam("name") String userName,
			@RequestParam("email") String userEmail,
			@RequestParam("phone") String userPhone) {
		logger.debug("register() 실행됨");
		logger.info("회원정보를 등록함");
		
		logger.debug("register() 종료됨");
		return "redirect:home";
	}
	*/
	
	//위 처럼 안적고 아래처럼 적음 근데 이것도 짜증나서 com.sample.web.form만들음
	/*
	@PostMapping("/register")
	public String register(String id, String password, String passwordConfirm,
				String name, String email) {
		logger.debug("register() 실행됨");
		logger.info("회원정보를 등록함");
		
		logger.debug("register() 종료됨");
		return "redirect:home";
	}
	*/
	
	//spring에서는 form에 입력값는 방법이 이렇게 바뀜!
	//com.sample.web.form에 form클래스를 만들고 변수명을 jsp에 입력필드에 name변수명과 똑같이 만들어
	//vo로 만들 수 도 있는데 변수명이 항상 일치하지 않기 때문에 form을위한 클래스를 따로 만든다.
	//form에서 한꺼번에 받고 vo로 쪼개서 가져가는거야
	//그러나 String은 값을 안넣으면 null로되고 int는 값을 안넣으면 오류난다.
	
	//**그래서 이제부터 입력값을 뽑아올 필요가 없고 form에 입력값을 vo에 담는거랑 입력값에 대한 유효성체크 !
	//컨트롤러가 더 가벼워졌다.!
	@PostMapping("/register")
	public String register(UserRegisterForm userRegisterForm) {
		logger.debug("register() 실행됨");
		logger.info("회원가입정보: " + userRegisterForm);
		
		// User객체를 생성하고, UserRegisterForm의 값을 User객체로 복사한다.
		User user = new User();
		BeanUtils.copyProperties(userRegisterForm, user);
		
		// UserService의 registerUser(user)를 호출해서 업무로직을 수행한다. 
		userService.registerUser(user);
				
		logger.info("회원정보 등록 요청을 처리함");
		logger.debug("register() 종료됨");
		
		return "redirect:home";
	}
	
	@GetMapping("/login")
	public String loginform() {
		return "loginform";
	}
	
	//세개 중 어떤 방식으로 하든 상관없음
	@PostMapping("/login")
	public String login(@RequestParam("id") String userId,
			@RequestParam("password") String userPassword) {
		logger.debug("login() 실행됨");
		logger.info("로그인하는 사용자의 아이디: " + userId);
		logger.info("로그인하는 사용자의 비밀번호: " + userPassword);
		
		userService.login(userId, userPassword);
		logger.debug("login() 종료됨");
		
		// 로그인 전 페이지로 되돌아가기
		String returnPath = (String) SessionUtils.getAttribute("returnPath");
		SessionUtils.removeAttribute("returnPath");
		if (returnPath != null) {
			return "redirect:" + returnPath;
		}
		
		return "redirect:home";
	}
	
//	@PostMapping("/login")
//	public String login(String id, String password) {
//		
//	}
//	
//	@PostMapping("/login")
//	public String login(User user) {
//		
//	}
	
	@GetMapping("/logout")
	public String logout() {
		SessionUtils.destorySession();
		return "redirect:home";
	}
	
	
}
