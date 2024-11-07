package ashsic.SpiritFare.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "card_effects")
public class CardEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String effectName;

    @Column
    private String effectDescription;

    @Column
    private Integer effectValue;

    // If the effect has a duration (in turns)
    @Column
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column
    private EffectType effectType;
} 