package com.sample.web.advice;

import com.sample.web.model.response.ResponseData;
import com.sample.web.model.service.ResponseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@Autowired
    private ResponseDataService responseDataService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData handleDefaultException(Exception ex) {
    	ex.printStackTrace();
        return responseDataService.getFailResponseData("서버에서 오류가 발생하였습니다.");
    }
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData handleRuntimeException(Exception ex) {
    	ex.printStackTrace();
        return responseDataService.getFailResponseData(ex.getMessage());
    }
}
