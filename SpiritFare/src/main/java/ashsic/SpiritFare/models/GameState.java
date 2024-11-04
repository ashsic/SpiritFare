package ashsic.SpiritFare.models;

import lombok.Data;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Array;
import java.util.HashMap;

@Data
public class GameState {
    private Long id;
    private Player[] players;
    private int currentTurn;
    private int activePlayerIndex;
    private GamePhase currentPhase;
    private Map<Player, Integer> playerHealth;
    private Map<Player, Integer> playerMana;
    private Map<Player, Integer> playerBlood;
    private Map<Player, List<Card>> playerHands;
    private Map<Player, List<Card>> playerDecks;
    private Map<Player, List<Card>> playerFields;
    private Map<Player, List<Card>> playerDiscards;

    public enum GamePhase {
        DRAW,
        MAIN,
        COMBAT,
        END
    }

    public GameState() {
        this.playerHealth = new HashMap<>();
        this.playerMana = new HashMap<>();
        this.playerBlood = new HashMap<>();
        this.playerHands = new HashMap<>();
        this.playerDecks = new HashMap<>();
        this.playerFields = new HashMap<>();
        this.playerDiscards = new HashMap<>();
    }

    public boolean isPlayerTurn(Player player) {
        return players.get(activePlayerIndex).equals(player);
    }

    public void nextTurn() {
        activePlayerIndex = (activePlayerIndex + 1) % players.size();
        currentTurn++;
        currentPhase = GamePhase.DRAW;
    }

    public void nextPhase() {
        currentPhase = switch (currentPhase) {
            case DRAW -> GamePhase.MAIN;
            case MAIN -> GamePhase.COMBAT;
            case COMBAT -> GamePhase.END;
            case END -> {
                nextTurn();
                yield GamePhase.DRAW;
            }
        };
    }

    public boolean hasEnoughResources(Player player, Card card) {
        return switch (card.getResourceType()) {
            case MANA -> playerMana.getOrDefault(player, 0) >= card.getResourceCost();
            case BLOOD -> playerBlood.getOrDefault(player, 0) >= card.getResourceCost();
        };
    }

    public void spendResources(Player player, Card card) {
        switch (card.getResourceType()) {
            case MANA -> playerMana.merge(player, -card.getResourceCost(), Integer::sum);
            case BLOOD -> playerBlood.merge(player, -card.getResourceCost(), Integer::sum);
        }
    }
} 