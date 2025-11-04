package com.custard.curemapcommunicationservice.infrastructure.clients;

import com.custard.curemapcommunicationservice.adapter.dto.SuccessApiResponse;
import com.custard.curemapcommunicationservice.infrastructure.clients.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ACCOUNTS")
public interface AccountsFeignClient {

    @GetMapping(value = "/api/v1/account/get-account/{userId}")
    public ResponseEntity<SuccessApiResponse<UserDto>> getAccountDetails(@PathVariable("userId") String userId);

}
