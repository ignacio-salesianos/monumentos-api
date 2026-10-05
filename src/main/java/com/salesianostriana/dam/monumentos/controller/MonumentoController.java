package com.salesianostriana.dam.monumentos.controller;

import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monument")
@RequiredArgsConstructor
public class MonumentoController {

    private final MonumentoRepository repository;

    @GetMapping
    public ResponseEntity<List<Monumento>> getAll() {
        List<Monumento> lista = repository.findAll();
        if (lista.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumento> getById(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Monumento> create(@Valid @RequestBody Monumento monumento) {
        monumento.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(monumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumento> edit(@PathVariable Long id, @Valid @RequestBody Monumento nuevo) {
        return ResponseEntity.of(repository.findById(id).map(m -> {
            m.setCodigoPais(nuevo.getCodigoPais());
            m.setNombrePais(nuevo.getNombrePais());
            m.setCiudad(nuevo.getCiudad());
            m.setLatitud(nuevo.getLatitud());
            m.setLongitud(nuevo.getLongitud());
            m.setNombre(nuevo.getNombre());
            m.setDescripcion(nuevo.getDescripcion());
            m.setUrlFoto(nuevo.getUrlFoto());
            return repository.save(m);
        }));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
