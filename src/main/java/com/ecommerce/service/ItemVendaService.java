package com.ecommerce.service;

import com.ecommerce.model.ItemVenda;
import com.ecommerce.model.Produto;
import com.ecommerce.model.Venda;
import com.ecommerce.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        //verificar existencia de produto
        Produto produto = produtoService.validarProdutoEstoque(itemVenda.getProduto().getId());
        // verificar qtd do pedido do item
        int qtdPedido = validarrQuantidadePedido(itemVenda.getQuantidade());
        // validar id de venda
        Venda venda = vendaService.validarVenda(itemVenda.getVenda().getId());
        //validar id usuario
        usuarioService.validarUsuario(itemVenda.getVenda().getUsuario().getId());
        //verificar se id da venda já foi finalizada
        vendaService.verificarVendaFinalizada(venda);
        //atualizar quantidade de produto em estoque
        produtoService.atualizarQuantidadeProdutoEstoque(qtdPedido, produto);
        //finalizar id da venda
        vendaService.finalizarVenda(venda);
        //salva comprar com todos os dados necessario
        return itemVendaRepository.save(itemVenda);
    }


    private int validarrQuantidadePedido(int qtdPedido){
        if(qtdPedido <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantidade do item invalido");
        }
        return qtdPedido;
    }
}
