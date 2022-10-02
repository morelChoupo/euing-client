package com.ufi.euing.client.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApi.class);
}
