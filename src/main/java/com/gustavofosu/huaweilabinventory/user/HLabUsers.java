package com.gustavofosu.huaweilabinventory.user;

import com.gustavofosu.huaweilabinventory.role.HLabRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Getter
@Setter
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
    private Long userID;
    private String lastName;
    private String otherNames;
    private String indexNumber;

    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<HLabRole> userRoles = new HashSet<>();

    // == public methods ==
    public void addRole(HLabRole hLabRole) {
        this.userRoles.add(hLabRole);
    }

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
