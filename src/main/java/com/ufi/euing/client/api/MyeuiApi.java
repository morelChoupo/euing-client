package com.ufi.euing.client.api;


import com.ufi.euing.client.entity.MessageApi;
import com.ufi.euing.client.entity.TransactionDetailsEntity;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.utils.others.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myeui")
public class MyeuiApi {

    private static final Logger log = LoggerFactory.getLogger(MyeuiApi.class);

    MessageApi message = new MessageApi();

    final Tools tools;

    public MyeuiApi(Tools tools) {
        this.tools = tools;
    }

    @PostMapping("/sendTrx")
    public synchronized MessageApi sendTransaction(TransactionDetailsEntity transactionDetails) throws EmailNotFoundException {
        return tools.addMobileCashIn(transactionDetails);
    }
}
