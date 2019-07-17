package com.tomekl007.chapter_2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String accountFrom;
    private String accountTo;
    private Long amount;

    public Salary() {
    }

    public Salary(String userId, String accountFrom, String accountTo, Long amount) {
        this.userId = userId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Salary(Long id, String userId, String accountFrom, String accountTo, Long amount) {
        this.id = id;
        this.userId = userId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", accountFrom='" + accountFrom + '\'' +
            ", accountTo='" + accountTo + '\'' +
            ", amount=" + amount +
            '}';
    }
}
