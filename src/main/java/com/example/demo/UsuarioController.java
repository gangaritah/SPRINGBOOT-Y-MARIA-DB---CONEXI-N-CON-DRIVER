package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        entityManager.persist(usuario);
        return ResponseEntity.ok(usuario);
    }

    // Read (All)
    @Transactional
    @GetMapping
    public List<Usuario> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM usuarios", Usuario.class);
        return query.getResultList();
    }

    // Read (By ID)
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM usuarios WHERE id = :id", Usuario.class);
        query.setParameter("id", id);
        Usuario usuario = (Usuario) query.getSingleResult();
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    // Update
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Query query = entityManager.createNativeQuery(
            "UPDATE usuarios SET nombres = :nombres, apellidos = :apellidos, contraseña = :contraseña, email = :email, rol = :rol WHERE id = :id"
        );
        query.setParameter("nombres", usuario.getNombres());
        query.setParameter("apellidos", usuario.getApellidos());
        query.setParameter("contraseña", usuario.getContraseña());
        query.setParameter("email", usuario.getEmail());
        query.setParameter("rol", usuario.getRol());
        query.setParameter("id", id);
        int updated = query.executeUpdate();
        return updated > 0 ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    // Delete
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Query query = entityManager.createNativeQuery("DELETE FROM usuarios WHERE id = :id");
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        return deleted > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
