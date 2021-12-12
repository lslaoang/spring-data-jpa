package io.lao.alloutjpa.view;

import io.lao.alloutjpa.dao.Genre;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    @NonNull
    private int id;
    private String name;
    private Genre genre;

}
