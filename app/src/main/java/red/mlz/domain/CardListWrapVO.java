package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class CardListWrapVO {
    private List<CardList> list;
    private Boolean isEnd;

    public CardListWrapVO(List<CardList> list) {
        this.list = list;
    }
}
