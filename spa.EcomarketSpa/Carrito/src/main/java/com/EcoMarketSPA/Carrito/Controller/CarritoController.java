package com.EcoMarketSPA.Carrito.Controller;

import java.util.List;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Service.CarritoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> listarCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarId(@PathVariable Integer idCarrito) {
        try {
            Carrito carrito = carritoService.findById(idCarrito);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Carrito> buscarPorUsuario(@PathVariable Integer usuarioId) {
        try {
            Carrito carrito = carritoService.findByUsuarioId(usuarioId);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Carrito> buscarPorEstado(@PathVariable String estado) {
        try {
            Carrito carrito = carritoService.findByEstado(estado);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Integer idCarrito, @RequestBody Carrito carrito) {
        try {
            Carrito carritoExistente = carritoService.findById(idCarrito);
            if (carritoExistente == null) {
                return ResponseEntity.notFound().build();
            }
            carrito.setId(idCarrito);
            Carrito carritoActualizado = carritoService.save(carrito);
            return ResponseEntity.ok(carritoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Integer idCarrito) {
        try {
            Carrito carrito = carritoService.findById(idCarrito);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            carritoService.delete(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    

    @PostMapping("/{carritoId}/items")
    public ResponseEntity<Carrito> agregarItem(@PathVariable Integer carritoId, @RequestBody CarritoItem item) {
        item.setCarrito(new Carrito(carritoId, null, 0, null, null)); // Asocia el carrito
        Carrito carritoActualizado = carritoService.agregarItem(item);
        return ResponseEntity.ok(carritoActualizado);
    }

    @PutMapping("/{carritoId}/items/{itemId}")
    public ResponseEntity<Carrito> actualizarItem(@PathVariable Integer carritoId, @PathVariable Integer itemId, @RequestBody CarritoItem item) {
        item.setId(itemId);
        item.setCarrito(new Carrito(carritoId, null, 0, null, null)); // Asocia el carrito
        Carrito carritoActualizado = carritoService.actualizarItem(item);
        return ResponseEntity.ok(carritoActualizado);
    }

    @DeleteMapping("/{carritoId}/items/{itemId}")
    public ResponseEntity<Carrito> eliminarItem(@PathVariable Integer carritoId, @PathVariable Integer itemId) {
        CarritoItem item = new CarritoItem();
        item.setId(itemId);
        item.setCarrito(new Carrito(carritoId, null, 0, null, null)); // Asocia el carrito
        Carrito carritoActualizado = carritoService.eliminarItem(item);
        return ResponseEntity.ok(carritoActualizado);
    }
}
