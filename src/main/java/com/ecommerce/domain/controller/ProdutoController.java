package com.ecommerce.domain.controller;

import com.ecommerce.domain.model.Produto;
import com.ecommerce.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/registrar-produto")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produto registarProduto(@RequestBody Produto produtoInput){
        return produtoService.savarProduto(produtoInput);
    }
}
