package io.lao.alloutjpa.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.lao.alloutjpa.model.Genre;
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
    private String id;

    @Column()
    private String name;

    @Column()
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private JpaStudent jpaStudent;

    public JpaBook(String id, String name, Genre genre, JpaStudent jpaStudent) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.jpaStudent = jpaStudent;
    }


}
