package com.rafalcurylo.ee8.jaxrs.server;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Stateless
public class PeopleService {

    public List<Person> getAll() {

        Person john = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .id(1)
                .city("KRK")
                .birthday(LocalDate.of(1982, 11, 22))
                //.gender(Person.Gender.MALE)
                .build();

        Person ann = Person.builder()
                .firstName("Ann")
                .lastName("Smith")
                .id(2)
                .birthday(LocalDate.of(1982, 8, 20))
                .gender(Person.Gender.FEMALE)
                .build();

        Person caren = Person.builder()
                .firstName("Caren")
                .lastName("Smith")
                .id(3)
                .birthday(LocalDate.of(2014, 8, 28))
                .gender(Person.Gender.FEMALE)
                .build();

        return Arrays.asList(john, ann, caren);

    }


}
