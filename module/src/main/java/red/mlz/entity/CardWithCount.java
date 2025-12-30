package red.mlz.entity;

import lombok.Data;

import java.util.List;

@Data
public class CardWithCount {
    private List<Card> list;
    private Long total;
}
