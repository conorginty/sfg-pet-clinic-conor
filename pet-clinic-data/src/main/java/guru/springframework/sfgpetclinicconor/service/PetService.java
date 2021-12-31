package guru.springframework.sfgpetclinicconor.service;

import guru.springframework.sfgpetclinicconor.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet owner);
    Set<Pet> findAll();
}
