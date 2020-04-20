package com.phonenumber.validation.controller;

import com.phonenumber.validation.model.PhoneNumber;
import com.phonenumber.validation.response.ResponseView;
import com.phonenumber.validation.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PhoneController {

    Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneService;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        logger.info("Inside homepage() method");
        return "index";
    }


    @RequestMapping(value="/phone/{pageNumber}",method = RequestMethod.GET)
    public ResponseEntity<?> getPhoneNumbers(@PathVariable Integer pageNumber){
        logger.info("Inside getPhoneNumbers() method");
        ResponseView<?> responseView = phoneService.getPhoneNumbers(pageNumber);

        if(responseView.getErrors() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseView);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(responseView);
        }
    }

    @RequestMapping(value="/phone/all",method = RequestMethod.GET)
    public ResponseEntity<?> getAllPhoneNumbers(){
        logger.info("Inside getPhoneNumbers() method");
        ResponseView<?> responseView = phoneService.getPhoneNumbers(null);

        if(responseView.getErrors() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseView);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(responseView);
        }
    }

    @RequestMapping(value="/phone/add",method = RequestMethod.POST)
    public ResponseEntity<?> addPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        logger.info("Inside addPhoneNumber() method");
        ResponseView<?> responseView = phoneService.addPhoneNumber(phoneNumber);
        if(responseView.getErrors() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseView);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(responseView);
        }
    }

    @RequestMapping(value="/phone/{phoneNumber}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNumber(@PathVariable String phoneNumber){
        logger.info("Inside deleteNumber() method");
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.removePhoneNumber(phoneNumber));

    }
}
