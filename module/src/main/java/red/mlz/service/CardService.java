package red.mlz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.mlz.entity.Card;
import red.mlz.entity.DTO.CardDTO;
import red.mlz.mapper.CardMapper;

import java.util.List;

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

    public int insert(String coverImages, String name, Float price, String introduction) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCoverImages(coverImages);
        cardDTO.setName(name);
        cardDTO.setPrice(price);
        cardDTO.setIntroduction(introduction);
        long mills = System.currentTimeMillis() / 1000;
        cardDTO.setCreateTime(mills);
        cardDTO.setUpdateTime(mills);
        return cardMapper.insert(cardDTO);
    }

    public int delete(Integer id) {
        return cardMapper.delete(id);
    }

    public int update(Integer id, String coverImages, String name, Float price, String introduction) {
        long mills = System.currentTimeMillis() / 1000;
        long updateTime = mills;
        return cardMapper.update(id, coverImages, name, price, introduction, updateTime);
    }
}





















