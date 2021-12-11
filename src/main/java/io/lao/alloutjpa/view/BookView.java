package io.lao.alloutjpa.view;

import io.lao.alloutjpa.dao.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    private int id;
    private String name;
    private Genre genre;

}
