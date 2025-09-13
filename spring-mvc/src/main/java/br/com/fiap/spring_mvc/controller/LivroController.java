package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.entity.Categoria;
import br.com.fiap.spring_mvc.entity.Livro;
import br.com.fiap.spring_mvc.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    LivroService livroService;
    @GetMapping("/lista")
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.readLivros();
        model.addAttribute("listaLivros", livros);
        return "livroLista";
    }

    @GetMapping("/cadastro")
    public String cadastroLivro(Model model){
        model.addAttribute("livro", new Livro());
        return "livroCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarLivro(@Valid Livro livro, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("livro", livro);
            model.addAttribute("categoriaLista", Arrays.asList(Categoria.values()));
            return "livroCadastro";
        }
        if(livro.getId() == null){
            livroService.createLivro(livro);

        }else{
            livroService.updateLivro(livro);
        }
        livroService.createLivro(livro);
        return listarLivros(model);
    }
    @GetMapping("/cadastro/{id}")
    public String cadastroLivro(@PathVariable Long id, Model model){
        Livro livro = livroService.readLivro(id);
        if(livro == null){
            listarLivros(model);
        }
        model.addAttribute("livro", livro);
        model.addAttribute("livro", new Livro());
        return "livroCadastro";
    }

    @GetMapping("deletar/{id}")
    public String deletarLivro(@PathVariable Long id, Model model){
        livroService.deleteLivro(id);
        return listarLivros(model);
    }

}
