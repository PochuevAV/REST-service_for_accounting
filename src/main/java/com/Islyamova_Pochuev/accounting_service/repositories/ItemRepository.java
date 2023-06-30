package com.Islyamova_Pochuev.accounting_service.repositories;

import com.Islyamova_Pochuev.accounting_service.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
