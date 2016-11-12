package com.cowlib.controller.library;

import com.cowlib.model.CallNumber;
import com.cowlib.repository.CallNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/libs/{ownerId}/callNumber")
public class CallNumberController {

    @Autowired
    private CallNumberRepository callNumberRepository;

    @PostMapping
    public void register(CallNumber callNumber) {
        callNumberRepository.insert(callNumber);
    }

}
