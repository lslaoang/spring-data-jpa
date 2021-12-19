package io.lao.alloutjpa.view;

import io.lao.alloutjpa.dao.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookView {

    private String id;
    private String name;
    private Genre genre;

}
