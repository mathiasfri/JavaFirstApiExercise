package com.example.external_api.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Nationality {
    int count;
    String name;
    List<Country> country;
}
