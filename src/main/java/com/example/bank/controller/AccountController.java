package com.example.bank.controller;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/makeAccount")
    @ResponseBody
    public String makeAccount(@RequestParam("id") String id,
                              @RequestParam("name") String name,
                              @RequestParam("balance") Integer balance,
                              @RequestParam("type") String type,
                              @RequestParam(name = "grade", required = false) String grade) {
        try {
            accountService.makeAccount(new Account(id, name, balance, type, grade));
            return "계좌개설 성공";
        } catch (Exception e) {
            e.printStackTrace();
            return "계좌개설 실패";
        }
    }
}
