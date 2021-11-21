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
    public BookDTO addBook(String name, String genre, String[] authors) {
        Genre genreExist = genreRepository.findByName(genre);
        if (genreExist == null) {
            genreExist = genreRepository.save(Genre.builder().name(genre).build());
        }

        List<Author> authorList = new ArrayList<>(1);
        if (authors != null) {
            for (String authorName : authors) {
                if (authorRepository.existsByName(authorName)) {
                    authorList.add(authorRepository.findByName(authorName));
                } else {
                    Author newAuthor = authorRepository.save(Author.makeDefault(authorName));
                    authorList.add(newAuthor);
                }
            }
        }

        Book book = Book.builder()
                .name(name)
                .genre(genreExist)
                .comments(new ArrayList<Comment>(1)) // пустой список комментрариев
                .authors(authorList)
                .build();

        bookRepository.save(book);

        return bookDTOFactory.createBookDTO(book);
    }

    @Override
    public BookDTO replaceBook(BookDTO bookDTO, Long id) {

        // Ищем или создаем жанр
        String genreName = bookDTO.getGenre();
        Genre genreExist = genreRepository.findByName(genreName);
        if (genreExist == null) {
            throw new NotFoundException("Жанр " + genreName + " не существует");
//            genreExist = genreRepository.save(Genre.builder().name(genre).build());
        }

        // Ищем или создаем Авторов
        List<Author> authorList = new ArrayList<>(1);
        if (bookDTO.getAuthors() != null) {
            for (String authorName : bookDTO.getAuthors()) {
                Author authorExist = authorRepository.findByName(authorName);
                if (authorExist == null) {
                    Author newAuthor = authorRepository.save(Author.makeDefault(authorName));
                    authorList.add(newAuthor);
                } else {
                    authorList.add(authorExist);
                }
            }
        }

        // Комментрарии не обрабатывам

        Book newBook = bookRepository.findById(id)
                .map(book -> {
                    book.setGenre(genreExist);
                    book.setAuthors(authorList);
                    book.setName(bookDTO.getName());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> Book.builder()
                        .genre(genreExist)
                        .authors(authorList)
                        .comments(new ArrayList<Comment>(1))
                        .name(bookDTO.getName())
                        .build());

        bookRepository.save(newBook);
        return bookDTOFactory.createBookDTO(newBook);
    }

    @Override
    public void remove(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Книга с идентификатором \"%s\" не найдена.", id)));
        bookRepository.deleteById(id);
    }
}
