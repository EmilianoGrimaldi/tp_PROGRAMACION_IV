package com.trabajopractico.fundamentosdespring.config;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaDto;
import com.trabajopractico.fundamentosdespring.detallePedido.DetallePedidoCreate;
import com.trabajopractico.fundamentosdespring.models.*;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.producto.ProductoDto;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import com.trabajopractico.fundamentosdespring.repository.PedidoRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import com.trabajopractico.fundamentosdespring.service.*;
import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    public DataLoader(UsuarioService usuarioService, CategoriaService categoriaService,
                      ProductoService productoService, PedidoService pedidoService,
                      UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository,
                      ProductoRepository productoRepository, PedidoRepository pedidoRepository) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 2 Usuarios
        UsuarioDto u1Dto = usuarioService
                .save(new UsuarioCreate("Juan", "Perez", "juan@mail.com", "11223344", "pass123", Rol.USUARIO));
        Usuario u1 = usuarioRepository.findById(u1Dto.id()).orElseThrow();

        UsuarioDto u2Dto = usuarioService
                .save(new UsuarioCreate("Ana", "Gomez", "ana@mail.com", "55667788", "pass456", Rol.ADMIN));
        Usuario u2 = usuarioRepository.findById(u2Dto.id()).orElseThrow();

        // 3 Categorías
        CategoriaDto c1Dto = categoriaService.save(new CategoriaCreate("Electronica", "Dispositivos electronicos"));
        Categoria c1 = categoriaRepository.findById(c1Dto.id()).orElseThrow();

        CategoriaDto c2Dto = categoriaService.save(new CategoriaCreate("Hogar", "Cosas para el hogar"));
        Categoria c2 = categoriaRepository.findById(c2Dto.id()).orElseThrow();

        CategoriaDto c3Dto = categoriaService.save(new CategoriaCreate("Indumentaria", "Ropa y accesorios"));
        Categoria c3 = categoriaRepository.findById(c3Dto.id()).orElseThrow();

        // 10 Productos
        ProductoDto p1Dto = productoService
                .save(new ProductoCreate("Notebook", 1500.0, "Notebook potente", 10, "http://img.com/notebook.png", true, c1.getId()));
        Producto p1 = productoRepository.findById(p1Dto.id()).orElseThrow();

        ProductoDto p2Dto = productoService
                .save(new ProductoCreate("Mouse", 25.0, "Mouse optico", 50, "http://img.com/mouse.png", true, c1.getId()));
        Producto p2 = productoRepository.findById(p2Dto.id()).orElseThrow();

        ProductoDto p3Dto = productoService
                .save(new ProductoCreate("Teclado", 45.0, "Teclado mecanico", 30, "http://img.com/teclado.png", true, c1.getId()));
        Producto p3 = productoRepository.findById(p3Dto.id()).orElseThrow();

        ProductoDto p4Dto = productoService
                .save(new ProductoCreate("Silla", 150.0, "Silla de oficina", 20, "http://img.com/silla.png", true, c2.getId()));
        Producto p4 = productoRepository.findById(p4Dto.id()).orElseThrow();

        productoService.save(new ProductoCreate("Mesa", 200.0, "Mesa de comedor", 15, "http://img.com/mesa.png", true, c2.getId()));

        ProductoDto p6Dto = productoService
                .save(new ProductoCreate("Lampara", 30.0, "Lampara LED", 100, "http://img.com/lampara.png", true, c2.getId()));
        Producto p6 = productoRepository.findById(p6Dto.id()).orElseThrow();

        ProductoDto p7Dto = productoService
                .save(new ProductoCreate("Remera", 20.0, "Remera de algodon", 200, "http://img.com/remera.png", true, c3.getId()));
        Producto p7 = productoRepository.findById(p7Dto.id()).orElseThrow();

        productoService.save(new ProductoCreate("Pantalon", 40.0, "Jean azul", 150, "http://img.com/pantalon.png", true, c3.getId()));

        ProductoDto p9Dto = productoService
                .save(new ProductoCreate("Zapatillas", 80.0, "Zapatillas deportivas", 80, "http://img.com/zapatillas.png", true, c3.getId()));
        Producto p9 = productoRepository.findById(p9Dto.id()).orElseThrow();

        productoService.save(new ProductoCreate("Gorra", 15.0, "Gorra negra", 50, "http://img.com/gorra.png", true, c3.getId()));
                PedidoDto ped1Dto = pedidoService.save(
                        new PedidoEdit(
                                LocalDate.now(),
                                Estado.PENDIENTE,
                                null,
                                FormaPago.TARJETA,
                                u1.getId(),
                                List.of(
                                        new DetallePedidoCreate(p1.getId(), 1),
                                        new DetallePedidoCreate(p2.getId(), 2)
                                )
                        )
                );

                PedidoDto ped2Dto = pedidoService.save(
                        new PedidoEdit(
                                LocalDate.now(),
                                Estado.CONFIRMADO,
                                null,
                                FormaPago.TRANSFERENCIA,
                                u2.getId(),
                                List.of(
                                        new DetallePedidoCreate(p4.getId(), 1),
                                        new DetallePedidoCreate(p7.getId(), 4),
                                        new DetallePedidoCreate(p9.getId(), 2)
                                )
                        )
                );

                PedidoDto ped3Dto = pedidoService.save(
                        new PedidoEdit(
                                LocalDate.now(),
                                Estado.TERMINADO,
                                null,
                                FormaPago.EFECTIVO,
                                u1.getId(),
                                List.of(
                                        new DetallePedidoCreate(p6.getId(), 10),
                                        new DetallePedidoCreate(p3.getId(), 1)
                                )
                        )
                );

        System.out.println("========================================");
        System.out.println("Carga de datos inicial completada con exito.");
        System.out.println("  - 2 usuarios, 3 categorias, 10 productos, 3 pedidos");
        System.out.println("========================================");
    }
}
