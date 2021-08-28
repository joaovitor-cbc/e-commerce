package com.ecommerce.service;

import com.ecommerce.model.ItemVenda;
import com.ecommerce.model.Produto;
import com.ecommerce.repository.ItemVendaRepository;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemVenda savarItemVenda(ItemVenda itemVenda){
        Produto produto = validarProduto(itemVenda.getProduto().getId());
        int qtdPedido = valirQuantidadePedido(itemVenda.getQuantidade());
        atualizarQuantidadeProdutoEstoque(qtdPedido, produto);
        return itemVendaRepository.save(itemVenda);
    }

    private Produto validarProduto(Long idProduto){
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não cadastrado"));
        return produto;
    }

    private void atualizarQuantidadeProdutoEstoque(int qtdPedido, Produto produto){
        verificarQuantidadeProdutoEstoque(qtdPedido, produto);
        int qtdAtualProduto = produto.getQuantidade() - qtdPedido;
        produto.setQuantidade(qtdAtualProduto);
        produtoRepository.save(produto);

    }
    private void verificarQuantidadeProdutoEstoque(int qtdPedido, Produto produto){
        if( qtdPedido > produto.getQuantidade()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do produto do pedido maior " +
                    "do que o produto em estoque");
        }
    }

    private int valirQuantidadePedido(int qtdPedido){
        if(qtdPedido <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do item invalido");
        }
        return qtdPedido;
    }
}
