package com.worddocs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kredit")
@DynamicUpdate
public class Kredit {

    @Id
    @Column(name = "kod", nullable = false, columnDefinition = "nchar(8)")
    private String kod;

    @Column(name = "numdog", nullable = false, columnDefinition = "nchar(12)")
    private String numdog;

    @Column(name = "datadog", nullable = false)
    private LocalDate datadog;

    @Column(name = "dats")
    private LocalDate dats;

    @Column(name = "summa")
    private BigDecimal summa;

    @Column(name = "vidvalut", nullable = false, columnDefinition = "nchar(3)")
    private String vidvalut;

    @Column(name = "vidzalog", nullable = false)
    private Byte vidzalog;

    @Column(name = "vidsrok")
    private Byte vidsrok;

    @Column(name = "prosent", nullable = false)
    private BigDecimal prosent;

    @Column(name = "maqsad")
    private Byte maqsad;

    @Column(name = "sost", nullable = false)
    private Byte sost;

    @Column(name = "status")
    private Byte status;

    @Column(name = "prim")
    private String prim;

    @Transient
    private String yurfiz;

    @Column(name = "tipkred")
    private Short tipkred;

    @Column(name = "srokkred")
    private Byte srokkred;

    @Column(name = "users")
    private Short users;

    @Column(name = "lskred", nullable = false, columnDefinition = "nchar(20)")
    private String lskred;

    @Column(name = "lsproc", nullable = false, columnDefinition = "nchar(20)")
    private String lsproc;

    @Column(name = "lsprosr_kred")
    private String lsprosrKred;

    @Column(name = "dtime")
    private Date dtime;

    @Column(name = "sms")
    private Byte sms;

    @Column(name = "tel")
    private Byte tel;

    @Column(name = "kod_dog", updatable = false, nullable = false)
    private Integer kodDog;  // Уберите @GeneratedValue отсюда


    @Transient
    private Boolean indpred;

    @Column(name = "sost_ls")
    private Byte sostLs;

    @Column(name = "dopsogl")
    private String dopsogl;

    @Column(name = "lssud_kred")
    private String lssudKred;

    @Column(name = "nalbeznal")
    private Byte nalbeznal;

    @Column(name = "vazvnalbeznal")
    private Byte vazvnalbeznal;

    @Column(name = "sost_proc")
    private Byte sostProc;

    @Column(name = "rejnach")
    private Byte rejnach;

    @Column(name = "autoe")
    private Byte autoe;

    @Column(name = "autos")
    private Byte autos;

    @Column(name = "autokred")
    private Byte autokred;

    @Column(name = "autoproc")
    private Byte autoproc;

    @Column(name = "dney")
    private Byte dney;

    @Column(name = "chas")
    private Byte chas;

    @Transient
    private String lsDox;

    @Column(name = "lsprocvne")
    private String lsprocvne;

    @Column(name = "vidkred")
    private Integer vidkred;

    @Column(name = "lsrezerv")
    private String lsrezerv;

    @Transient
    private Integer tip;

    @Column(name = "minvznos")
    private BigDecimal minvznos;

    @Column(name = "komissy")
    private BigDecimal komissy;

    @Column(name = "lgot")
    private Byte lgot;

    @Column(name = "progress")
    private Byte progress;

    @Column(name = "lizpredmet", columnDefinition = "TEXT")
    private String lizpredmet;

    @Column(name = "lizprodovec", columnDefinition = "TEXT")
    private String lizprodovec;

    @Column(name = "dats_zakr")
    private LocalDate datsZakr;

    @Column(name = "dats_prosr")
    private Date datsProsr;

    @Column(name = "dopsogl_dats")
    private Date dopsoglDats;

    @Column(name = "spec")
    private Byte spec;

    @Column(name = "tipliz")
    private Byte tipliz;

    @Column(name = "ls_peres")
    private String lsPeres;

    @Column(name = "graf")
    private Byte graf;

    @Column(name = "autop")
    private Byte autop;

    @Transient
    private String lsKontrvne;

    @Transient
    private String lsSpiskred;

    @Column(name = "dats_izm")
    private LocalDate datsIzm;

    @Column(name = "dats_izm_grafik")
    private Date datsIzmGrafik;

    @Column(name = "dats_izm_zalog")
    private Date datsIzmZalog;

    @Column(name = "objekt")
    private String objekt;

    @Column(name = "valut")
    private Byte valut;

    @Column(name = "klass")
    private Byte klass;

    @Column(name = "lsprosr_proc")
    private String lsprosrProc;

    @Column(name = "ls22812")
    private String ls22812;

    @Column(name = "dats_izm_asoki")
    private Date datsIzmAsoki;

    @Column(name = "xatar")
    private Byte xatar;

    @OneToMany(mappedBy = "kredit")
    private List<Grafik> grafiks;

    @OneToMany(mappedBy = "kredit")
    private List<Zalog> zalogs;

    @OneToMany(mappedBy = "kredit")
    private List<ZalogXranenie> zalogXranenieList;

    @ManyToOne
    @JoinColumn(name = "kod", referencedColumnName = "kodchlen", insertable = false, updatable = false)
    private AzolikFiz azolikFiz;

    public Kredit(String kod, String numdog, LocalDate datadog, LocalDate dats, BigDecimal summa, String vidvalut, Byte vidzalog, Byte vidsrok, BigDecimal prosent, Byte maqsad, Byte sost, Byte status, String prim, String yurfiz, Short tipkred, Byte srokkred, Short users, String lskred, String lsproc, String lsprosrKred, Date dtime, Byte sms, Byte tel, Integer kodDog, Boolean indpred, Byte sostLs, String dopsogl, String lssudKred, Byte nalbeznal, Byte vazvnalbeznal, Byte sostProc, Byte rejnach, Byte autoe, Byte autos, Byte autokred, Byte autoproc, Byte dney, Byte chas, String lsDox, String lsprocvne, Integer vidkred, String lsrezerv, Integer tip, BigDecimal minvznos, BigDecimal komissy, Byte lgot, Byte progress, String lizpredmet, String lizprodovec, LocalDate datsZakr, Date datsProsr, Date dopsoglDats, Byte spec, Byte tipliz, String lsPeres, Byte graf, Byte autop, String lsKontrvne, String lsSpiskred, LocalDate datsIzm, Date datsIzmGrafik, Date datsIzmZalog, String objekt, Byte valut, Byte klass, String lsprosrProc, String ls22812, Date datsIzmAsoki, Byte xatar, List<Grafik> grafiks, List<Zalog> zalogs, List<ZalogXranenie> zalogXranenieList, AzolikFiz azolikFiz) {
        this.kod = kod;
        this.numdog = numdog;
        this.datadog = datadog;
        this.dats = dats;
        this.summa = summa;
        this.vidvalut = vidvalut;
        this.vidzalog = vidzalog;
        this.vidsrok = vidsrok;
        this.prosent = prosent;
        this.maqsad = maqsad;
        this.sost = sost;
        this.status = status;
        this.prim = prim;
        this.yurfiz = yurfiz;
        this.tipkred = tipkred;
        this.srokkred = srokkred;
        this.users = users;
        this.lskred = lskred;
        this.lsproc = lsproc;
        this.lsprosrKred = lsprosrKred;
        this.dtime = dtime;
        this.sms = sms;
        this.tel = tel;
        this.kodDog = kodDog;
        this.indpred = indpred;
        this.sostLs = sostLs;
        this.dopsogl = dopsogl;
        this.lssudKred = lssudKred;
        this.nalbeznal = nalbeznal;
        this.vazvnalbeznal = vazvnalbeznal;
        this.sostProc = sostProc;
        this.rejnach = rejnach;
        this.autoe = autoe;
        this.autos = autos;
        this.autokred = autokred;
        this.autoproc = autoproc;
        this.dney = dney;
        this.chas = chas;
        this.lsDox = lsDox;
        this.lsprocvne = lsprocvne;
        this.vidkred = vidkred;
        this.lsrezerv = lsrezerv;
        this.tip = tip;
        this.minvznos = minvznos;
        this.komissy = komissy;
        this.lgot = lgot;
        this.progress = progress;
        this.lizpredmet = lizpredmet;
        this.lizprodovec = lizprodovec;
        this.datsZakr = datsZakr;
        this.datsProsr = datsProsr;
        this.dopsoglDats = dopsoglDats;
        this.spec = spec;
        this.tipliz = tipliz;
        this.lsPeres = lsPeres;
        this.graf = graf;
        this.autop = autop;
        this.lsKontrvne = lsKontrvne;
        this.lsSpiskred = lsSpiskred;
        this.datsIzm = datsIzm;
        this.datsIzmGrafik = datsIzmGrafik;
        this.datsIzmZalog = datsIzmZalog;
        this.objekt = objekt;
        this.valut = valut;
        this.klass = klass;
        this.lsprosrProc = lsprosrProc;
        this.ls22812 = ls22812;
        this.datsIzmAsoki = datsIzmAsoki;
        this.xatar = xatar;
        this.grafiks = grafiks;
        this.zalogs = zalogs;
        this.zalogXranenieList = zalogXranenieList;
        this.azolikFiz = azolikFiz;
    }

    public Kredit() {
    }

    public String getKod() {
        return this.kod;
    }

    public String getNumdog() {
        return this.numdog;
    }

    public LocalDate getDatadog() {
        return this.datadog;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public BigDecimal getSumma() {
        return this.summa;
    }

    public String getVidvalut() {
        return this.vidvalut;
    }

    public Byte getVidzalog() {
        return this.vidzalog;
    }

    public Byte getVidsrok() {
        return this.vidsrok;
    }

    public BigDecimal getProsent() {
        return this.prosent;
    }

    public Byte getMaqsad() {
        return this.maqsad;
    }

    public Byte getSost() {
        return this.sost;
    }

    public Byte getStatus() {
        return this.status;
    }

    public String getPrim() {
        return this.prim;
    }

    public String getYurfiz() {
        return this.yurfiz;
    }

    public Short getTipkred() {
        return this.tipkred;
    }

    public Byte getSrokkred() {
        return this.srokkred;
    }

    public Short getUsers() {
        return this.users;
    }

    public String getLskred() {
        return this.lskred;
    }

    public String getLsproc() {
        return this.lsproc;
    }

    public String getLsprosrKred() {
        return this.lsprosrKred;
    }

    public Date getDtime() {
        return this.dtime;
    }

    public Byte getSms() {
        return this.sms;
    }

    public Byte getTel() {
        return this.tel;
    }

    public Integer getKodDog() {
        return this.kodDog;
    }

    public Boolean getIndpred() {
        return this.indpred;
    }

    public Byte getSostLs() {
        return this.sostLs;
    }

    public String getDopsogl() {
        return this.dopsogl;
    }

    public String getLssudKred() {
        return this.lssudKred;
    }

    public Byte getNalbeznal() {
        return this.nalbeznal;
    }

    public Byte getVazvnalbeznal() {
        return this.vazvnalbeznal;
    }

    public Byte getSostProc() {
        return this.sostProc;
    }

    public Byte getRejnach() {
        return this.rejnach;
    }

    public Byte getAutoe() {
        return this.autoe;
    }

    public Byte getAutos() {
        return this.autos;
    }

    public Byte getAutokred() {
        return this.autokred;
    }

    public Byte getAutoproc() {
        return this.autoproc;
    }

    public Byte getDney() {
        return this.dney;
    }

    public Byte getChas() {
        return this.chas;
    }

    public String getLsDox() {
        return this.lsDox;
    }

    public String getLsprocvne() {
        return this.lsprocvne;
    }

    public Integer getVidkred() {
        return this.vidkred;
    }

    public String getLsrezerv() {
        return this.lsrezerv;
    }

    public Integer getTip() {
        return this.tip;
    }

    public BigDecimal getMinvznos() {
        return this.minvznos;
    }

    public BigDecimal getKomissy() {
        return this.komissy;
    }

    public Byte getLgot() {
        return this.lgot;
    }

    public Byte getProgress() {
        return this.progress;
    }

    public String getLizpredmet() {
        return this.lizpredmet;
    }

    public String getLizprodovec() {
        return this.lizprodovec;
    }

    public LocalDate getDatsZakr() {
        return this.datsZakr;
    }

    public Date getDatsProsr() {
        return this.datsProsr;
    }

    public Date getDopsoglDats() {
        return this.dopsoglDats;
    }

    public Byte getSpec() {
        return this.spec;
    }

    public Byte getTipliz() {
        return this.tipliz;
    }

    public String getLsPeres() {
        return this.lsPeres;
    }

    public Byte getGraf() {
        return this.graf;
    }

    public Byte getAutop() {
        return this.autop;
    }

    public String getLsKontrvne() {
        return this.lsKontrvne;
    }

    public String getLsSpiskred() {
        return this.lsSpiskred;
    }

    public LocalDate getDatsIzm() {
        return this.datsIzm;
    }

    public Date getDatsIzmGrafik() {
        return this.datsIzmGrafik;
    }

    public Date getDatsIzmZalog() {
        return this.datsIzmZalog;
    }

    public String getObjekt() {
        return this.objekt;
    }

    public Byte getValut() {
        return this.valut;
    }

    public Byte getKlass() {
        return this.klass;
    }

    public String getLsprosrProc() {
        return this.lsprosrProc;
    }

    public String getLs22812() {
        return this.ls22812;
    }

    public Date getDatsIzmAsoki() {
        return this.datsIzmAsoki;
    }

    public Byte getXatar() {
        return this.xatar;
    }

    public List<Grafik> getGrafiks() {
        return this.grafiks;
    }

    public List<Zalog> getZalogs() {
        return this.zalogs;
    }

    public List<ZalogXranenie> getZalogXranenieList() {
        return this.zalogXranenieList;
    }

    public AzolikFiz getAzolikFiz() {
        return this.azolikFiz;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setNumdog(String numdog) {
        this.numdog = numdog;
    }

    public void setDatadog(LocalDate datadog) {
        this.datadog = datadog;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public void setVidvalut(String vidvalut) {
        this.vidvalut = vidvalut;
    }

    public void setVidzalog(Byte vidzalog) {
        this.vidzalog = vidzalog;
    }

    public void setVidsrok(Byte vidsrok) {
        this.vidsrok = vidsrok;
    }

    public void setProsent(BigDecimal prosent) {
        this.prosent = prosent;
    }

    public void setMaqsad(Byte maqsad) {
        this.maqsad = maqsad;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public void setYurfiz(String yurfiz) {
        this.yurfiz = yurfiz;
    }

    public void setTipkred(Short tipkred) {
        this.tipkred = tipkred;
    }

    public void setSrokkred(Byte srokkred) {
        this.srokkred = srokkred;
    }

    public void setUsers(Short users) {
        this.users = users;
    }

    public void setLskred(String lskred) {
        this.lskred = lskred;
    }

    public void setLsproc(String lsproc) {
        this.lsproc = lsproc;
    }

    public void setLsprosrKred(String lsprosrKred) {
        this.lsprosrKred = lsprosrKred;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public void setSms(Byte sms) {
        this.sms = sms;
    }

    public void setTel(Byte tel) {
        this.tel = tel;
    }

    public void setKodDog(Integer kodDog) {
        this.kodDog = kodDog;
    }

    public void setIndpred(Boolean indpred) {
        this.indpred = indpred;
    }

    public void setSostLs(Byte sostLs) {
        this.sostLs = sostLs;
    }

    public void setDopsogl(String dopsogl) {
        this.dopsogl = dopsogl;
    }

    public void setLssudKred(String lssudKred) {
        this.lssudKred = lssudKred;
    }

    public void setNalbeznal(Byte nalbeznal) {
        this.nalbeznal = nalbeznal;
    }

    public void setVazvnalbeznal(Byte vazvnalbeznal) {
        this.vazvnalbeznal = vazvnalbeznal;
    }

    public void setSostProc(Byte sostProc) {
        this.sostProc = sostProc;
    }

    public void setRejnach(Byte rejnach) {
        this.rejnach = rejnach;
    }

    public void setAutoe(Byte autoe) {
        this.autoe = autoe;
    }

    public void setAutos(Byte autos) {
        this.autos = autos;
    }

    public void setAutokred(Byte autokred) {
        this.autokred = autokred;
    }

    public void setAutoproc(Byte autoproc) {
        this.autoproc = autoproc;
    }

    public void setDney(Byte dney) {
        this.dney = dney;
    }

    public void setChas(Byte chas) {
        this.chas = chas;
    }

    public void setLsDox(String lsDox) {
        this.lsDox = lsDox;
    }

    public void setLsprocvne(String lsprocvne) {
        this.lsprocvne = lsprocvne;
    }

    public void setVidkred(Integer vidkred) {
        this.vidkred = vidkred;
    }

    public void setLsrezerv(String lsrezerv) {
        this.lsrezerv = lsrezerv;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public void setMinvznos(BigDecimal minvznos) {
        this.minvznos = minvznos;
    }

    public void setKomissy(BigDecimal komissy) {
        this.komissy = komissy;
    }

    public void setLgot(Byte lgot) {
        this.lgot = lgot;
    }

    public void setProgress(Byte progress) {
        this.progress = progress;
    }

    public void setLizpredmet(String lizpredmet) {
        this.lizpredmet = lizpredmet;
    }

    public void setLizprodovec(String lizprodovec) {
        this.lizprodovec = lizprodovec;
    }

    public void setDatsZakr(LocalDate datsZakr) {
        this.datsZakr = datsZakr;
    }

    public void setDatsProsr(Date datsProsr) {
        this.datsProsr = datsProsr;
    }

    public void setDopsoglDats(Date dopsoglDats) {
        this.dopsoglDats = dopsoglDats;
    }

    public void setSpec(Byte spec) {
        this.spec = spec;
    }

    public void setTipliz(Byte tipliz) {
        this.tipliz = tipliz;
    }

    public void setLsPeres(String lsPeres) {
        this.lsPeres = lsPeres;
    }

    public void setGraf(Byte graf) {
        this.graf = graf;
    }

    public void setAutop(Byte autop) {
        this.autop = autop;
    }

    public void setLsKontrvne(String lsKontrvne) {
        this.lsKontrvne = lsKontrvne;
    }

    public void setLsSpiskred(String lsSpiskred) {
        this.lsSpiskred = lsSpiskred;
    }

    public void setDatsIzm(LocalDate datsIzm) {
        this.datsIzm = datsIzm;
    }

    public void setDatsIzmGrafik(Date datsIzmGrafik) {
        this.datsIzmGrafik = datsIzmGrafik;
    }

    public void setDatsIzmZalog(Date datsIzmZalog) {
        this.datsIzmZalog = datsIzmZalog;
    }

    public void setObjekt(String objekt) {
        this.objekt = objekt;
    }

    public void setValut(Byte valut) {
        this.valut = valut;
    }

    public void setKlass(Byte klass) {
        this.klass = klass;
    }

    public void setLsprosrProc(String lsprosrProc) {
        this.lsprosrProc = lsprosrProc;
    }

    public void setLs22812(String ls22812) {
        this.ls22812 = ls22812;
    }

    public void setDatsIzmAsoki(Date datsIzmAsoki) {
        this.datsIzmAsoki = datsIzmAsoki;
    }

    public void setXatar(Byte xatar) {
        this.xatar = xatar;
    }

    public void setGrafiks(List<Grafik> grafiks) {
        this.grafiks = grafiks;
    }

    public void setZalogs(List<Zalog> zalogs) {
        this.zalogs = zalogs;
    }

    public void setZalogXranenieList(List<ZalogXranenie> zalogXranenieList) {
        this.zalogXranenieList = zalogXranenieList;
    }

    public void setAzolikFiz(AzolikFiz azolikFiz) {
        this.azolikFiz = azolikFiz;
    }
}

