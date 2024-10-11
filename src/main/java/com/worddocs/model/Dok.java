package com.worddocs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dok")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod")
    private Long kod;

    @Column(name = "numdok", nullable = false, columnDefinition = "nchar(10)")
    private String numdok;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "tipdoc", columnDefinition = "tinyint")
    private int tipdoc;

    @Column(name = "ls", nullable = false, columnDefinition = "nchar(20)")
    private String ls;

    @Column(name = "lscor", nullable = false, columnDefinition = "nchar(20)")
    private String lscor;

    @Column(name = "nazn", nullable = false)
    private String nazn;

    @Column(name = "sost", columnDefinition = "tinyint")
    private int sost;

    @Column(name = "users", columnDefinition = "smallint")
    private int users;

    @Column(name = "dtime", nullable = false)
    private LocalDateTime dtime;

    @Column(name = "sums", nullable = false)
    private BigDecimal sums;

    @Column(name = "dat_prov")
    private Date datProv;

    @Column(name = "datsproc")
    private LocalDate datsProc;

    @Column(name = "koduch", nullable = false, columnDefinition = "nchar(8)")
    private String kodUch;

    @Column(name = "prixgroup")
    private Integer prixGroup;

    @Column(name = "groupstr", columnDefinition = "tinyint")
    private Integer groupStr;


}