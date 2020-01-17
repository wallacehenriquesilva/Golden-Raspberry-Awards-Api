package br.com.wallace.worstmovie.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieProducerModel {
    private String producer;
    private int followingWin;
    private int previousWin;
    private int interval;
}
