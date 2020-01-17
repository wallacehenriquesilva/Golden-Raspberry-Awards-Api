package br.com.wallace.worstmovie.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {
    private Set<MovieProducerModel> min;
    private Set<MovieProducerModel> max;
}
