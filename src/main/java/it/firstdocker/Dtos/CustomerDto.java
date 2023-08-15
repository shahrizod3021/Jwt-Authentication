package it.firstdocker.Dtos;

import it.firstdocker.Entity.Gender;

import java.util.List;


public record CustomerDto (
    Long id,
    String name,
    String email,
    Gender gender,
    Integer age,
    List<String> roles,
    String username
){

}