package red.mlz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.mlz.entity.Card;
import red.mlz.dto.CardWithCount;
import red.mlz.mapper.CardMapper;

import java.util.List;

@Slf4j
@Service
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    public List<Card> getCardList() {
        return cardMapper.getList();
    }

    public Card getCard(Integer id) {
        return cardMapper.getInfo(id);
    }

    public String insert(String coverImages, String name, Float price, String introduction) {
        Card card = new Card();
        card.setCoverImages(coverImages);
        card.setName(name);
        card.setPrice(price);
        card.setIntroduction(introduction);
        long mills = System.currentTimeMillis() / 1000;
        card.setCreateTime(mills);
        card.setUpdateTime(mills);

        try {
            int rows = cardMapper.insert(card);
            if (rows > 0) {
                Long generatedId = card.getId();
                return "自增ID是:" + generatedId;
            } else {
                return "失败";
            }
        } catch (Exception e) {
            log.error("插入数据失败", e);
            return "失败";
        }
    }

    public int delete(Integer id) {
        return cardMapper.delete(id);
    }

    public int update(Integer id, String coverImages, String name, Float price, String introduction) {
        long mills = System.currentTimeMillis() / 1000;
        long updateTime = mills;
        return cardMapper.update(id, coverImages, name, price, introduction, updateTime);
    }

    public CardWithCount getAdminCardList(Integer page, Integer pageSize) {
        int offset = (page - 1)*pageSize;
        List<Card> list = cardMapper.getPageList(offset,pageSize);
        Long total = cardMapper.getTotal();
        CardWithCount cardWithCount = new CardWithCount();
        cardWithCount.setList(list);
        cardWithCount.setTotal(total);
        return cardWithCount;
    }

    public Card getAdminCard(Integer id) {
        return cardMapper.getInfo(id);
    }
}