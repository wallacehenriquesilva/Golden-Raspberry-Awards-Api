package br.com.wallace.worstmovie.data.entities;


import br.com.wallace.javapow.annotations.Csv;
import br.com.wallace.javapow.annotations.CsvBooleanColumn;
import br.com.wallace.javapow.annotations.CsvCollectionColumn;
import br.com.wallace.javapow.annotations.CsvColumn;
import br.com.wallace.javapow.enums.ColumnTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Table(name = "movies", indexes = {@Index(
        name = "movie_title_uk",
        columnList = "movie_title"
)})
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "movies_sequence",
            sequenceName = "movies_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movies_sequence"
    )
    private Long id;

    @CsvColumn(name = "title")
    @Column(name = "movie_title", nullable = false, length = 100)
    private String title;

    @CsvColumn(name = "year", type = ColumnTypeEnum.INTEGER)
    @Column(name = "movie_year", nullable = false)
    private int year;


    @Column(name = "movie_winner", nullable = false)
    @CsvBooleanColumn(name = "winner", trueValues = {"yes", "true"})
    private boolean winner;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies", cascade = CascadeType.MERGE)
    @CsvCollectionColumn(name = "producers", collectionDelimiterRegex = ",| and")
    private Set<ProducerEntity> producers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "movies", cascade = CascadeType.MERGE)
    @CsvCollectionColumn(name = "studios", collectionDelimiterRegex = ",| and")
    private Set<StudioEntity> studios = new HashSet<>();
}