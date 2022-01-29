package guru.springframework.sfgpetclinicconor.service.map;

import guru.springframework.sfgpetclinicconor.model.Owner;
import guru.springframework.sfgpetclinicconor.model.Pet;
import guru.springframework.sfgpetclinicconor.model.PetType;
import guru.springframework.sfgpetclinicconor.service.OwnerService;
import guru.springframework.sfgpetclinicconor.service.PetService;
import guru.springframework.sfgpetclinicconor.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            persistPets(owner);
            return super.save(owner);
        }
        return null;
    }

    private void persistPets(Owner owner) {
        Collection<Pet> ownerPets = owner.getPets();
        ownerPets.forEach(this::persistPet);
    }

    private void persistPet(Pet pet) {
        if (pet.getPetType() != null) {
            createPetTypeIfNeeded(pet);
            savePetIfNeeded(pet);
        } else {
            throw new RuntimeException("Pet Type is required");
        }
    }

    private void createPetTypeIfNeeded(Pet pet) {
        PetType petsType = pet.getPetType();
        if (petsType.getId() == null) {
            pet.setPetType(petTypeService.save(petsType));
        }
    }

    private void savePetIfNeeded(Pet pet) {
        if (pet.getId() == null) {
            Pet savedPet = petService.save(pet);
            pet.setId(savedPet.getId());
        }
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
