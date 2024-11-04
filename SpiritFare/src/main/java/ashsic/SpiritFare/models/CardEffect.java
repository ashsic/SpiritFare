package ashsic.SpiritFare.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEffect {
    private Long id;
    private String name;
    private String description;
    private int magnitude;
    private String type;
} 