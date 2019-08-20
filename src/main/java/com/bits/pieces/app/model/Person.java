package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    int digits;
    String name;

    public int addDigits(int addDigits) {
        return digits += addDigits;
    }
}
