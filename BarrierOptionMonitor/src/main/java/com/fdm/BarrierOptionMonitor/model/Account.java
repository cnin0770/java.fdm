package com.fdm.BarrierOptionMonitor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currency;
    private double balance;
    @ManyToOne
    private Client client;

    public double gain(double amount) {
        balance += amount;
        return balance;
    }

    public double lose(double amount) {
        balance -= amount;
        return balance;
    }
}
