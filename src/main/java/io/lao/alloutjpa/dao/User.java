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
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "MGA_AKLAT")
    private List<Aklat> aklatList;

    public User(long id, UserType userType, String name, Department department, String address) {
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.department = department;
        this.address = address;
    }

    public User(){}
}
