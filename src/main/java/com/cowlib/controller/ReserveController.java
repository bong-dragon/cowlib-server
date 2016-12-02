package com.cowlib.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cowlib.code.ReserveStatus;
import com.cowlib.model.Reserve;
import com.cowlib.repository.ReserveRepository;

@RestController
@RequestMapping("/v1/callNumbers/{callNumberId}/reserve")
public class ReserveController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReserveRepository reserveRepository;

    @PostMapping
    public Reserve reserve(Reserve reserve) {
        reserve.setStatus(ReserveStatus.예약함.getCode());
        Reserve alreadyReserve = reserveRepository.selectByCallNumberIdAndReserverIdAndStatus(reserve);
        if (alreadyReserve != null){
           logger.debug("{}님, {}을 이미 예약 하셨어요", alreadyReserve.getReserverId(), alreadyReserve.getCallNumberId());
            return reserve;
        }

        reserveRepository.insert(reserve);
        return reserve;
    }

    @DeleteMapping
    public Reserve cancel(Reserve reserve) {
        reserve.setStatus(ReserveStatus.취소함.getCode());
        reserveRepository.updateByCallNumberId(reserve);
        return reserve;
    }
}
