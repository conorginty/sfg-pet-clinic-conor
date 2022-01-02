package guru.springframework.sfgpetclinicconor.service;

import guru.springframework.sfgpetclinicconor.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName); // BUT SHOULDN'T THIS RETURN A SET OF OWNERS, AS THE LAST NAME COULD BE COMMON?
}
