package com.ecommerce.service;

import com.ecommerce.model.Venda;
import com.ecommerce.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda criarVenda(Venda venda){
        return vendaRepository.save(venda);
    }
}
