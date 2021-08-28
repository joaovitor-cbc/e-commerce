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
        validarVendaUsuario(venda.getUsuario().getCpf());
        return vendaRepository.save(venda);
    }

    public void validarVendaUsuario(String cpfUsuario){
        if(!usuarioService.verificarExistenciaUsuario(cpfUsuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não cadastrado");
        }
    }

    public Venda validarVenda(Long idVenda){
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "venda não cadastrada, " +
                        "id venda invalido"));
        return venda;
    }

    public void finalizarVenda(Venda venda){
        venda.setFinalizada(true);
        vendaRepository.save(venda);
    }
    public void verificarVendaFinalizada(Venda venda){
        if(venda.isFinalizada()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "venda já finalizada");
        }
    }
}
