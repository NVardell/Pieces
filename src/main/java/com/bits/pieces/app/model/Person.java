package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    int digits;
    String name;

    public int addDigits(int addDigits) {
        return digits += addDigits;
    }

    @Override
    public int hashCode() {
        log.warn("\n\n~~~~~ CALLING MY HASH FUNCTION ~~~~~\n\n");
        return digits;
    }

}
