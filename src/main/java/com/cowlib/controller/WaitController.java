package com.cowlib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.code.WaitStatus;
import com.cowlib.model.Wait;
import com.cowlib.repository.WaitRepository;

@RestController
@RequestMapping("/v1/wait")
public class WaitController {

    @Autowired
    private WaitRepository waitRepository;

    @PostMapping
    public Wait wait(Wait wait) {
        wait.setStatus(WaitStatus.대기함.getCode());
        waitRepository.insert(wait);
        return wait;
    }

    @DeleteMapping
    public Wait cancel(Wait wait) {
        wait.setStatus(WaitStatus.취소함.getCode());
        waitRepository.update(wait);
        return wait;
    }
}
