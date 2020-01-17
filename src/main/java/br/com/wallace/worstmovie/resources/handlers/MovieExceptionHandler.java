package br.com.wallace.worstmovie.resources.handlers;


import br.com.wallace.worstmovie.data.enums.ReturnStatusEnum;
import br.com.wallace.worstmovie.data.models.ApiExceptionResponseModel;
import br.com.wallace.worstmovie.exceptions.NoProducersIntervalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * Class for controller all api exceptions.
 * </p>
 */
@ControllerAdvice
public class MovieExceptionHandler {

    private static final String ERROR_MESSAGE = "No producers found for interval calculation.";

    /**
     * <p>
     * Exception handler which will be called when no producers are found.
     * </p>
     *
     * @param e       Exception
     * @param request Request which the response will be build.
     * @return Return the response entity for the error.
     */
    @ExceptionHandler(NoProducersIntervalException.class)
    public ResponseEntity<ApiExceptionResponseModel> noProducersIntervalException(
            NoProducersIntervalException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiExceptionResponseModel
                        .builder()
                        .message(ERROR_MESSAGE)
                        .error(e.toString())
                        .status(ReturnStatusEnum.NO_PRODUCERS_INTERVAL)
                        .timestamp(new Date().getTime())
                        .path(request.getServletPath())
                        .build());
    }
}