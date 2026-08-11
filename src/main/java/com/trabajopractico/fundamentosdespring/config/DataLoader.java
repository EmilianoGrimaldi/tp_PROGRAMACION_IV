package com.trabajopractico.fundamentosdespring.config;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaDto;
import com.trabajopractico.fundamentosdespring.models.*;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.producto.ProductoDto;
import com.trabajopractico.fundamentosdespring.repository.CategoriaRepository;
import com.trabajopractico.fundamentosdespring.repository.ProductoRepository;
import com.trabajopractico.fundamentosdespring.repository.UsuarioRepository;
import com.trabajopractico.fundamentosdespring.service.*;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;
    
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public DataLoader(UsuarioService usuarioService, CategoriaService categoriaService, ProductoService productoService, PedidoService pedidoService,
                      UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 2 Usuarios
        UsuarioDto u1Dto = usuarioService.save(new UsuarioCreate("Juan", "Perez", "juan@mail.com", "11223344", "3123124", Rol.USUARIO));
        Usuario u1 = usuarioRepository.findById(u1Dto.id()).orElseThrow();

        UsuarioDto u2Dto = usuarioService.save(new UsuarioCreate("Ana", "Gomez", "ana@mail.com", "55667788", "4214312", Rol.ADMIN));
        Usuario u2 = usuarioRepository.findById(u2Dto.id()).orElseThrow();

        // 3 Categorias
        CategoriaDto c1Dto = categoriaService.save(new CategoriaCreate("Electronica", "Dispositivos electronicos"));
        Categoria c1 = categoriaRepository.findById(c1Dto.id()).orElseThrow();

        CategoriaDto c2Dto = categoriaService.save(new CategoriaCreate("Hogar", "Cosas para el hogar"));
        Categoria c2 = categoriaRepository.findById(c2Dto.id()).orElseThrow();

        CategoriaDto c3Dto = categoriaService.save(new CategoriaCreate("Indumentaria", "Ropa y accesorios"));
        Categoria c3 = categoriaRepository.findById(c3Dto.id()).orElseThrow();

        // 10 Productos
        ProductoDto p1Dto = productoService.save(new ProductoCreate("Notebook", 1500.0, "Notebook potente", 10, "url1", true, c1));
        Producto p1 = productoRepository.findById(p1Dto.id()).orElseThrow();

        ProductoDto p2Dto = productoService.save(new ProductoCreate("Mouse", 25.0, "Mouse optico", 50, "url2", true, c1));
        Producto p2 = productoRepository.findById(p2Dto.id()).orElseThrow();

        ProductoDto p3Dto = productoService.save(new ProductoCreate("Teclado", 45.0, "Teclado mecanico", 30, "url3", true, c1));
        Producto p3 = productoRepository.findById(p3Dto.id()).orElseThrow();

        ProductoDto p4Dto = productoService.save(new ProductoCreate("Silla", 150.0, "Silla de oficina", 20, "url4", true, c2));
        Producto p4 = productoRepository.findById(p4Dto.id()).orElseThrow();

        ProductoDto p5Dto = productoService.save(new ProductoCreate("Mesa", 200.0, "Mesa de comedor", 15, "url5", true, c2));
        Producto p5 = productoRepository.findById(p5Dto.id()).orElseThrow();

        ProductoDto p6Dto = productoService.save(new ProductoCreate("Lampara", 30.0, "Lampara LED", 100, "url6", true, c2));
        Producto p6 = productoRepository.findById(p6Dto.id()).orElseThrow();

        ProductoDto p7Dto = productoService.save(new ProductoCreate("Remera", 20.0, "Remera de algodon", 200, "url7", true, c3));
        Producto p7 = productoRepository.findById(p7Dto.id()).orElseThrow();

        ProductoDto p8Dto = productoService.save(new ProductoCreate("Pantalon", 40.0, "Jean azul", 150, "url8", true, c3));
        Producto p8 = productoRepository.findById(p8Dto.id()).orElseThrow();

        ProductoDto p9Dto = productoService.save(new ProductoCreate("Zapatillas", 80.0, "Zapatillas deportivas", 80, "url9", true, c3));
        Producto p9 = productoRepository.findById(p9Dto.id()).orElseThrow();

        ProductoDto p10Dto = productoService.save(new ProductoCreate("Gorra", 15.0, "Gorra negra", 50, "url10", true, c3));
        Producto p10 = productoRepository.findById(p10Dto.id()).orElseThrow();

        // 3 Pedidos (al menos 2 detalles pedido por cada uno)
        Pedido ped1 = new Pedido();
        ped1.setUsuario(u1);
        ped1.setFecha(LocalDate.now());
        ped1.setEstado(Estado.PENDIENTE);
        ped1.setFormaPago(FormaPago.TARJETA);
        ped1.addDetallePedido(1, p1);
        ped1.addDetallePedido(2, p2);
        pedidoService.save(ped1);

        Pedido ped2 = new Pedido();
        ped2.setUsuario(u2);
        ped2.setFecha(LocalDate.now());
        ped2.setEstado(Estado.CONFIRMADO);
        ped2.setFormaPago(FormaPago.TRANSFERENCIA);
        ped2.addDetallePedido(1, p4);
        ped2.addDetallePedido(4, p7);
        ped2.addDetallePedido(2, p9);
        pedidoService.save(ped2);

        Pedido ped3 = new Pedido();
        ped3.setUsuario(u1);
        ped3.setFecha(LocalDate.now());
        ped3.setEstado(Estado.TERMINADO);
        ped3.setFormaPago(FormaPago.EFECTIVO);
        ped3.addDetallePedido(10, p6);
        ped3.addDetallePedido(1, p3);
        pedidoService.save(ped3);

        System.out.println("Carga de datos inicial completada con exito.");
    }
}
