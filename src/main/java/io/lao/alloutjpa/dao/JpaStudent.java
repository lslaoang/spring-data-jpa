package io.lao.alloutjpa.dao;

import io.lao.alloutjpa.model.Department;
import io.lao.alloutjpa.model.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "STUDENT")
@Getter
@Setter
@NoArgsConstructor
public class JpaStudent {

    @Id
    private String id;

    @Column
    private UserType userType;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "jpaStudent")
    private List<JpaBook> jpaBookList;


    public JpaStudent(String id, UserType userType, String name, Department department, String address) {
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.department = department;
        this.address = address;
    }
}
