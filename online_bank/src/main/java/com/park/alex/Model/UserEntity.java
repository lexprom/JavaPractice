package com.park.alex.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    private String name;

    private String lastname;

    private String middlename;

    @Column(name = "IS_ADMIN")
    private boolean admin;

    @OneToMany(mappedBy = "owner")
    private List<BankAccount> accounts = new ArrayList<>();
}
