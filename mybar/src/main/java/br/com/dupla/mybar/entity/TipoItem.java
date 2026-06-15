package br.com.dupla.mybar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipo_item")
public class TipoItem {

    @Id
    private Integer codigo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal gorjeta;

    @Column(nullable = false)
    private Boolean cozinha;

    public TipoItem() {}

    // Getters e Setters
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getGorjeta() { return gorjeta; }
    public void setGorjeta(BigDecimal gorjeta) { this.gorjeta = gorjeta; }
    public Boolean getCozinha() { return cozinha; }
    public void setCozinha(Boolean cozinha) { this.cozinha = cozinha; }
}