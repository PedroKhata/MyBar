package br.com.dupla.mybar.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "itens_da_conta")
public class ItensDaConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_cardapio_codigo", nullable = false)
    private ItemCardapio itemCardapio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quem_lancou_codigo", nullable = false)
    private Usuario quemLancou;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quem_removeu_codigo")
    private Usuario quemRemoveu;

    // Datas e Horas (Omitidos os getters/setters abaixo para poupar espaço, mas você pode gerá-os na IDE)
    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @Column(name = "hora_solicitacao", nullable = false)
    private LocalTime horaSolicitacao;

    @Column(name = "data_recebimento_cozinha")
    private LocalDate dataRecebimentoCozinha;

    @Column(name = "hora_recebimento_cozinha")
    private LocalTime horaRecebimentoCozinha;

    @Column(name = "data_entrega_cozinha")
    private LocalDate dataEntregaCozinha;

    @Column(name = "hora_entrega_cozinha")
    private LocalTime horaEntregaCozinha;

    @Column(name = "data_recebimento_bar")
    private LocalDate dataRecebimentoBar;

    @Column(name = "hora_recebimento_bar")
    private LocalTime horaRecebimentoBar;

    @Column(name = "data_entrega_bar")
    private LocalDate dataEntregaBar;

    @Column(name = "hora_entrega_bar")
    private LocalTime horaEntregaBar;

    public ItensDaConta() {

    }

    // Lembre-se de gerar os Getters e Setters de todos os campos aqui!
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public ItemCardapio getItemCardapio() {
        return itemCardapio;
    }

    public void setItemCardapio(ItemCardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    public Usuario getQuemLancou() {
        return quemLancou;
    }

    public void setQuemLancou(Usuario quemLancou) {
        this.quemLancou = quemLancou;
    }

    public Usuario getQuemRemoveu() {
        return quemRemoveu;
    }

    public void setQuemRemoveu(Usuario quemRemoveu) {
        this.quemRemoveu = quemRemoveu;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalTime getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(LocalTime horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public LocalDate getDataRecebimentoCozinha() {
        return dataRecebimentoCozinha;
    }

    public void setDataRecebimentoCozinha(LocalDate dataRecebimentoCozinha) {
        this.dataRecebimentoCozinha = dataRecebimentoCozinha;
    }

    public LocalTime getHoraRecebimentoCozinha() {
        return horaRecebimentoCozinha;
    }

    public void setHoraRecebimentoCozinha(LocalTime horaRecebimentoCozinha) {
        this.horaRecebimentoCozinha = horaRecebimentoCozinha;
    }

    public LocalDate getDataEntregaCozinha() {
        return dataEntregaCozinha;
    }

    public void setDataEntregaCozinha(LocalDate dataEntregaCozinha) {
        this.dataEntregaCozinha = dataEntregaCozinha;
    }

    public LocalTime getHoraEntregaCozinha() {
        return horaEntregaCozinha;
    }

    public void setHoraEntregaCozinha(LocalTime horaEntregaCozinha) {
        this.horaEntregaCozinha = horaEntregaCozinha;
    }

    public LocalDate getDataRecebimentoBar() {
        return dataRecebimentoBar;
    }

    public void setDataRecebimentoBar(LocalDate dataRecebimentoBar) {
        this.dataRecebimentoBar = dataRecebimentoBar;
    }

    public LocalTime getHoraRecebimentoBar() {
        return horaRecebimentoBar;
    }

    public void setHoraRecebimentoBar(LocalTime horaRecebimentoBar) {
        this.horaRecebimentoBar = horaRecebimentoBar;
    }

    public LocalDate getDataEntregaBar() {
        return dataEntregaBar;
    }

    public void setDataEntregaBar(LocalDate dataEntregaBar) {
        this.dataEntregaBar = dataEntregaBar;
    }

    public LocalTime getHoraEntregaBar() {
        return horaEntregaBar;
    }

    public void setHoraEntregaBar(LocalTime horaEntregaBar) {
        this.horaEntregaBar = horaEntregaBar;
    }
}