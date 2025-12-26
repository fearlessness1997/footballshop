package red.mlz.domain.VO;

import java.util.List;

public class CardListWrapVO {
    private List<CardListVO> list;

    public CardListWrapVO(List<CardListVO> list) {
        this.list = list;
    }

    public List<CardListVO> getList() {
        return list;
    }

    public void setList(List<CardListVO> list) {
        this.list = list;
    }
}
