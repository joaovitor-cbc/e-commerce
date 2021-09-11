package com.ecommerce.domain.service;

import com.ecommerce.DTO.UsuarioInput;
import com.ecommerce.DTO.UsuarioModel;
import com.ecommerce.domain.model.Usuario;
import com.ecommerce.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Usuario savarUsuario(Usuario usuario){
        if (verificarExistenciaUsuario(usuario.getCpf())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }
    public Boolean verificarExistenciaUsuario(String cpf){
        // verificar se usuario existe
        Optional<Usuario> usuarioExiste = usuarioRepository.findBycpf(cpf);
        if (usuarioExiste.isPresent()){
            return true;
        }
        return false;
    }
    public void validarUsuario(Long idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não cadastrado"));
    }

    private UsuarioModel toUsuarioModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    private Usuario toUsuarioEntity(UsuarioInput usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }

}
