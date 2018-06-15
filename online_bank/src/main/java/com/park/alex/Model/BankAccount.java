package com.park.alex.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", unique = true)
    private String accountNumber;

    private Long amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    private UserEntity owner;
}
