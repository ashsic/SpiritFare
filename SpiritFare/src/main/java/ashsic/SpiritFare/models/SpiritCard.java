package ashsic.SpiritFare.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class SpiritCard extends Card {
    private int attack;
    private int health;

    @Override
    public CardType getType() {
        return CardType.SPIRIT;
    }

    @Override
    public void activate(GameState state) {
        // Spirit-specific activation logic
    }

    @Override
    public boolean canActivate(GameState state) {
        // Spirit-specific activation check
        return true;
    }
} 