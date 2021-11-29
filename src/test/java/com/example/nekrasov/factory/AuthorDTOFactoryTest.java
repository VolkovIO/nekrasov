package com.example.nekrasov.factory;

import com.example.nekrasov.entity.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorDTOFactoryTest {

    @Mock
    Author author;

//    @InjectMocks
//    AuthorDTOFactory authorDTOFactory;

    @DisplayName("Создание автора ДТО")
    @Test
    void createAuthorDTO() {
        when(author.getBooks()).thenReturn(new ArrayList<>());
        when(author.getId()).thenReturn(25L);

        author.getBooks();
        author.getId();


        verify(author).getBooks();
        verify(author).getId();

        assertEquals(author.getId(), 25L);
    }


    @Test
    void createAuthorDTOList() {
    }
}