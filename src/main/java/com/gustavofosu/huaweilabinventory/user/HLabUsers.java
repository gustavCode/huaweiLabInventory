package com.gustavofosu.huaweilabinventory.user;

import com.gustavofosu.huaweilabinventory.role.HLabRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("WhileLoopReplaceableByForEach")
@Entity
@Getter                 // Annotation to add getters for the fields
@Setter                 // Annotation to add setters for the fields
@NoArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_name_unique",
                        columnNames = "user_name"
                )
        }
)
public class HLabUsers {

    // == private fields ==
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userID;                // id for the user
    private String lastName;            // last name of the user
    private String otherNames;          // other names of the user
    private String indexNumber;         // index number of the user

    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;            // username for the user
    private String password;            // password of the user

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<HLabRole> userRoles = new HashSet<>();  //set of roles

    // == public methods ==
    // method to add roles
    public void addRole(HLabRole hLabRole) {
        this.userRoles.add(hLabRole);
    }

    // method to check if user has a role
    public boolean hasRole(String roleName) {
        Iterator<HLabRole> iterator = this.userRoles.iterator();
        while (iterator.hasNext()) {
            HLabRole role = iterator.next();
            if (role.getRoleName().equals(roleName)) {
                return true;
            }
        }

        return false;
    }
}
