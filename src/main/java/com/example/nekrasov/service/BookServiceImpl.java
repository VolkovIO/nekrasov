package com.example.nekrasov.service;

import com.example.nekrasov.dto.BookDTO;
import com.example.nekrasov.entity.Book;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.BookDTOFactory;
import com.example.nekrasov.repository.BookRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDTOFactory bookDTOFactory;


    @Override
    public List<BookDTO> listBook() {
        List<Book> all = bookRepository.findAll();
        return bookDTOFactory.createBookDTOList(all);
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Книга с идентификатором \"%s\" не найден.", id)));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book replaceBook(Book book, Long id) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
