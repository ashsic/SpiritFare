package ashsic.SpiritFare.repositories;

import ashsic.SpiritFare.models.Card;
import ashsic.SpiritFare.models.SpiritCard;
import ashsic.SpiritFare.models.EquipmentCard;
import ashsic.SpiritFare.models.Element;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    // Generic queries
    List<Card> findByElement(Element element);
    List<Card> findByResourceCost(int cost);
    
    // Spirit card specific queries
    List<SpiritCard> findSpiritCardByAttackGreaterThan(int attack);
    List<SpiritCard> findSpiritCardByHealthGreaterThan(int health);
} 