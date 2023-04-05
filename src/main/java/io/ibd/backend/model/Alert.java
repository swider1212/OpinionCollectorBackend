package io.ibd.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "alerts")
public class Alert implements ModelEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    private boolean read;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Product product;
}
