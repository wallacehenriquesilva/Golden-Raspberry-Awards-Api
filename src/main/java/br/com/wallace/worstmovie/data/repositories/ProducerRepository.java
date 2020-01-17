package br.com.wallace.worstmovie.data.repositories;

import br.com.wallace.worstmovie.data.entities.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {
    boolean existsByName(String name);
}