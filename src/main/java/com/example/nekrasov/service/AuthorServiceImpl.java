package com.example.nekrasov.service;

import com.example.nekrasov.dto.AuthorDTO;
import com.example.nekrasov.entity.Author;
import com.example.nekrasov.excepsion.BadRequestException;
import com.example.nekrasov.excepsion.NotFoundException;
import com.example.nekrasov.factory.AuthorDTOFactory;
import com.example.nekrasov.repository.AuthorRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@Transactional
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
    public AuthorDTO addAuthor(String authorName) {
        if (authorRepository.existsByName(authorName)) {
            throw new BadRequestException("Автор " + authorName + " уже существует.");
        }

        Author author = authorRepository.saveAndFlush(
                Author.makeDefault(authorName));
        return authorDTOFactory.createAuthorDTO(author);
    }

    @Override
    public AuthorDTO replaceAuthor(String name, Long id) {
        Author authorForDTO = authorRepository.findById(id)
                .map(author -> {
                    author.setName(name);
                    return authorRepository.save(author);
                })
                .orElseGet(() -> {
                    if (authorRepository.existsByName(name)) {
                        throw new BadRequestException("Автор " + name + " уже существует, но не с id " + id);
                    }
                    return authorRepository.saveAndFlush(
                            Author.makeDefault(name));
                });
        return authorDTOFactory.createAuthorDTO(authorForDTO);
    }

    @Override
    public void remove(Long id) {
        authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Автор с идентификатором \"%s\" не найден.", id)));
        authorRepository.deleteById(id);
    }
}
