package guru.springframework.sfgpetclinicconor.bootstrap;

import guru.springframework.sfgpetclinicconor.model.*;
import guru.springframework.sfgpetclinicconor.service.OwnerService;
import guru.springframework.sfgpetclinicconor.service.PetTypeService;
import guru.springframework.sfgpetclinicconor.service.SpecialtyService;
import guru.springframework.sfgpetclinicconor.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int petTypeCount = petTypeService.findAll().size();

        if (petTypeCount == 0) {
            loadData();
        }
    }

    private void loadData() {
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
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        Pet mikesDog = new Pet();
        mikesDog.setName("Rosco");
        mikesDog.setOwner(owner1);
        mikesDog.setBirthDate(LocalDate.now());
        mikesDog.setPetType(savedDogPetType);
        owner1.getPets().add(mikesDog);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);
        ownerService.save(owner2);

        ownerService.save(owner2);
        System.out.println("Owners now loaded...");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jesse");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("Vets now loaded...");
    }
}
