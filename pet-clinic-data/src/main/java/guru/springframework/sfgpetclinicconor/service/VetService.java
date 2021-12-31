package guru.springframework.sfgpetclinicconor.service;

import guru.springframework.sfgpetclinicconor.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet owner);
    Set<Vet> findAll();
}
