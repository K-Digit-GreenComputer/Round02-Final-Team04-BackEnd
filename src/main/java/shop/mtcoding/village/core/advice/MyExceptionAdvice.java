package shop.mtcoding.village.core.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.core.exception.MyConstException;

import io.sentry.Sentry;
import shop.mtcoding.village.core.exception.CustomApiException;
import shop.mtcoding.village.core.exception.CustomException;

import shop.mtcoding.village.core.exception.MyValidationException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.exception.Exception400;


@RestControllerAdvice
public class MyExceptionAdvice {

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ProblemDetail onException400(Exception400 exception400) {
//        return ProblemDetail.forStatusAndDetail(
//                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
//                exception400.getMessage()
//        );
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        Sentry.captureException(e);
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(500, "오류", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception e){

        Sentry.captureException(e);
        String message = e.getMessage();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @ExceptionHandler(Exception400.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> ex401(MyValidationException e){
//        String errMsg = e.getErroMap().toString();
//        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
//        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MyValidationException.class)
    public ResponseEntity<?> error(MyValidationException e){
        Sentry.captureException(e);
        String errMsg = e.getErroMap().toString();
        String devideMsg = errMsg.split("=")[1].split(",")[0].split("}")[0];
        return new ResponseEntity<>(new ResponseDTO<>(-1,devideMsg,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyConstException.class)
    public ResponseEntity<?> error(MyConstException e){
        String detail = e.getMessage();
        ResponseDTO<?> responseDTO = new ResponseDTO<>().fail(400, detail, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomApiException e) {
        Sentry.captureException(e);
        return new ResponseEntity<>(new ResponseDTO<>(-1, e.getMessage(), null), e.getStatus());
    }
}