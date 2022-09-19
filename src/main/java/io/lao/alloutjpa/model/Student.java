package io.lao.alloutjpa.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

    private String id;
    private String name;
    private String location;

}
