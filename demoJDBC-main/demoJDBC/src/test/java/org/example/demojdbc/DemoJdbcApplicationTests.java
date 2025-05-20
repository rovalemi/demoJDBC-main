package org.example.demojdbc;

import org.example.demojdbc.model.Persona;
import org.example.demojdbc.model.Producto;
import org.example.demojdbc.repository.PersonaRepository;
import org.example.demojdbc.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/schema.sql"})
class DemoJdbcApplicationTests {

    @Autowired
    PersonaRepository repositorio;

    @Autowired
    ProductoRepository productoRepository;

    // Personas

    @Test
    void borrarTodosPersona() {
        repositorio.borrarTodos();
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    void insertarPersona() {
        Persona persona = new Persona("pedro", "perez", 18);
        repositorio.insertar(persona);
        persona = new Persona("javier", "sanchez", 20);
        repositorio.insertar(persona);
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void buscarTodosPersona() {
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void borrarPersona() {
        Persona persona = new Persona("pedro");
        repositorio.borrar(persona);
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(1, lista.size());
    }

    @Test
    void buscarUnoPersona() {
        Persona persona = repositorio.buscarUno("javier");
        assertEquals("javier", persona.getNombre());
    }

    // Productos

    @Test
    void borrarTodosProducto() {
        productoRepository.borrarTodos();
        List<Producto> lista =  productoRepository.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    void insertarProducto() {
        Producto producto = new Producto("pera", 5.5);
        productoRepository.insertar(producto);
        producto = new Producto("manzana", 4.3);
        productoRepository.insertar(producto);
        List<Producto> lista = productoRepository.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void buscarTodosProducto() {
        List<Producto> lista = productoRepository.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void borrarProducto() {
        Producto producto = new Producto("manzana");
        productoRepository.borrar(producto);
        List<Producto> lista = productoRepository.buscarTodos();
        assertEquals(1, lista.size());
    }

    @Test
    void buscarUnoProducto() {
        Producto producto = productoRepository.buscarUno("pera");
        assertEquals("pera", producto.getNombre());
    }
}
