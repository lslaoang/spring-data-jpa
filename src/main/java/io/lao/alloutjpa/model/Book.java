package io.lao.alloutjpa.model;

import io.lao.alloutjpa.dao.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String id;
    private String name;
    private Genre genre;

}
