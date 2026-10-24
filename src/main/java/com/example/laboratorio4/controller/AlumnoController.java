package com.example.laboratorio4.controller;
import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {
    
    @Autowired
    private AlumnoService service;

    @GetMapping
    public List<Alumno> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtener(@PathVariable Long id) {
        Optional<Alumno> alumno = service.buscarPorId(id);
        return alumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alumno> crear(@RequestBody Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Long id, @RequestBody Alumno alumnoActualizado) {
        return service.buscarPorId(id).map(alumno -> {
            alumno.setNombres(alumnoActualizado.getNombres());
            alumno.setCarrera(alumnoActualizado.getCarrera());
            alumno.setCiclo(alumnoActualizado.getCiclo());
            alumno.setPromedio(alumnoActualizado.getPromedio());
            return ResponseEntity.ok(service.guardar(alumno));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
