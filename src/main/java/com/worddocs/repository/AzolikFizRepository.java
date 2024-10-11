package com.worddocs.repository;

import com.worddocs.model.AzolikFiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AzolikFizRepository extends JpaRepository<AzolikFiz, String> {

    Optional<AzolikFiz> findByKodchlen(String kodchlen);

}
