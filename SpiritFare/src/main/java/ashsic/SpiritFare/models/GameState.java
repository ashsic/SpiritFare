package ashsic.SpiritFare.models;

import lombok.Data;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Data
public class GameState {
    private Long id;
    private Player[] players;
    private int currentTurn;
    private int activePlayerIndex;
    private GamePhase currentPhase;
    private Map<Player, Integer> playerMana;
    private Card[][] playerHands;
    private Map<Player, List<Card>> playerDecks;
    private Map<Player, Card[]> playerFields;
    private Map<Player, List<Card>> playerDiscards;

    public enum GamePhase {
        DRAW,
        MAIN,
        COMBAT,
        END
    }

    public GameState() {
        this.players = new Player[2];
        this.playerMana = new HashMap<>();
        this.playerHands = new Card[2][6];
        this.playerDecks = new HashMap<>();
        this.playerFields = new HashMap<>();
        this.playerDiscards = new HashMap<>();
    }

    public boolean isPlayerTurn(Player player) {
        return players[activePlayerIndex].equals(player);
    }

    public void nextTurn() {
        activePlayerIndex = (activePlayerIndex + 1) % 2;
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