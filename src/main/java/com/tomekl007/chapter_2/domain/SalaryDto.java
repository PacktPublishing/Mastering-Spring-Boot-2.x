package com.tomekl007.chapter_2.domain;

public class SalaryDto {

    private String userId;
    private String accountFrom;
    private String accountTo;
    private Long amount;

    public SalaryDto() {
    }

    public SalaryDto(String userId, String accountFrom, String accountTo, Long amount) {
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SalaryDto{" +
            "userId='" + userId + '\'' +
            ", accountFrom='" + accountFrom + '\'' +
            ", accountTo='" + accountTo + '\'' +
            ", amount=" + amount +
            '}';
    }
}
