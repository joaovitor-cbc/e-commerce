package com.ecommerce.domain.controller;

import com.ecommerce.domain.model.ItemVenda;
import com.ecommerce.domain.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item-venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemvendaService;

    @PostMapping("/finalizar-compra")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemVenda finalizarCompra(@RequestBody ItemVenda itemVendaInput){
        return itemvendaService.savarItemVenda(itemVendaInput);
    }
}
