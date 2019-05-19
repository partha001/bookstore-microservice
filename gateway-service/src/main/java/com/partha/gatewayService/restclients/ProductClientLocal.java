package com.partha.gatewayService.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile(value="local")
@FeignClient("productService")
public interface ProductClientLocal  extends ProductClient {
	

}
