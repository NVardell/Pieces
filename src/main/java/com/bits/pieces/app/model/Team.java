package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Team Model Class
 *
 * @author Nate Vardell
 * @since 6/30/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    String name;
    Optional<Person> teamMembers;
}
