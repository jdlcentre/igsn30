package org.csiro.igsn.web.controllers;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginCtrl {
	
	static final Logger log = Logger.getLogger(LoginCtrl.class);
	
	@RequestMapping("getUser.do")
	public ResponseEntity<Principal>  user(Principal user) {
		
		if(user != null){
			return new ResponseEntity<Principal>(user,HttpStatus.OK);
		}else{
			return new ResponseEntity<Principal>(user,HttpStatus.UNAUTHORIZED);
		}
	}
	


}
