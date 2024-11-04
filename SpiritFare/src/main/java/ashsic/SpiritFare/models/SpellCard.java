package ashsic.SpiritFare.models;

public class SpellCard extends Card {
    @Override
    public CardType getType() {
        return CardType.SPELL;
    }

    @Override
    public void activate(GameState state) {
        // Spell-specific activation logic
    }

    @Override
    public boolean canActivate(GameState state) {
        // Spell-specific activation check
        return true;
    } 
}
