package io.ibd.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements ModelEntity<String> {

    @Id
    private String id;

    private String password;

    private Boolean isAdmin;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Opinion> opinions;
}
