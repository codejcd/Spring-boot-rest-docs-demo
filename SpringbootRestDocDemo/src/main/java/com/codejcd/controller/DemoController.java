package com.codejcd.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	   @GetMapping("/demo/user/{id}")
	   public ResponseEntity<?> getUserInfoById(@PathVariable("id") String id){

		   HashMap<String, String> map = new HashMap<>();
		   map.put("id", id);
		   map.put("name", "jack");
		   map.put("email", "jack@abc.com");

	       return new ResponseEntity<>(map, HttpStatus.OK);
	   }

	   @PostMapping("/demo/user")
	   public ResponseEntity<?> registUserInfo(@RequestParam HashMap<String, String> map) {
		   return new ResponseEntity<>(map, HttpStatus.OK);
	   }
}
