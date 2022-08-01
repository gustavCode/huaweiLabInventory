package com.gustavofosu.huaweilabinventory.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter                     // Annotation to add getters for fields
@Setter                     // Annotation to add setters for fields
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HLabRole {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "role_id")
    private Long id;                // id for the role
    private String roleName;        // name of the role (ADMIN, USER)

    // constructor that takes id as a parameter
    public HLabRole(Long id) {
        this.id = id;
    }

    //constructor that takes roleName as a parameter
    public HLabRole(String roleName) {
        this.roleName = roleName;
    }

    // toString method that returns the roleName
    @Override
    public String toString() {
        return this.roleName;
    }
}
