package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String TAG="HTTPController Test : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member("sol", "1234", "email"); //AllArgsConstructor 덕분에 모든 값을 받는 생성자가 만들어졌다.
		Member m = Member.builder().username("sol").password("1234").email("sol@gmail.com").build(); //AllArgsConstructor 덕분에 모든 값을 받는 생성자가 만들어졌다.
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("solecito");
		System.out.println(TAG+"getter : "+m.getUsername());
		return "lombok test 완료";
	}
	

	// http://localhost:8080/http/get    (select)
	@GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) {
		return "get 요청" + m.getId() +", "+ m.getUsername() +", "+m.getPassword()+", "+m.getEmail();
	}

	// http://localhost:8080/http/post   (insert)
	@PostMapping("/http/post")
	//public String postTest(@RequestBody String text) {
	public String postTest(@RequestBody Member m) {
	//return "post 요청" + text;
		return "post 요청" + m.getId() +", "+ m.getUsername() +", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:8080/http/put     (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() +", "+ m.getUsername() +", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:8080/http/delete   (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
