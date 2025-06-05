package com.EcoMarketSPA.Usuario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcoMarketSPA.Usuario.Model.Usuario;
import com.EcoMarketSPA.Usuario.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario findById(long id) {
        return usuarioRepository.findById(id).get();
    }
    public Usuario findByRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    public Usuario findByTelefono(String telefono) {
        return usuarioRepository.findByTelefono(telefono);
    }
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario save(Usuario usuario) {
       return usuarioRepository.save(usuario);
    }
}
