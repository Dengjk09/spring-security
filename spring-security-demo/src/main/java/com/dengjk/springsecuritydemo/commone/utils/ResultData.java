package com.dengjk.springsecuritydemo.commone.utils;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private T verifyData;

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(ReturnCode re,T data) {

        this.code = re.getCode();
        if(re != ReturnCode.SUCCESS ){
            this.msg = data == null ? re.getMsg():data.toString();
            this.data = null;
        }else{
            this.msg = re.getMsg();
            this.data = data;
        }

    }

    public ResultData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultData() { }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultData SUCCESS(Object obj){
        ResultData result = new ResultData(ReturnCode.SUCCESS,obj);
        return result;
    }

    public static ResultData AUTHFAIL(Object obj){
        ResultData result = new ResultData(ReturnCode.AUTH_FAIL,obj);
        return result;
    }
    public static ResultData FAIL(Object obj){
        ResultData result = new ResultData(ReturnCode.FAIL,obj);
        return result;
    }



    public static ResultData ERROR(Object obj){
        ResultData result = new ResultData(ReturnCode.SERVER_ERROR,obj);
        return result;
    }
    public static ResultData ERRORMEG(String msg){
        ResultData result = new ResultData(ReturnCode.SERVER_ERROR,null);
        result.setMsg(msg);
        return result;
    }


    public static ResultData NOTEXIST(Object obj){
        ResultData result = new ResultData(ReturnCode.NOT_EXIST,obj);
        return result;
    }

    public static ResultData PrintServiceError(Object obj){
        ResultData result = new ResultData(ReturnCode.PrintServiceError,obj);
        return result;
    }

    public static ResultData PrintSFTError(Object obj){
        ResultData result = new ResultData(ReturnCode.PrintSFTError,obj);
        return result;
    }

    public T getVerifyData() {
        return verifyData;
    }

    public void setVerifyData(T verifyData) {
        this.verifyData = verifyData;
    }

    public static ResultData CHECK_FAIL(Object obj){
        ResultData result = new ResultData();
        result.setCode(ReturnCode.CHECK_FAIL.getCode());
        result.setMsg(ReturnCode.CHECK_FAIL.getMsg());
        result.setVerifyData(obj);
        return result;
    }

    public static ResultData reDIECT(Object obj){
        ResultData result = new ResultData(ReturnCode.rediect,obj);
        return result;
    }
}
