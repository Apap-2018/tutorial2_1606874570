package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
	@RequestMapping(value = {"/challenge","challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}
		else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
		
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value="a", required = false, defaultValue = "0") String jml_a, 
			@RequestParam(value="b", required = false, defaultValue = "0") String jml_b, Model model) {
		
		//menunjukkan value a dan b pada generator.html
		model.addAttribute("jml_a", jml_a);
		model.addAttribute("jml_b", jml_b);
		
		int val_a = Integer.parseInt(jml_a);
		int val_b = Integer.parseInt(jml_b);
		String only_h = "h";
		//untuk menampung kata "hm"
		String hm = "";
		
		//jika value a lebih dari 1
		if (val_a > 1) {
			//method untuk pengulangan huruf m
			String add_m = String.format("%0" + jml_a + "d", 0).replace("0","m");
			//huruf m yang sudah diulang sesuai value a digabung dengan huruf h dan disimpan
			//di variable hm
			hm = only_h + add_m + " ";
			//pengulangan kata "hm"
			if(val_b <= 1) {
				model.addAttribute("kata_hm", hm);
			}
			else {
				String stc = String.format("%0" + jml_b + "d", 0).replace("0",hm);
				model.addAttribute("kata_hm", stc);
			}
		}
		
		//jika value a hanya 0 atau 1
		else {
			hm = only_h + "m ";
			//pengulangan kata "hm"
			if(val_b <= 1) {
				model.addAttribute("kata_hm", hm);
			}
			else {
				String stc = String.format("%0" + jml_b + "d", 0).replace("0",hm);
				model.addAttribute("kata_hm", stc);
			}
		}
		return "generator";
	}
	
}