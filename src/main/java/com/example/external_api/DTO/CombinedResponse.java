package com.example.external_api.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CombinedResponse {
    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;
    String country;
    double countryProbability;

    public CombinedResponse(Age age, Gender gender, Nationality nationality) {
        this.name = age.getName();
        this.gender = gender.getGender();
        this.genderProbability = gender.getProbability() * 100;
        this.age = age.getAge();
        this.ageCount = age.getCount();
        this.country = nationality.getCountry().get(0).getCountry_id();
        this.countryProbability = nationality.getCountry().get(0).getProbability() * 100;
    }
}
