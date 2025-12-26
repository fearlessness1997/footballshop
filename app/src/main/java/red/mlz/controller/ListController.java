package red.mlz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.mlz.domain.VO.CardListVO;
import red.mlz.domain.VO.CardVO;
import red.mlz.domain.VO.CardListWrapVO;
import red.mlz.service.CardService;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class ListController {

    @Autowired
    private CardService cardService;
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/card/list")
    public CardListWrapVO cardList() {
        List<CardListVO> List = cardService.getCardList();
        return new CardListWrapVO(List);
    }

    @RequestMapping("/card/info")
    public CardVO cardInfo(@RequestParam(name = "cardId") Integer id) {
        return cardService.getCard(id);
    }

    @GetMapping("/test")
    public String test() throws Exception {
        System.out.println("当前数据库: " + dataSource.getConnection().getCatalog());
        return "ok";
    }
}























