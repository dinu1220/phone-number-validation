package com.phonenumber.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber implements Serializable{

    @JsonProperty("phone_numbers")
    private List<String> phoneNumbers;

    @JsonProperty("total_phone_numbers_count")
    private int totalPossiblePhoneNumbers;

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public int getTotalPossiblePhoneNumbers() {
        return totalPossiblePhoneNumbers;
    }

    public void setTotalPossiblePhoneNumbers(int totalPossiblePhoneNumbers) {
        this.totalPossiblePhoneNumbers = totalPossiblePhoneNumbers;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumbers=" + phoneNumbers +
                ", totalPossiblePhoneNumbers=" + totalPossiblePhoneNumbers +
                '}';
    }
}
