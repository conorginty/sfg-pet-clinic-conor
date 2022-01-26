package guru.springframework.sfgpetclinicconor.bootstrap;

import guru.springframework.sfgpetclinicconor.model.Owner;
import guru.springframework.sfgpetclinicconor.model.PetType;
import guru.springframework.sfgpetclinicconor.model.Vet;
import guru.springframework.sfgpetclinicconor.service.OwnerService;
import guru.springframework.sfgpetclinicconor.service.PetTypeService;
import guru.springframework.sfgpetclinicconor.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        System.out.println("Dog's ID BEFORE saving: " + dog.getId());
        PetType savedDogPetType = petTypeService.save(dog);
//        dog = petTypeService.save(dog); // WHY NOT THIS INSTEAD???
        System.out.println("Dog's ID AFTER saving: " + dog.getId());
        System.out.println("Therefore, ID only gets generated after saving the Dog PetType Entity...");
        // We assign the saved object to a variable, because remember the Saving Process is going to establish that ID of the Animal for us
        // Now we can use that later on when we start wiring in the Objects for Owners and Pets

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        ownerService.save(owner2);
        System.out.println("Owners now loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jesse");
        vet2.setLastName("Porter");
        vetService.save(vet2);
        System.out.println("Vets now loaded...");
    }
}
