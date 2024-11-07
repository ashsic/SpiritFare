package ashsic.SpiritFare.services;

import ashsic.SpiritFare.models.*;
import ashsic.SpiritFare.repositories.CardRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card findCardById(Long id) {
        return cardRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> findByElement(Element element) {
        return cardRepository.findByElement(element);
    }

    public List<SpiritCard> findStrongSpirits(int minAttack) {
        return cardRepository.findSpiritCardByAttackGreaterThan(minAttack);
    }
} 