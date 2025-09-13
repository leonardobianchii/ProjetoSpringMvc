package br.com.fiap.spring_mvc.service;

import br.com.fiap.spring_mvc.entity.Livro;
import br.com.fiap.spring_mvc.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    LivroRepository livroRepository;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    //CRUD

    public Livro createLivro(Livro livro) {
        return livroRepository.save(livro);

    }
    public Livro readLivro(Livro livro){
        return livroRepository.findById(livro.getId()).orElse(null);
    }
    public List<Livro> readLivros(){
        return livroRepository.findAll();

    }

    public void deleteLivro(Livro livro){
        livroRepository.delete(livro);
    }

    public void updateLivro(@Valid Livro livro) {

    }
}
