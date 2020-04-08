package br.com.senac;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestaController {
	
	
	@RequestMapping("/oi")
	public String testa() {
		return "TESTA";
	}
	

}
