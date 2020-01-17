package br.com.wallace.worstmovie.data.models;


import br.com.wallace.worstmovie.data.enums.ReturnStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Default response for api errors.
 *
 * @author Wallace Silva
 * @version 1.0
 * @since 2020-01-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private ReturnStatusEnum status;
    private String error;
    private String message;
    private String path;
}
