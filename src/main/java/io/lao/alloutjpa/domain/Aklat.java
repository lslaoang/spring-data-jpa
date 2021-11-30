package io.lao.alloutjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AKLAT")
public class Aklat {

    @Id
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    private Genre  genre;
}
