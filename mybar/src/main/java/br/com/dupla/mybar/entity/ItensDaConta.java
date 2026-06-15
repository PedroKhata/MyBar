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

    public ItensDaConta() {}

    // Lembre-se de gerar os Getters e Setters de todos os campos aqui!
}