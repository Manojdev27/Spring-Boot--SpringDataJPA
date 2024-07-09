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
public class Type2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(unique = true,name = "type2")
    private String type2;

    @OneToMany(mappedBy = "type2")
    private List<Pokemon> pokemon;

}
