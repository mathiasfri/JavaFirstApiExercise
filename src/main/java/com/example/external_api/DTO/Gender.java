package com.example.external_api.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Gender {
    String gender;
    String name;
    int count;
    double probability;
}

