package com.bits.pieces.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person {

    Double money;

    Employee(Person person) {
        this.digits = person.digits;
        this.name = person.name;
        this.money = 0.0;
    }

    Employee(int digits, String name, double money) {
        this.digits = digits;
        this.name = name;
        this.money = money;
    }

    public void salaryIncrement(double percent) {
        this.money = (double) Math.round(money * (1 + (percent / 100)));
    }
}
