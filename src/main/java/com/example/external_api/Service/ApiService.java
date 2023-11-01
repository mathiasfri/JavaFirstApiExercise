package com.example.external_api.Service;

import com.example.external_api.DTO.Age;
import com.example.external_api.DTO.CombinedResponse;
import com.example.external_api.DTO.Gender;
import com.example.external_api.DTO.Nationality;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {
    Mono<Gender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<Gender> gender = client.get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(Gender.class);
        return gender;
    }

    Mono<Age> getAgeForName(String name){
        WebClient client = WebClient.create();
        Mono<Age> age = client.get()
                .uri("https://api.agify.io?name="+name)
                .retrieve()
                .bodyToMono(Age.class);
        return age;
    }

    Mono<Nationality> getNationalityForName(String name){
        WebClient client = WebClient.create();
        Mono<Nationality> nationality = client.get()
                .uri("https://api.nationalize.io/?name="+name)
                .retrieve()
                .bodyToMono(Nationality.class);
        return nationality;
    }

    public Mono<CombinedResponse> fetchNameDetails(String name){
        Mono<Age> age = getAgeForName(name);
        Mono<Gender> gender = getGenderForName(name);
        Mono<Nationality> nationality = getNationalityForName(name);

        return Mono.zip(age, gender, nationality)
                .map(tuple -> new CombinedResponse(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}