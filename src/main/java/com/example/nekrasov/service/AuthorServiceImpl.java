package com.example.nekrasov.service;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.AuthorDTOFactory;
import com.example.nekrasov.repository.AuthorRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDTOFactory authorDTOFactory;

    @Override
    public List<AuthorDTO> listAuthor() {
        List<Author> all = authorRepository.findAll();
        return authorDTOFactory.createAuthorDTOList(all);
    }

    @Override
    public AuthorDTO getAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Автор с идентификатором \"%s\" не найден.", id)));
        return authorDTOFactory.createAuthorDTO(author);
    }

    @Override
    public AuthorDTO addAuthor(Author author) {
        Author save = authorRepository.save(author);
        return authorDTOFactory.createAuthorDTO(save);
    }

    @Override
    public Author replaceAuthor(Author newAuthor, Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setId(newAuthor.getId());
                    author.setName(newAuthor.getName());
                    return authorRepository.save(author);
                })
                .orElseGet(() -> {
                    newAuthor.setId(id);
                    return authorRepository.save(newAuthor);
                });
    }

    @Override
    public void remove(Long id) {
        authorRepository.deleteById(id);
    }
}
