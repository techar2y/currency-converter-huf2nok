package com.arty.currencyConversion.controllers;

import com.arty.currencyConversion.domains.Currency;
import com.arty.currencyConversion.utils.CurrencyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/currency")
public class CurrencyController
{
    @Autowired
    private CurrencyUtils currencyUtils;

    @GetMapping("/getCurrencyRate")
    public ResponseEntity<Map<String, Object>> getCurrencyRate() {
        try {

            String xmlContent = currencyUtils.requestCbr();
            Map<String, Object> data = currencyUtils.xmlToCurrency(xmlContent);

            if(data.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCurrencyHufNok")
    public ResponseEntity<List<Currency>> getCurrencyHufNok() {
        try {
            String xmlContent = currencyUtils.requestCbr();

            Map<String, Object> data = currencyUtils.xmlToCurrency(xmlContent);
            if(((List<Currency>)data.get("currencies")).size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            List<Currency> cur = currencyUtils.getCurrencyHufNok((List<Currency>)data.get("currencies"));

            return new ResponseEntity<>(cur, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
