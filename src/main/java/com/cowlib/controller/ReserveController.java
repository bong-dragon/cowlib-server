package com.cowlib.controller;

import com.cowlib.code.ReserveStatus;
import com.cowlib.model.Reserve;
import com.cowlib.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/callNumbers/{callNumberId}/reserve")
public class ReserveController {

    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping
    public Reserve reserve(Reserve reserve) {
        reserve.setStatus(ReserveStatus.예약함.getCode());
        reserveRepository.insert(reserve);
        return reserve;
    }

    @DeleteMapping
    public Reserve cancel(Reserve reserve) {
        reserve.setStatus(ReserveStatus.취소함.getCode());
        reserveRepository.update(reserve);
        return reserve;
    }
}
