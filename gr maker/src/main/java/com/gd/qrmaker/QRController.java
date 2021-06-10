package com.gd.qrmaker;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.zxing.WriterException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Controller
public class QRController {
	@Autowired QRService qrService;
	
	@GetMapping("/getInfo") //get방식으로 getInfo 로 접속하볌ㄴ
	public String getInfo (Model model) throws WriterException, IOException { //아무것도 안받고
		log.debug("0. 콘트롤러 실행 param 없음");
		log.debug("1. param없음");
		String url = qrService.getUserName(); //서비스 getUserName으로 기존에 입력되었던 String을 받는다.
		log.debug("5. 서비스에서 온 String 확인 : "+url);
		
		model.addAttribute("url", url);
		return "getInfo";
	}
	
	@PostMapping("/getInfo") //get방식으로 getInfo 로 접속하볌ㄴ
	public String getInfo (Model model
						,Info info) throws WriterException, IOException { //아무것도 안받고
		log.debug("0. 콘트롤러 실행 param 확인 : "+ info);
		log.debug("1. param 그대로 : " + info.toString());
		String url = qrService.getUserInfo(info); //서비스 getUserName으로 기존에 입력되었던 String을 받는다.
		log.debug("5. 서비스에서 온 String 확인 : "+url);
		
		model.addAttribute("url", url);
		return "getInfo";
	}
	
	@GetMapping("/QRCheck")
	   public String qrCheck(Model model) {
	      
	      String qrName = "a";
	      model.addAttribute("qrName", qrName);
	      
	      return "QRCheck";
	   }
	   
	   @PostMapping("/QRCheck")
	   public String qrCheck(Model model, Info info) {
	      String qrName = qrService.returnQRname(info);
	      
	      model.addAttribute("qrName", qrName);
	      
	      return "QRCheck";
	   }

	
}
