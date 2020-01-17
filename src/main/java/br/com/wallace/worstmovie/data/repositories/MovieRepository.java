package br.com.wallace.worstmovie.data.repositories;

import br.com.wallace.worstmovie.data.entities.MovieEntity;
import br.com.wallace.worstmovie.data.models.MovieProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("SELECT new br.com.wallace.worstmovie.data.models.MovieProducerModel(p.name, MAX(m.year), MIN(m.year),  (MAX(m.year) - MIN(m.year)))  "
            + "FROM MovieEntity m INNER JOIN m.producers p "
            + "WHERE m.winner = TRUE "
            + "GROUP BY p.name "
            + "having count (m.title) > 1 ")
    Set<MovieProducerModel> getIntervals();
}