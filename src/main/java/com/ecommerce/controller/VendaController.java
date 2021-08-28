package com.ecommerce.controller;

import com.ecommerce.model.Venda;
import com.ecommerce.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iniciar-compra")
public class VendaController {

    @Autowired
    private VendaService vendaService;


    @PostMapping("/registrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Venda iniciarCompra(@RequestBody Venda vendaInput){
        return vendaService.criarVenda(vendaInput);
    }

}
