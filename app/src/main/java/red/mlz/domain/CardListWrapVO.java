package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class CardListWrapVO {
    private List<CardListVO> list;
    private Boolean isEnd;

    public CardListWrapVO(List<CardListVO> list) {
        this.list = list;
    }
}
