package guru.springframework.sfgpetclinicconor.service;

import guru.springframework.sfgpetclinicconor.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);
    Owner findByLastName(String lastName); // BUT SHOULDN'T THIS RETURN A SET OF OWNERS, AS THE LAST NAME COULD BE COMMON?
    Owner save(Owner owner);
    Set<Owner> findAll();
}
