package com.EcoMarketSPA.Carrito;

import com.EcoMarketSPA.Carrito.Model.Carrito;
import com.EcoMarketSPA.Carrito.Model.CarritoItem;
import com.EcoMarketSPA.Carrito.Repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar la tabla carrito antes de insertar nuevos datos (opcional)
        carritoRepository.deleteAll();

        Faker faker = new Faker();
        Random random = new Random();
        Set<Integer> usuariosGenerados = new HashSet<>(); // Para evitar duplicados de usuario_id

        // Generar carritos
        for (int i = 0; i < 10; i++) {
            int usuarioId;
            do {
                usuarioId = faker.number().numberBetween(1, 100); // Generar usuario ficticio
            } while (usuariosGenerados.contains(usuarioId)); // Evitar duplicados
            usuariosGenerados.add(usuarioId);

            Carrito carrito = new Carrito();
            carrito.setUsuarioId(usuarioId); // Usuario único
            carrito.setEstado(faker.options().option("activo", "finalizado", "cancelado")); // Estado correcto
            carrito.setTotal(0.0); // Inicialmente el total es 0

            // Generar ítems para el carrito
            List<CarritoItem> items = new ArrayList<>();
            int numeroDeItems = random.nextInt(5) + 1; // Entre 1 y 5 ítems por carrito
            for (int j = 0; j < numeroDeItems; j++) {
                CarritoItem item = new CarritoItem();
                item.setProductoId(faker.number().numberBetween(1, 100)); // Producto ficticio
                item.setCantidad(faker.number().numberBetween(1, 10)); // Cantidad entre 1 y 10
                item.setPrecioUnitario(
                        BigDecimal.valueOf(faker.number().randomDouble(2, 5, 100)) // Precio entre 5 y 100
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ); // Redondear a 2 decimales
                item.setCarrito(carrito); // Asociar el ítem al carrito
                items.add(item);
            }

            // Asociar los ítems al carrito
            carrito.setItems(items);

            // Calcular el total del carrito
            double total = items.stream()
                    .mapToDouble(item -> item.getCantidad() * item.getPrecioUnitario())
                    .sum();
            carrito.setTotal(BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue()); // Total correcto

            // Guardar el carrito (y sus ítems gracias a CascadeType.ALL)
            carritoRepository.save(carrito);
        }
    }
}