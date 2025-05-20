package org.example.demojdbc.repository;

import org.example.demojdbc.model.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductoRepository {

    private JdbcTemplate plantilla;

    public ProductoRepository(JdbcTemplate plantilla) {
        this.plantilla = plantilla;
    }

    @Transactional
    public void insertar(Producto producto) {
        plantilla.update("INSERT INTO Productos (nombre, precio) VALUES (?, ?)", producto.getNombre(), producto.getPrecio());
    }

    public List<Producto> buscarTodos() {
        return plantilla.query("SELECT * FROM Productos", new ProductoMapper());
    }

    public Producto buscarUno(String nombre) {
        List<Producto> resultados = plantilla.query("SELECT * FROM Productos WHERE nombre = ?", new ProductoMapper(), nombre);
        if (resultados.isEmpty()) {
            System.out.println("No se encontró ningún resultado para: " + nombre);
            return null;
        } else {
            System.out.println("Encontrado: " + resultados.get(0));
            return resultados.get(0);
        }
    }

    @Transactional
    public void borrar(Producto producto) {
        plantilla.update("DELETE FROM Productos WHERE nombre = ?", producto.getNombre());
    }

    public void borrarTodos() {
        plantilla.update("DELETE FROM Productos");
    }
}