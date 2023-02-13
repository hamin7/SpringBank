package com.example.bank.controller;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private AccountService accountService;

    @PostMapping("/makeAccount")
    @ResponseBody
    public ResponseEntity<String> makeAccount(@ModelAttribute Account acc) {
        try {
            accountService.makeAccount(acc);
            return new ResponseEntity<String>("계좌개설 성공", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("계좌개설 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accountInfo/{id}")
    @ResponseBody
    public ResponseEntity<Account> accountInfo(@PathVariable String id) {
        ResponseEntity<Account> res = null;
        try {
            Account acc = accountService.accountInfo(id);
            res = new ResponseEntity<Account>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(
            @RequestParam("id") String id,
            @RequestParam("money") Integer money) {
        ResponseEntity<Account> res = null;
        try {
            Account acc = accountService.deposit(id, money);
            res = new ResponseEntity<Account>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(
            @RequestParam("id") String id,
            @RequestParam("money") Integer money) {
        ResponseEntity<Account> res = null;
        try {
            Account acc = accountService.withdraw(id, money);
            res = new ResponseEntity<Account>(acc, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Account>> allAccount() {
        ResponseEntity<List<Account>> res = null;
        try {
            List<Account> accs = accountService.accountList();
            log.info(accs.toString());
            res = new ResponseEntity<List<Account>>(accs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<List<Account>>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
