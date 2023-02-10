package com.vti.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.LoginDto;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class Logincontroller {
	@Autowired
	private IAccountService accountService;

	@GetMapping
	public ResponseEntity<?> getLogin(Principal principal) {
		String username = principal.getName();

//		tu thong tin username lay duoc ==> tim thong tin chi tiet cua user dang nam duoi DB
		Account accountLogin = accountService.getAccountByUsername(username);
		LoginDto loginDto = new LoginDto();
		loginDto.setId(accountLogin.getId());
		loginDto.setFullname(accountLogin.getFullname());
		return new ResponseEntity<>(loginDto, HttpStatus.OK);
	}

}
