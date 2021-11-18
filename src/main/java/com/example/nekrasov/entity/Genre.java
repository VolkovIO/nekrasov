package com.example.nekrasov.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Book> books = new ArrayList<>();

    public static Genre makeDefault(String genre){
        return builder()
                .name(genre)
                .books(new ArrayList<>(1))
                .build();
    }
}
