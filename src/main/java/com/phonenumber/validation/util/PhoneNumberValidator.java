package com.phonenumber.validation.util;

import com.phonenumber.validation.exception.InvalidPhoneNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PhoneNumberValidator {

    Logger logger = LoggerFactory.getLogger(PhoneNumberValidator.class);

    static Set<String> uniqueCombinations = new HashSet<>();

    public boolean validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {

        logger.info("inside validatePhoneNumber()");

        boolean isValid = false;

        if(isNotEmpty(phoneNumber)){
            if(phoneNumber.length() == 7 || phoneNumber.length() == 10){
                isValid = true;
            }else{
                throw new InvalidPhoneNumberException(400,"Length of Phone Number is Invalid",null);
            }
        }else{
            isValid = false;
        }

        return isValid;

    }


    private static boolean isNotEmpty(String phoneNumber){
        return phoneNumber != null && phoneNumber.length() > 0;
    }

    public Set<String> possiblePhoneNumbers(String string) {
        return printPermutation(string,"");
    }
    private Set<String> printPermutation(String string, String permutation) {

        if(string.length()==0){
            uniqueCombinations.add(permutation);
        }
        for (int i = 0; i < string.length(); i++) {
            char toAppendToPermutation = string.charAt(i);
            String remaining = string.substring(0, i) + string.substring(i + 1);
            printPermutation( remaining,permutation + toAppendToPermutation);
        }
        return uniqueCombinations;
    }


}
