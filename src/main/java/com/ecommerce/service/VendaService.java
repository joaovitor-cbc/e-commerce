package com.ecommerce.service;

import com.ecommerce.model.Venda;
import com.ecommerce.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Venda criarVenda(Venda venda){
        validarVendaUsuario(venda.getUsuario().getCpf());
        return vendaRepository.save(venda);
    }
    public void validarVendaUsuario(String cpfUsuario){
        if(!usuarioService.verificarExistenciaUsuario(cpfUsuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não cadastrado");
        }
    }
}
