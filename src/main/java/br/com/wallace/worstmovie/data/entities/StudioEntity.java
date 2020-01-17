package br.com.wallace.worstmovie.data.entities;

import br.com.wallace.javapow.annotations.CsvColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@Table(name = "studio", indexes = {@Index(
        name = "studio_name_uk",
        columnList = "studio_name"
)})
@NoArgsConstructor
@AllArgsConstructor
public class StudioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "studio_sequence",
            sequenceName = "studio_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studio_sequence"
    )
    private Long id;

    @CsvColumn(name = "studios")
    @Column(name = "studio_name", nullable = false, unique = true, length = 100)
    private String name;

    @JsonIgnore
    @ManyToMany
    private Set<MovieEntity> movies = new HashSet<>();
}