package com.worddocs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Saldo {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @Id
    @Column(name = "ls", nullable = false)
    private String ls;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "sums", nullable = false)
    private BigDecimal sums;

    @Column(name = "activate")
    private Byte activate;

}
