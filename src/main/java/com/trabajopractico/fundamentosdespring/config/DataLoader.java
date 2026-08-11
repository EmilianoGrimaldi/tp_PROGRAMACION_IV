package com.trabajopractico.fundamentosdespring.config;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.models.*;
import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.service.*;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    public DataLoader(UsuarioService usuarioService, CategoriaService categoriaService, ProductoService productoService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 2 Usuarios
        Usuario u1 = usuarioService.crearUsuario(new UsuarioCreate("Juan", "Perez", "juan@mail.com", "11223344", Rol.USUARIO));
        Usuario u2 = usuarioService.crearUsuario(new UsuarioCreate("Ana", "Gomez", "ana@mail.com", "55667788", Rol.ADMIN));

        // 3 Categorias
        Categoria c1 = categoriaService.crearCategoria(new CategoriaCreate("Electronica", "Dispositivos electronicos"));
        Categoria c2 = categoriaService.crearCategoria(new CategoriaCreate("Hogar", "Cosas para el hogar"));
        Categoria c3 = categoriaService.crearCategoria(new CategoriaCreate("Indumentaria", "Ropa y accesorios"));

        // 10 Productos
        Producto p1 = productoService.crearProducto(new ProductoCreate("Notebook", 1500.0, "Notebook potente", 10, "url1", true, c1));
        Producto p2 = productoService.crearProducto(new ProductoCreate("Mouse", 25.0, "Mouse optico", 50, "url2", true, c1));
        Producto p3 = productoService.crearProducto(new ProductoCreate("Teclado", 45.0, "Teclado mecanico", 30, "url3", true, c1));
        Producto p4 = productoService.crearProducto(new ProductoCreate("Silla", 150.0, "Silla de oficina", 20, "url4", true, c2));
        Producto p5 = productoService.crearProducto(new ProductoCreate("Mesa", 200.0, "Mesa de comedor", 15, "url5", true, c2));
        Producto p6 = productoService.crearProducto(new ProductoCreate("Lampara", 30.0, "Lampara LED", 100, "url6", true, c2));
        Producto p7 = productoService.crearProducto(new ProductoCreate("Remera", 20.0, "Remera de algodon", 200, "url7", true, c3));
        Producto p8 = productoService.crearProducto(new ProductoCreate("Pantalon", 40.0, "Jean azul", 150, "url8", true, c3));
        Producto p9 = productoService.crearProducto(new ProductoCreate("Zapatillas", 80.0, "Zapatillas deportivas", 80, "url9", true, c3));
        Producto p10 = productoService.crearProducto(new ProductoCreate("Gorra", 15.0, "Gorra negra", 50, "url10", true, c3));

        // 3 Pedidos (al menos 2 detalles pedido por cada uno)
        Pedido ped1 = new Pedido();
        ped1.setUsuario(u1);
        ped1.setFecha(LocalDate.now());
        ped1.setEstado(Estado.PENDIENTE);
        ped1.setFormaPago(FormaPago.TARJETA);
        ped1.addDetallePedido(1, p1);
        ped1.addDetallePedido(2, p2);
        
        Pedido ped2 = new Pedido();
        ped2.setUsuario(u2);
        ped2.setFecha(LocalDate.now());
        ped2.setEstado(Estado.CONFIRMADO);
        ped2.setFormaPago(FormaPago.TRANSFERENCIA);
        ped2.addDetallePedido(1, p4);
        ped2.addDetallePedido(4, p7);
        ped2.addDetallePedido(2, p9);

        Pedido ped3 = new Pedido();
        ped3.setUsuario(u1);
        ped3.setFecha(LocalDate.now());
        ped3.setEstado(Estado.TERMINADO);
        ped3.setFormaPago(FormaPago.EFECTIVO);
        ped3.addDetallePedido(10, p6);
        ped3.addDetallePedido(1, p3);

        pedidoService.guardar(ped1);
        pedidoService.guardar(ped2);
        pedidoService.guardar(ped3);
        
        System.out.println("Carga de datos inicial completada con exito.");
    }
}
