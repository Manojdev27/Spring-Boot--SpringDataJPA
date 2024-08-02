package com.spring.jpa.pokemon.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Type1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(name = "Type1")
    private String type1;

    @OneToMany(mappedBy = "type1")

    private List<Pokemon> pokemon = new ArrayList<>();

}
