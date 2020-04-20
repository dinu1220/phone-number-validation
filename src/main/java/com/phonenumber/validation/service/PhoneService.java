package com.phonenumber.validation.service;


import com.phonenumber.validation.exception.InvalidPhoneNumberException;
import com.phonenumber.validation.model.PhoneNumber;
import com.phonenumber.validation.response.ErrorDetails;
import com.phonenumber.validation.response.ResponseView;
import com.phonenumber.validation.util.PhoneNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneService {

    Logger logger = LoggerFactory.getLogger(PhoneService.class);
    private final static int pageSize=100;

    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    private static List<String> phoneNumbers = new ArrayList<>();

    public ResponseView<PhoneNumber> addPhoneNumber(PhoneNumber phoneNumber) {
        logger.info("Inside addPhoneNumber() method");
        ResponseView<PhoneNumber> responseView = null;
        if(phoneNumber != null && !phoneNumber.getPhoneNumbers().isEmpty()){
             for(String number : phoneNumber.getPhoneNumbers()){
                 responseView = new ResponseView<>();
                 try {
                     if(phoneNumberValidator.validatePhoneNumber(number)){
                         int total = phoneNumberValidator.possiblePhoneNumbers(number).size();
                         phoneNumbers.addAll(phoneNumberValidator.possiblePhoneNumbers(number));
                         phoneNumber.setPhoneNumbers(phoneNumbers);
                         phoneNumber.setTotalPossiblePhoneNumbers(total);
                         responseView.setDataContent(phoneNumber);
                     }
                 } catch (InvalidPhoneNumberException e) {
                     ErrorDetails errorDetails = new ErrorDetails(e.getErrorCode(),e.getErrorMessage());
                     responseView.setErrors(errorDetails);
                 }
             }
        }
        return responseView;
    }

    public ResponseView<PhoneNumber> getPhoneNumbers(Integer pageNumber){
        logger.info("Inside getPhoneNumbers() method");
        ResponseView<PhoneNumber> responseView = new ResponseView<>();
        PhoneNumber phoneNumber = null;
        if(phoneNumbers.size() > 0){
            phoneNumber = new PhoneNumber();
            /*
             * Pagination
             */
            if(pageNumber != null){
                int startIndex=(pageNumber-1)*pageSize;
                int endIndex=startIndex+pageSize;

                if(endIndex<phoneNumbers.size()){
                    phoneNumber.setPhoneNumbers(phoneNumbers.subList(startIndex,endIndex));
                }else {
                    phoneNumber.setPhoneNumbers(phoneNumbers.subList(startIndex,phoneNumbers.size()));
                }
            }else{
                    phoneNumber.setPhoneNumbers(phoneNumbers);
            }
            phoneNumber.setTotalPossiblePhoneNumbers(phoneNumber.getPhoneNumbers().size());
            responseView.setDataContent(phoneNumber);
        }else{
            ErrorDetails errorDetails = new ErrorDetails(400,"No Phone Numbers Found");
            responseView.setErrors(errorDetails);
        }
        return responseView;
    }

    public ResponseView<PhoneNumber> removePhoneNumber(String phoneNumber){
        logger.info("Inside deleteNumber() method");
        ResponseView<PhoneNumber> responseView = new ResponseView<>();
        phoneNumbers.remove(phoneNumber);
        PhoneNumber pn = new PhoneNumber();
        pn.setPhoneNumbers(phoneNumbers);
        pn.setTotalPossiblePhoneNumbers(phoneNumbers.size());
        responseView.setDataContent(pn);
        return responseView;
    }
}
