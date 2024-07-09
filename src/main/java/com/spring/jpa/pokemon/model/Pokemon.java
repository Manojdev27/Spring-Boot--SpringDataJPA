package com.spring.jpa.pokemon.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pokemonId;

    @Column(name = "pokeDexNumber")
    private int pokeDexNumber;

    @Column(unique = true,name = "pokemonName")
    private String pokemonName;

    @ManyToOne()
    @JoinColumn(name = "type1")
    private Type1 type1;

    @ManyToOne()
    @JoinColumn(name = "type2")
    private Type2 type2;
}
