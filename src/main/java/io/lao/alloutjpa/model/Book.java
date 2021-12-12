package io.lao.alloutjpa.model;

import io.lao.alloutjpa.dao.Genre;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Book {

    @NonNull
    private int id;
    private String name;
    private Genre genre;
}
