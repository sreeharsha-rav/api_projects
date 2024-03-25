package com.api.bootsbootique.controller;

import com.api.bootsbootique.entities.Boot;
import com.api.bootsbootique.enums.BootType;
import com.api.bootsbootique.exceptions.QueryNotSupportedException;
import com.api.bootsbootique.repositories.BootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/boots")
public class BootController {

    @Autowired
    private BootRepository bootRepository;

    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        return bootRepository.findAll();
    }

    @GetMapping("/types")
    public List<BootType> getBootTypes() {
        return Arrays.asList(BootType.values());
    }

    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        Boot newBoot = new Boot();
        newBoot.setMaterial(boot.getMaterial());
        newBoot.setType(boot.getType());
        newBoot.setSize(boot.getSize());
        newBoot.setQuantity(boot.getQuantity());
        bootRepository.save(newBoot);
        return newBoot;

    }

    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {
        Optional<Boot> boot = bootRepository.findById(id);
        if (boot.isPresent()) {
            Boot deletedBoot = boot.get();
            bootRepository.delete(deletedBoot);
            return deletedBoot;
        } else {
            return null;
        }
    }

    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> boot = bootRepository.findById(id);
        if (boot.isPresent()) {
            Boot updatedBoot = boot.get();
            updatedBoot.setQuantity(updatedBoot.getQuantity() + 1);
            bootRepository.save(updatedBoot);
            return updatedBoot;
        } else {
            return null;
        }
    }

    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        Optional<Boot> boot = bootRepository.findById(id);
        if (boot.isPresent()) {
            Boot updatedBoot = boot.get();
            updatedBoot.setQuantity(updatedBoot.getQuantity() - 1);
            bootRepository.save(updatedBoot);
            return updatedBoot;
        } else {
            return null;
        }
    }

    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) throws QueryNotSupportedException {
        if (Objects.nonNull(material) && Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
            List<Boot> boots = bootRepository.findBootsByMaterial(material);
        } else if (Objects.nonNull(type) && Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
            List<Boot> boots = bootRepository.findBootsByType(type);
        } else if (Objects.nonNull(size) && Objects.nonNull(minQuantity)) {
            List<Boot> boots = bootRepository.findBootsBySize(size);
        } else if (Objects.nonNull(minQuantity) && Objects.isNull(material) && Objects.isNull(type) && Objects.isNull(size)){
            List<Boot> boots = bootRepository.findBootsMoreThanQuantity(minQuantity);
        } else {
            throw new QueryNotSupportedException("This query is not supported! Try a different combination of search parameters.");
        }
        return null;
    }

}
