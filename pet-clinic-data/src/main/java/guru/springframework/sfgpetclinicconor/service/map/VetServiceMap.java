package guru.springframework.sfgpetclinicconor.service.map;

import guru.springframework.sfgpetclinicconor.model.Specialty;
import guru.springframework.sfgpetclinicconor.model.Vet;
import guru.springframework.sfgpetclinicconor.service.SpecialtyService;
import guru.springframework.sfgpetclinicconor.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet save(Vet vet) {
        Set<Specialty> vetSpecialties = vet.getSpecialties();
        if (vetSpecialties.size() > 0) {
            vetSpecialties.forEach(specialty -> {
                if (specialty.getId() == null) {
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }
}
