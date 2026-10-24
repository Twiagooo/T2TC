package com.example.laboratorio4.service;
import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository repository;

    public List<Alumno> listarTodos() { return repository.findAll(); }
    public Optional<Alumno> buscarPorId(Long id) { return repository.findById(id); }
    public Alumno guardar(Alumno alumno) { return repository.save(alumno); }
    public void eliminar(Long id) { repository.deleteById(id); }
}
