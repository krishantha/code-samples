package lk.codelabs.springboot.samples.propertyorderexample.excep;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ExceptionResponse exceptionResponse;
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ExceptionResponse> serviceExceptionHandler(JsonMappingException e){
        exceptionResponse.setMessage(e.getMessage().substring(0,e.getMessage().indexOf("\n")));
        exceptionResponse.setCode("Serialize code");
        exceptionResponse.setData(e.toString());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
