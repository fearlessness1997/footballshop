package red.mlz.service;

import io.micrometer.common.util.StringUtils;
import red.mlz.entity.Card;
import red.mlz.domain.DTO.CardDTO;
import red.mlz.domain.VO.CardListVO;
import red.mlz.domain.VO.CardVO;
import red.mlz.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    public List<CardListVO> getCardList() {
        List<Card> cardLists = cardMapper.getList();
        List<CardListVO> result = new ArrayList<>();
        for (Card card : cardLists) {
            CardListVO vo = new CardListVO();
            vo.setCardId(card.getId());

            String coverImagesStr = card.getCardImages();
            String wallImageStr = "";
            if (coverImagesStr != null && !coverImagesStr.trim().isEmpty()) {
                wallImageStr = coverImagesStr.split("\\$")[0];
            }
            vo.setWallImage(wallImageStr);

            vo.setName(card.getName());
            vo.setPrice(card.getPrice());
            result.add(vo);
        }
        return result;
    }

    public CardVO getCard(Integer id) {
        Card info = cardMapper.getInfo(id);

        CardVO cardVO = new CardVO();
        cardVO.setName(info.getName());
        cardVO.setPrice(info.getPrice());
        cardVO.setIntroduction(info.getIntroduction());

        String coverImagesStr = info.getCardImages();
        cardVO.setCoverImages(List.of(StringUtils.isNotBlank(coverImagesStr) ? coverImagesStr.split("\\$") : new String[0]));
        return cardVO;
    }

    public int insert(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCoverImages(card.getCardImages());
        cardDTO.setName(card.getName());
        cardDTO.setPrice(card.getPrice());
        cardDTO.setIntroduction(card.getIntroduction());
        long mills = System.currentTimeMillis();
        cardDTO.setCreateTime(mills);
        cardDTO.setUpdateTime(mills);
        return cardMapper.insert(cardDTO);
    }

    public int delete(Integer id) {
        return cardMapper.delete(id);
    }

    public int update(Integer id, String coverImages, String name, Float price, String introduction) {
        long mills = System.currentTimeMillis();
        long updateTime = mills;
        return cardMapper.update(id, coverImages, name, price, introduction, updateTime);
    }
}





















