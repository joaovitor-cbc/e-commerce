package com.ecommerce.service;

import com.ecommerce.model.Venda;
import com.ecommerce.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Venda criarVenda(Venda venda){
        if(!usuarioService.verificarExistenciaUsuario(venda.getUsuario().getCpf())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não cadastrado");
        }
        return vendaRepository.save(venda);
    }
}
