package com.dataloaderportal.datauploading.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.dataloaderportal.datauploading.dto.ValidatingDTO;


@FeignClient(name = "auth-client", url="${Authorization.URL}")
public interface AuthClient {
	
	@GetMapping(value = "/validate")
	ValidatingDTO getValidity(@RequestHeader(name="Authorization",required = true) String token);

}
