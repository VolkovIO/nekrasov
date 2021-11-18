package com.example.nekrasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public static Author makeDefault(String authorName){
        return builder()
                .name(authorName)
                .books(new ArrayList<>(1))
                .build();
    }
}
