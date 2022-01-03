package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

/**
 * Person Model Class
 *
 * @author Nate Vardell
 * @since 6/29/2019
 */
@Data
@Log
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
        log.warning("\\n\\n~~~~~ CALLING MY HASH FUNCTION ~~~~~\\n\\n");
        return digits;
    }

}
