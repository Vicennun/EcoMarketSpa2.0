package com.ECOMARKET.DetallePedido.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ECOMARKET.DetallePedido.Model.DetallePedido;
import com.ECOMARKET.DetallePedido.Service.DetallePedidoService;

@RestController
@RequestMapping("/api/v1/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> listarDetalles() {
        List<DetallePedido> detalles = detallePedidoService.findAll();
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }

    @PostMapping
    public ResponseEntity<DetallePedido> agregarDetalle(@RequestBody DetallePedido detallePedido) {
        DetallePedido nuevoDetalle = detallePedidoService.save(detallePedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDetalle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscarDetalle(@PathVariable Integer id) {
        DetallePedido detalle = detallePedidoService.findById(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedido>> buscarDetallesPorPedido(@PathVariable Integer pedidoId) {
        List<DetallePedido> detalles = detallePedidoService.findByPedidoId(pedidoId);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(@PathVariable Integer id, @RequestBody DetallePedido detallePedido) {
        DetallePedido detped = detallePedidoService.findById(id);
        if (detped == null) {
            return ResponseEntity.notFound().build();
        }
        // Actualiza los campos necesarios
        detped.setPedidoId(detallePedido.getPedidoId());
        detped.setProductoId(detallePedido.getProductoId());
        detped.setCantidad(detallePedido.getCantidad());
        detped.setPrecioUnitario(detallePedido.getPrecioUnitario());

        DetallePedido actualizado = detallePedidoService.save(detped);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Integer id) {
        DetallePedido detped = detallePedidoService.findById(id);
        if (detped == null) {
            return ResponseEntity.notFound().build();
        }
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}