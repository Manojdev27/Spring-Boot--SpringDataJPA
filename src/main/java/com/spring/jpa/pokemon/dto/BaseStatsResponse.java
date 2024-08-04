package com.spring.jpa.pokemon.dto;

import lombok.Data;

@Data
public class BaseStatsResponse {
    private int pokeDexNumber;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

}
