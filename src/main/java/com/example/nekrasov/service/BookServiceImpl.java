package com.example.nekrasov.service;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.entity.Book;
import com.example.nekrasov.entity.Comment;
import com.example.nekrasov.entity.Genre;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.BookDTOFactory;
import com.example.nekrasov.repository.AuthorRepository;
import com.example.nekrasov.repository.BookRepository;
import com.example.nekrasov.repository.CommentRepository;
import com.example.nekrasov.repository.GenreRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDTOFactory bookDTOFactory;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<BookDTO> listBook() {
        List<Book> books = bookRepository.findAll();
        return bookDTOFactory.createBookDTOList(books);
    }

    @Override
    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Книга с идентификатором \"%s\" не найден.", id)));
        return bookDTOFactory.createBookDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Genre genre = genreRepository.findByName(bookDTO.getGenre());

//        List<Comment> commentList = bookDTO.getComments().stream()
//                .map(comment -> commentRepository.save(new Comment())))
//                .collect(Collectors.toList());

        List<Author> authorList = bookDTO.getAuthors().stream()
                .map(author -> authorRepository.findByName(author))
                .collect(Collectors.toList());

        Book book = Book.builder()
                .name(bookDTO.getName())
                .genre(genre)
                .comments(new ArrayList<Comment>(1)) // пустой список комментрариев
                .authors(authorList)
                .build();

         bookRepository.save(book);

         return bookDTOFactory.createBookDTO(book);
    }

    @Override
    public BookDTO replaceBook(BookDTO bookDTO, Long id) {
        return null;
//        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
