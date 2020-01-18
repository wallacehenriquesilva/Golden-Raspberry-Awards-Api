package br.com.wallace.worstmovie.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallace.javapow.annotations.Csv;
import com.wallace.javapow.annotations.CsvColumn;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Csv
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@Table(name = "producers", indexes = {@Index(
        name = "producer_name_uk",
        columnList = "producer_name"
)})
@NoArgsConstructor
@AllArgsConstructor
public class ProducerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "producers_sequence",
            sequenceName = "producers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producers_sequence"
    )
    private Long id;

    @CsvColumn(name = "producers")
    @Column(name = "producer_name", nullable = false, unique = true, length = 100)
    private String name;

    @JsonIgnore
    @ManyToMany
    private Set<MovieEntity> movies = new HashSet<>();
}