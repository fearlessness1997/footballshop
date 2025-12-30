package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class CardListWrapVO {
    private List<CardListVO> list;

    public CardListWrapVO(List<CardListVO> list) {
        this.list = list;
    }
}
