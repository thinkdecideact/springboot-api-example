package com.tdar.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * RestController Error Handling
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * Parameter Binding Error
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleRRException(MissingServletRequestParameterException e)
    {
        return R.fail("Failed to bind data");
    }

    /**
     * Request method error
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleRRException(HttpRequestMethodNotSupportedException e)
    {
        return R.fail("Wrong request method");
    }


    /**
     * Handle custom errors
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e)
    {
        e.printStackTrace();
        return R.fail("Server errors");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.fail("Not found");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public R handleDuplicateKeyException(DataIntegrityViolationException e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.fail("Database errors: repeated fields, foreign key constraint, etc.");
    }



    /*
    @ExceptionHandler(MyException.class)
    public R handleRRException(MyException e)
    {
        return R.fail(e.getCode(), e.getMsg());
    }
    
    // Permission
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public R unauthorizedException(UnauthorizedException e)
    {
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return R.fail("Lack of permissions");
    }
     */

}