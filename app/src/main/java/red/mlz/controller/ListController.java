package red.mlz.controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.mlz.domain.CardListVO;
import red.mlz.domain.CardListWrapVO;
import red.mlz.domain.CardVO;
import red.mlz.entity.Card;
import red.mlz.service.CardService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ListController {

    @Autowired
    private CardService cardService;
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/card/list")
    public CardListWrapVO cardList() {
        List<Card> cardLists = cardService.getCardList();

        List<CardListVO> result = new ArrayList<>();
        for (Card card : cardLists) {
            CardListVO vo = new CardListVO();
            vo.setCardId(card.getId());
            String coverImagesStr = card.getCoverImages();
            String wallImageStr = "";
            if (coverImagesStr != null && !coverImagesStr.trim().isEmpty()) {
                wallImageStr = coverImagesStr.split("\\$")[0];
            }
            vo.setWallImage(wallImageStr);
            vo.setName(card.getName());
            vo.setPrice(card.getPrice());
            result.add(vo);
        }
        return new CardListWrapVO(result);
    }

    @RequestMapping("/card/info")
    public CardVO cardInfo(@RequestParam(name = "cardId") Integer id) {

        Card info = cardService.getCard(id);
        CardVO cardVO = new CardVO();
        cardVO.setName(info.getName());
        cardVO.setPrice(info.getPrice());
        cardVO.setIntroduction(info.getIntroduction());
        String coverImagesStr = info.getCoverImages();
        cardVO.setCoverImages(List.of(StringUtils.isNotBlank(coverImagesStr) ? coverImagesStr.split("\\$") : new String[0]));

        return cardVO;
    }

}
 






















