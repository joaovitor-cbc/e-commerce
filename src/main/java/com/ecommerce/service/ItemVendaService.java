package com.ecommerce.service;

import com.ecommerce.model.ItemVenda;
import com.ecommerce.model.Produto;
import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venda;
import com.ecommerce.repository.ItemVendaRepository;
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
    private ProdutoService produtoService;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private UsuarioService usuarioService;

    public ItemVenda savarItemVenda(ItemVenda itemVenda){
        Produto produto = produtoService.validarProdutoEstoque(itemVenda.getProduto().getId());
        int qtdPedido = validarrQuantidadePedido(itemVenda.getQuantidade());
        Venda venda = vendaService.validarVenda(itemVenda.getVenda().getId());
        usuarioService.validarUsuario(itemVenda.getVenda().getUsuario().getId());
        produtoService.atualizarQuantidadeProdutoEstoque(qtdPedido, produto);
        return itemVendaRepository.save(itemVenda);
    }


    private int validarrQuantidadePedido(int qtdPedido){
        if(qtdPedido <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do item invalido");
        }
        return qtdPedido;
    }
}
