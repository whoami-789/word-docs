package com.worddocs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "azolik_fiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AzolikFiz {

    @Column(name = "kodchlen", nullable = false, columnDefinition = "nchar(8)")
    private String kodchlen;

    @Column(name = "name")
    private String name;

    @Column(name = "inn", nullable = false, columnDefinition = "nchar()")
    private String inn;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telmobil", nullable = false, columnDefinition = "nchar()")
    private String telmobil;

    @Column(name = "telhome", nullable = false, columnDefinition = "nchar()")
    private String telhome;

    @Column(name = "ser_num_pasp")
    private String serNumPasp;

    @Column(name = "ser_pasp")
    private String ser_pasp;

    @Column(name = "num_pasp")
    private String num_pasp;

    @Column(name = "kem_pasp")
    private String kem_pasp;

    @Column(name = "vidan_pasp")
    private LocalDate vidanPasp;

    @Column(name = "dats_rojd")
    private LocalDate datsRojd;

    @Column(name = "fsobst")
    private Byte fsobst;

    @Column(name = "users")
    private Short users;

    @Transient
    private Byte status;


    @Column(name = "lsvznos", nullable = false, columnDefinition = "nchar()")
    private String lsvznos;

    @Column(name = "indpred")
    private Byte indpred;

    @Column(name = "kodObl", nullable = false, columnDefinition = "nchar()")
    private String kodObl;

    @Column(name = "kodRayon", nullable = false, columnDefinition = "nchar()")
    private String kodRayon;

    @Column(name = "fam")
    private String fam;

    @Column(name = "imya")
    private String imya;

    @Column(name = "otch")
    private String otch;

    @Column(name = "kodPension", nullable = false, columnDefinition = "nchar()")
    private String kodPension;

    @Column(name = "tipDok", nullable = false, columnDefinition = "nchar()")
    private String tipDok;

    @Column(name = "dats_izm")
    private LocalDate datsIzm;

    @Column(name = "paspdo")
    private LocalDate paspdo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}

