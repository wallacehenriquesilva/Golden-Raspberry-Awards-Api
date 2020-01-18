package br.com.wallace.worstmovie.services;

import br.com.wallace.worstmovie.data.entities.MovieEntity;
import br.com.wallace.worstmovie.data.entities.ProducerEntity;
import br.com.wallace.worstmovie.data.entities.StudioEntity;
import br.com.wallace.worstmovie.data.models.MovieProducerModel;
import br.com.wallace.worstmovie.data.models.ResponseModel;
import br.com.wallace.worstmovie.data.repositories.MovieRepository;
import br.com.wallace.worstmovie.data.repositories.ProducerRepository;
import br.com.wallace.worstmovie.data.repositories.StudioRepository;
import br.com.wallace.worstmovie.exceptions.NoProducersIntervalException;
import com.wallace.javapow.readers.CsvReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * Service class for movie management.
 * </p>
 */
@Component
public class MovieService {

    @Value("${csv.path}")
    private String csvPath;

    private final MovieRepository movieRepository;
    private final StudioRepository studioRepository;
    private final ProducerRepository producerRepository;

    public MovieService(MovieRepository movieRepository, StudioRepository studioRepository, ProducerRepository producerRepository) {
        this.movieRepository = movieRepository;
        this.studioRepository = studioRepository;
        this.producerRepository = producerRepository;
    }


    /**
     * <p>
     * Method responsible for generate a producers list with the interval between the shorter e longer time.
     * </p>
     *
     * @return Return the interval response object.
     */
    public ResponseModel getProducersIntervals() {
        final MovieProducerModel movieProducerModelMax = movieRepository.getIntervals()
                .stream()
                .max(Comparator.comparing(MovieProducerModel::getInterval))
                .orElseThrow(NoProducersIntervalException::new);

        final MovieProducerModel movieProducerModelMin = movieRepository.getIntervals()
                .stream()
                .min(Comparator.comparing(MovieProducerModel::getInterval))
                .orElseThrow(NoProducersIntervalException::new);

        return ResponseModel
                .builder()
                .max(Collections.singleton(movieProducerModelMax))
                .min(Collections.singleton(movieProducerModelMin))
                .build();
    }


    /**
     * <p>
     * This method is responsible for init all the application, loading the csv file and persist the informations.
     * </p>
     */
    @PostConstruct
    public void init() {
        List<MovieEntity> moviesList = new CsvReader(MovieEntity.class).read(csvPath);
        Set<ProducerEntity> productorsSet = new HashSet<>();
        Set<StudioEntity> studiosSet = new HashSet<>();

        moviesList
                .forEach(movieEntity -> {
                    productorsSet.addAll(movieEntity.getProducers());
                    studiosSet.addAll(movieEntity.getStudios());
                });

        movieRepository.saveAll(moviesList);

        productorsSet
                .forEach(producerEntity -> producerEntity
                        .getMovies()
                        .addAll(moviesList
                                .stream()
                                .filter(movieEntity -> movieEntity.getProducers()
                                        .stream()
                                        .anyMatch(producer -> producer.getName().equalsIgnoreCase(producerEntity.getName())))
                                .collect(Collectors.toSet())));

        producerRepository.saveAll(productorsSet);

        studiosSet
                .forEach(studioEntity -> studioEntity
                        .getMovies()
                        .addAll(moviesList
                                .stream()
                                .filter(movieEntity -> movieEntity.getStudios()
                                        .stream()
                                        .anyMatch(studio -> studio.getName().equalsIgnoreCase(studioEntity.getName())))
                                .collect(Collectors.toSet())));

        studioRepository.saveAll(studiosSet);
    }
}