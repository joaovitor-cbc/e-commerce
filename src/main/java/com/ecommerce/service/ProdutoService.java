package com.ecommerce.service;

import com.ecommerce.model.Produto;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto savarProduto(Produto produto){
        if(verificarExistenciaProduto(produto.getNome())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto já cadastrado");
        }
        return produtoRepository.save(produto);
    }

    public Boolean verificarExistenciaProduto(String nome){
        Optional<Produto> produtoOptional = produtoRepository.findByNome(nome);
        if(produtoOptional.isPresent()){
            return true;
        }
        return false;
    }
}
