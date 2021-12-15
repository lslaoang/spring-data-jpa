package io.lao.alloutjpa.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
public class JpaBook {

    @Id
    @Column
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    private Genre  genre;

    @ManyToOne
    @JoinColumn(name = "student", nullable = false)
    private JpaStudent jpaStudent;

    public JpaBook(int id, String name, Genre genre, JpaStudent jpaStudent) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.jpaStudent = jpaStudent;
    }


}
