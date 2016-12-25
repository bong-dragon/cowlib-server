package com.cowlib.controller;

import com.cowlib.model.CallNumber;
import com.cowlib.repository.CallNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/callNumbers")
public class CallNumberController {

    @Autowired
    private CallNumberRepository callNumberRepository;

    @PostMapping
    public CallNumber register(CallNumber callNumber) {
        callNumberRepository.insert(callNumber);
        return callNumber;
    }

    @DeleteMapping
    public CallNumber delete(CallNumber callNumber) {
        callNumber.setDeleted(true);
        callNumberRepository.update(callNumber);
        return callNumber;
    }

}
