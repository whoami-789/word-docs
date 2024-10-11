package com.worddocs.repository;

import com.worddocs.model.Grafik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GrafikRepository extends JpaRepository<Grafik, String> {

    List<Grafik> findAllByNumdog(String numdog);
    Optional<Grafik> findByNumdog(String numdog);
}
