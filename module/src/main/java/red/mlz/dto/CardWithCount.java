package red.mlz.dto;

import lombok.Data;
import red.mlz.entity.Card;

import java.util.List;

@Data
public class CardWithCount {
    private List<Card> list;
    private Long total;
}
