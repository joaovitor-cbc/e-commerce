package com.ecommerce.service;

import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario savarUsuario(Usuario usuario){
        // verificar se usuario existe
        Optional<Usuario> usuarioExiste = usuarioRepository.findBycpf(usuario.getCpf());
        if (usuarioExiste.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }
}
