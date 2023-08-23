package com.example.dbservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

    private final Logger exceptionLogger = LoggerFactory.getLogger(ErrorResponseHandler.class.getName());

    @ExceptionHandler(RuntimeBusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleBusinessExceptionInterface(RuntimeBusinessException e, WebRequest requestInfo, HttpServletRequest request) {
        logException(requestInfo, request, e);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getErrorMessage(), e.getErrorCode());

        return new ResponseEntity<>(errorResponseDTO,
                e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException e, WebRequest requestInfo, HttpServletRequest request) {
        logException(requestInfo, request, e);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getErrorMessage(), e.getErrorCode());

        return new ResponseEntity<>(errorResponseDTO,
                e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleThrowable(Throwable e, WebRequest requestInfo, HttpServletRequest request) {
        logException(requestInfo, request, e);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage());

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Log failed request with exception details
     *
     * @param requestInfo WebRequest that result in that Exception
     * @param ex          Exception to be handled
     */
    private void logException(WebRequest requestInfo, HttpServletRequest request, Throwable ex) {
        StringBuilder msg = new StringBuilder();

        Map<String, String> paramMap = readParametersMap(requestInfo);

        msg.append(" Exception: Unable to process this request :  ")
                .append(requestInfo.getDescription(false))
                .append("\nWith Parameters: ")
                .append(paramMap);


        //This will work only if we add RequestBodyCachingFilter to the request filters, which
        //wraps the request as ContentCachingRequestWrapper.
        //for more info refer to RequestBodyCachingFilter javadoc
        if (request != null && request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper req = (ContentCachingRequestWrapper) request;
            String requestBody = new String(req.getContentAsByteArray());

            msg.append("\n>> Request Body: ");
            msg.append(requestBody);
        }


        exceptionLogger.error(msg.toString(), ex);
    }


    private Map<String, String> readParametersMap(WebRequest requestInfo) {
        return requestInfo.getParameterMap().entrySet().stream()
                .map(this::getRequstParamAsStr)
                .collect(Collectors.toMap(Entry<String, String>::getKey, Entry<String, String>::getValue));
    }


    private Entry<String, String> getRequstParamAsStr(Entry<String, String[]> e) {
        return new AbstractMap.SimpleEntry<String, String>(e.getKey(), Arrays.toString(e.getValue()));
    }


    /**
     * Log failed request with exception details
     *
     * @param requestInfo WebRequest that result in that Exception
     * @param ex          Exception to be handled
     */
    private void logException(WebRequest requestInfo, Throwable ex) {
        logException(requestInfo, null, ex);
    }


    private ResponseEntity<Object> createBadRequestHttpResponse(Exception e) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST.name());

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
