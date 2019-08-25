package me.meodinger.cat.entity;

public class Data {

    private int statusCode;
    private int errorCode;
    private String result;

    public Data setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Data setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Data setResult(String result) {
        this.result = result;
        return this;
    }

    public String getResult() {
        return result;
    }

}
