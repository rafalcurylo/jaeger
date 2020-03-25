package com.rafalcurylo.ee8.jaxrs.client;

import lombok.Getter;
import lombok.Setter;

import javax.json.Json;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
public class Person {

    @JsonbProperty
    private String firstName;

    @JsonbTypeAdapter(LocalDateAdapter.class)
    @JsonbProperty
    private LocalDate birthday;

    @JsonbProperty
    @JsonbTypeAdapter(GenderAdapter.class)
    private Gender gender;

    public static class LocalDateAdapter implements JsonbAdapter<LocalDate, JsonValue> {
        @Override
        public LocalDate adaptFromJson(JsonValue v) {
            return LocalDate.parse(((JsonString)v).getString());
        }

        @Override
        public JsonValue adaptToJson(LocalDate v) {
            return Json.createValue(v.toString());
        }
    }

    public static class GenderAdapter implements JsonbAdapter<Gender, JsonValue> {
        @Override
        public Gender adaptFromJson(JsonValue v) {
            String value = ((JsonString)v).getString();
            return Gender.valueOf(value);
        }

        @Override
        public JsonValue adaptToJson(Gender v) {
            return Json.createValue(v.toString());
        }
    }

    public enum Gender {

        MALE, FEMALE;

    }

}
