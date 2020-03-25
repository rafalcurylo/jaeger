package com.rafalcurylo.ee8.jaxrs.server;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Person {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final Gender gender;
    private final String city;

    public enum Gender {

        MALE("M"),
        FEMALE("F");

        @Getter
        private final String shortSymbol;

        Gender(String shortSymbol) {
            this.shortSymbol = shortSymbol;
        }

    }

}
