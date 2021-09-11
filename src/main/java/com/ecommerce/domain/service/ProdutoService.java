package com.ecommerce.domain.service;

import com.ecommerce.DTO.ProdutoInput;
import com.ecommerce.DTO.ProdutoModel;
import com.ecommerce.domain.model.Produto;
import com.ecommerce.domain.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel savarProduto(ProdutoInput produtoInput){
        Produto produtoEntity = toProdutoEntity(produtoInput);
        verificarExistenciaProduto(produtoEntity.getNome());
        verificarQuantidadeProduto(produtoEntity.getQuantidade());
        return toProdutoModel(produtoRepository.save(produtoEntity));
    }

    public void verificarExistenciaProduto(String nome){
        Optional<Produto> produtoOptional = produtoRepository.findByNome(nome);
        if(produtoOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto já cadastrado");
        }
    }

    public void verificarQuantidadeProduto(int qtdProduto){
        if(qtdProduto <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do produto invalido");
        }
    }

    public Produto validarProdutoEstoque(Long idProduto){
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não cadastrado"));
        return produto;
    }

    public void atualizarQuantidadeProdutoEstoque(int qtdPedido, Produto produto){
        verificarQuantidadeProdutoEstoque(qtdPedido, produto);
        int qtdAtualProduto = produto.getQuantidade() - qtdPedido;
        produto.setQuantidade(qtdAtualProduto);
        produtoRepository.save(produto);

    }

    private void verificarQuantidadeProdutoEstoque(int qtdPedido, Produto produto){
        if( qtdPedido > produto.getQuantidade()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do item do pedido maior " +
                    "do que o produto em estoque");
        }
    }

    private ProdutoModel toProdutoModel(Produto produto){
        return modelMapper.map(produto, ProdutoModel.class);
    }

    private Produto toProdutoEntity(ProdutoInput produtoInput){
        return modelMapper.map(produtoInput, Produto.class);
    }
}
