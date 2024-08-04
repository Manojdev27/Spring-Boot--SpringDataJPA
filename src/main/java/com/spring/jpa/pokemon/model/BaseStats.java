package com.spring.jpa.pokemon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BaseStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pokeDexNumber;
    @Column(name = "HP")
    private int hp;
    @Column(name = "Attack")
    private int attack;
    @Column(name = "Defense")
    private int defense;
    @Column(name = "SpecialAttack")
    private int specialAttack;
    @Column(name = "SpecialDefense")
    private int specialDefense;
    @Column(name = "Speed")
    private int speed;



}
