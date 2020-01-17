package br.com.wallace.worstmovie.data.repositories;

import br.com.wallace.worstmovie.data.entities.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    boolean existsByName(String name);

    Optional<StudioEntity> findFirstByName(String name);
}