package io.ibd.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product implements ModelEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private float price;

    private boolean hidden;

    private String description;

    private String imageUrl;

    @Transient
    private int rating;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<Alert> alerts;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<Opinion> opinions;
}
