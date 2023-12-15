package br.com.application.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoContaEnum tipoConta;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private User titular;

    private double saldo;

    public enum TipoContaEnum {
        CONTA_CORRENTE,
        CONTA_POUPANCA
    }

}
