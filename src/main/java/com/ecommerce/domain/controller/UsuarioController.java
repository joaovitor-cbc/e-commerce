package com.ecommerce.domain.controller;

import com.ecommerce.domain.model.Usuario;
import com.ecommerce.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar-usuario")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody Usuario usuarioInput){
        return usuarioService.savarUsuario(usuarioInput);
    }
}
