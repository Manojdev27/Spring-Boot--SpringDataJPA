package com.spring.jpa.pokemon.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Type1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(unique = true,name = "Type1")
    private String type1;

    @OneToMany(mappedBy = "type1")
    private List<Pokemon> pokemon;


}
