package com.EcoMarketSPA.Carrito.Service;

import java.util.List;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Repository.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Carrito findById(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito findByUsuarioId(int usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public Carrito findByEstado(String estado) {
        return carritoRepository.findByEstado(estado);
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void delete(Integer idCarrito) {
        carritoRepository.deleteById(idCarrito);
    }

    @Transactional
    public Carrito agregarItem(CarritoItem item) {
        Carrito carrito = item.getCarrito(); // Obtiene el carrito asociado al ítem.
        carrito.getItems().add(item); // Agrega el ítem a la lista de ítems del carrito.
        actualizarTotal(carrito); // Recalcula el total del carrito.
        return carritoRepository.save(carrito); // Guarda el carrito (y sus ítems) en la base de datos.
    }

    @Transactional
    public Carrito eliminarItem(CarritoItem item) {
        Carrito carrito = item.getCarrito(); // Obtiene el carrito asociado al ítem.
        carrito.getItems().remove(item); // Elimina el ítem de la lista de ítems del carrito.
        actualizarTotal(carrito); // Recalcula el total del carrito.
        return carritoRepository.save(carrito); // Guarda el carrito actualizado en la base de datos.
    }

    @Transactional
    public Carrito actualizarItem(CarritoItem item) {
        Carrito carrito = item.getCarrito(); // Obtiene el carrito asociado al ítem.
        actualizarTotal(carrito); // Recalcula el total del carrito.
        return carritoRepository.save(carrito); // Guarda el carrito actualizado en la base de datos.
    }


    //este metodo se encarga de actualizar el total del carrito después de agregar, eliminar o actualizar un ítem.
    //no es utilizado en controller ni llamado directamente fuera de service
    private void actualizarTotal(Carrito carrito) {
        double total = carrito.getItems().stream() // Obtiene la lista de ítems del carrito.
                .mapToDouble(CarritoItem::getSubtotal) // Calcula el subtotal de cada ítem.
                .sum(); // Suma todos los subtotales.
        carrito.setTotal(total); // Actualiza el total del carrito.
    }
}
