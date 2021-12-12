package io.lao.alloutjpa.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@Entity
@Table(name = "BOOK")
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
    private User user;

    public JpaBook(int id, String name, Genre genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public JpaBook(){}
}
