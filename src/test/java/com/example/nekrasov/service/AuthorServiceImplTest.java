package com.example.nekrasov.service;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.factory.AuthorDTOFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

//    @Spy
//    AuthorRepository authorRepository;
//
    @Mock
    AuthorDTOFactory authorDTOFactory;

    @Mock
    AuthorServiceImpl authorService;

    @Mock
    AuthorDTO authorDTO;

    @Test
    void listAuthor() {
    }

    @Test
    void getAuthor() {

        when(authorService.getAuthor(1L)).thenReturn(authorDTO);
//        when(authorService.getAuthor(1000L)).thenThrow(new NotFoundException("Not Founded"));

        authorService.getAuthor(1L);
//        authorService.getAuthor(1000L);

        verify(authorService).getAuthor(1L);
//        verify(authorService).getAuthor(1000L);
    }

    @Test
    void addAuthor() {
    }

    @Test
    void replaceAuthor() {
    }

    @Test
    void remove() {
    }
}