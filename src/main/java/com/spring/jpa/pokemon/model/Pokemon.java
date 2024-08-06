package com.spring.jpa.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "pokemonName")
    private String pokemonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type1",referencedColumnName = "typeId")
    private Type1 type1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type2",referencedColumnName = "typeId")
    private Type2 type2;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "pokemonId", referencedColumnName = "pokeDexNumber")
    private BaseStats baseStats;

    @JsonIgnore
    public Type1 getType1() {
        return type1;
    }
    @JsonIgnore
    public Type2 getType2() {
        return type2;
    }
    @JsonIgnore
    public BaseStats getBaseStats(){
        return baseStats;
    }

//    @Override
//    public String toString() {
//        return "Pokemon{" +
//                "pokemonId=" + pokemonId +
//                ", pokeDexNumber=" + pokeDexNumber +
//                ", pokemonName='" + pokemonName +
//                '}';
//    }
}
