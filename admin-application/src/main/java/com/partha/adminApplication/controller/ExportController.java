package com.partha.adminApplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partha.adminApplication.service.ExportService;

@Controller
public class ExportController {
	
	
	@Autowired
	private ExportService service;
	
	
//	@ResponseBody
//	@GetMapping(value="/download")
//	public ResponseEntity<Resource> download(@RequestParam String exportType,HttpServletResponse resp) throws IOException{
//		ResponseEntity<Resource> response = null ;
//		if("inventoryExport".equalsIgnoreCase(exportType)) {
//			response = service.getInventoryExport();
//		}
//		return response;
//	}
	
	
	@ResponseBody
	@GetMapping(value="/download")
	public ResponseEntity<byte[]> download(@RequestParam String exportType) throws IOException{
		ResponseEntity<byte[]> response = null ;
		if("inventoryExport".equalsIgnoreCase(exportType)) {
			response = service.getInventoryExport();
		}
		return response;
	}

}
