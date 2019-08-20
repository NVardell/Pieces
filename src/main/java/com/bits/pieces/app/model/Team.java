package com.bits.pieces.app.model;

import lombok.Data;

import java.util.Optional;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/30/2019
 */
@Data
public class Team {
    private final String name;
    private final Optional<Person> teamMembers;
}
