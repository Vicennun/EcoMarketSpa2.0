package com.EcoMarketSPA.Usuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EcoMarketSPA.Usuario.Model.Usuario;
import com.EcoMarketSPA.Usuario.Service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
    List<Usuario> usuarios = usuarioService.findAll();
    if (usuarios.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarId(@PathVariable Integer id) {
        try { 
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Usuario> buscarRut(@PathVariable String rut) {
        try { 
            Usuario usuario = usuarioService.findByRut(rut);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<Usuario> buscarTelefono(@PathVariable String telefono) {
        try { 
            Usuario usuario = usuarioService.findByTelefono(telefono);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarEmail(@PathVariable String email) {
        try { 
            Usuario usuario = usuarioService.findByEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario usu = usuarioService.findById(id);
            usu.setId(id);
            usu.setNombre(usuario.getNombre());
            usu.setApellidos(usuario.getApellidos());
            usu.setRut(usuario.getRut());
            usu.setEmail(usuario.getEmail());
            usu.setTelefono(usuario.getTelefono());
            usu.setDireccion(usuario.getDireccion());
            usu.setRegion(usuario.getRegion());
            usu.setCiudad(usuario.getCiudad());
            usu.setCodigoPostal(usuario.getCodigoPostal());
            usuarioService.save(usu);
            return ResponseEntity.ok(usu);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
