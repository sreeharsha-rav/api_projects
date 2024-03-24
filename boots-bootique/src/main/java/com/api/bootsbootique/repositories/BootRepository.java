package com.api.bootsbootique.repositories;

import com.api.bootsbootique.entities.Boot;
import com.api.bootsbootique.enums.BootType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BootRepository extends CrudRepository<Boot, Integer> {
    List<Boot> findBootsBySize(Float size);
    List<Boot> findBootsByMaterial(String material);
    List<Boot> findBootsByType(BootType type);
    List<Boot> findBootsMoreThanQuantity(Integer quantity);
}
