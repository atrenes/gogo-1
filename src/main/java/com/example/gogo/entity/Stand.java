package com.example.gogo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stands")
@Generated
public class Stand {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "power", nullable = false)
    private Double power;

    @Column(name = "speed", nullable = false)
    private Double speed;

    @Column(name = "range", nullable = false)
    private Double range;

    @Column(name = "durability", nullable = false)
    private Double durability;

    @Column(name = "precision", nullable = false)
    private Double precision;

    public double getStats() {
        return power + speed + range + durability + precision;
    }
}
