package io.ibd.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "opinions")
public class Opinion implements ModelEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    @Min(1) @Max(5)
    private int grade;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Product product;
}
