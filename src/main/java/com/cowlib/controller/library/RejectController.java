package com.cowlib.controller.library;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/libs/{ownerId}/reject")
public class RejectController {

    @RequestMapping(method = RequestMethod.POST)
    public void reject(@PathVariable String ownerId, @RequestParam String bookId, @RequestParam String borrowerId) {

    }
}
