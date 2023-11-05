package com.example.gogo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fights")
public class Fight {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_stand_id", nullable = false)
    private Stand firstStand;

    @ManyToOne
    @JoinColumn(name = "second_stand_id", nullable = false)
    private Stand secondStand;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    private Stand winner;

    @ManyToOne
    @JoinColumn(name = "dropped_item_id")
    private Item droppedItem;
}
