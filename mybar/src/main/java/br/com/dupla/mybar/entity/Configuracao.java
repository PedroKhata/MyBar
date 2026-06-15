package br.com.dupla.mybar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "configuracao")
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_ingresso_masc", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorIngressoMasc;

    @Column(name = "valor_ingresso_femin", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorIngressoFemin;

    @Column(name = "modo_operacao", nullable = false, length = 50)
    private String modoOperacao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;

    public Configuracao() {}

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public BigDecimal getValorIngressoMasc() { return valorIngressoMasc; }
    public void setValorIngressoMasc(BigDecimal valorIngressoMasc) { this.valorIngressoMasc = valorIngressoMasc; }
    public BigDecimal getValorIngressoFemin() { return valorIngressoFemin; }
    public void setValorIngressoFemin(BigDecimal valorIngressoFemin) { this.valorIngressoFemin = valorIngressoFemin; }
    public String getModoOperacao() { return modoOperacao; }
    public void setModoOperacao(String modoOperacao) { this.modoOperacao = modoOperacao; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
}