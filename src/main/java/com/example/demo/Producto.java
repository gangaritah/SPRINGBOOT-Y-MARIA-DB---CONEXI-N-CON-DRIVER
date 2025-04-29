package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// Ya no se importan Getter y Setter de Lombok

@Entity
@Table(name = "Productos") // Asegúrate que este nombre coincida con tu tabla en la BD
// Ya no se usan las anotaciones @Getter y @Setter a nivel de clase
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    private String categoria;

    private String urlImagen;

    // Constructor por defecto (necesario para JPA y frameworks como Jackson)
    public Producto() {
    }

    // --- Getters ---

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    // --- Setters ---

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}

