package com.phonenumber.validation.response;

public class ResponseView<T> {

    private T dataContent;

    private ErrorDetails errors;

    public T getDataContent() {
        return dataContent;
    }

    public void setDataContent(T dataContent) {
        this.dataContent = dataContent;
    }

    public ErrorDetails getErrors() {
        return errors;
    }

    public void setErrors(ErrorDetails errors) {
        this.errors = errors;
    }

    public ResponseView(T dataContent, ErrorDetails errors) {
        this.dataContent = dataContent;
        this.errors = errors;
    }

    public ResponseView(){

    }
}
