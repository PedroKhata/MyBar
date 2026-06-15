package br.com.dupla.mybar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String forma;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quem_lancou_pg_codigo", nullable = false)
    private Usuario quemLancouPg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quem_excluiu_pg_codigo")
    private Usuario quemExcluiuPg;

    public Pagamento() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getForma() { return forma; }
    public void setForma(String forma) { this.forma = forma; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
    public Usuario getQuemLancouPg() { return quemLancouPg; }
    public void setQuemLancouPg(Usuario quemLancouPg) { this.quemLancouPg = quemLancouPg; }
    public Usuario getQuemExcluiuPg() { return quemExcluiuPg; }
    public void setQuemExcluiuPg(Usuario quemExcluiuPg) { this.quemExcluiuPg = quemExcluiuPg; }
}