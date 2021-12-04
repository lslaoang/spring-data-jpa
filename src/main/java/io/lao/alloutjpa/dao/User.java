package io.lao.alloutjpa.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
public class User {
    @Id
    @Column
    private long id;

    @Column
    private UserType userType;

    @Column
    private String name;
    @Column
    private String department;
    @Column
    private String address;

    @Column(name = "BOOKS")
    @OneToMany
   private List<Aklat> aklatList;
}
