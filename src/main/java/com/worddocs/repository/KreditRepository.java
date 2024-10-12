package com.worddocs.repository;


import com.worddocs.model.Kredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface KreditRepository extends JpaRepository<Kredit, String> {
    Optional<Kredit> findByNumdog(String kod);
}
