package ashsic.SpiritFare.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class EquipmentCard extends Card {
    @Override
    public CardType getType() {
        return CardType.EQUIPMENT;
    }

    @Override
    public void activate(GameState state) {
        // Equipment-specific activation logic
    }

    @Override
    public boolean canActivate(GameState state) {
        // Equipment-specific activation check
        return true;
    }
} 