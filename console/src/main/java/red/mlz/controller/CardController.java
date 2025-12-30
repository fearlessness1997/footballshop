package red.mlz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.mlz.domain.AdminCardListVO;
import red.mlz.domain.AdminCardListWrapVO;
import red.mlz.entity.Card;
import red.mlz.entity.CardWithCount;
import red.mlz.service.CardService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/card/create")
    public String insertController(@RequestParam(name = "coverImages") String coverImages,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "price") Float price,
                                   @RequestParam(name = "introduction") String introduction) {
        return cardService.insert(coverImages, name, price, introduction);

    }

    @RequestMapping("/card/delete")
    public String deleteController(@RequestParam(name = "cardId") Integer id) {
        int result = cardService.delete(id);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/card/update")
    public String updateController(@RequestParam(name = "cardId") Integer id,
                                   @RequestParam(name = "coverImages") String coverImages,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "price") Float price,
                                   @RequestParam(name = "introduction") String introduction) {
        int result = cardService.update(id, coverImages, name, price, introduction);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/card/adminlist")
    public AdminCardListWrapVO adminListController(@RequestParam(name = "page") Integer page){

        int pageSize = 7;
        CardWithCount cardWithCount = cardService.getAdminCardList(page,pageSize);
        List<Card> cardLists =cardWithCount.getList();

        List<AdminCardListVO> result = new ArrayList<>();
        for (Card card : cardLists) {
            AdminCardListVO vo = new AdminCardListVO();
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

        AdminCardListWrapVO vo = new AdminCardListWrapVO();
        vo.setList(result);
        vo.setTotal(cardWithCount.getTotal());
        vo.setPageSize(pageSize);

        return vo;
    }
}