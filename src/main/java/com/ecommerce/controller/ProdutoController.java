package com.ecommerce.controller;

import com.ecommerce.model.Produto;
import com.ecommerce.service.ProdutoService;
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
