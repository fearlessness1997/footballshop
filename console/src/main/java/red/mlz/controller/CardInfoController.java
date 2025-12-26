package red.mlz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.mlz.entity.Card;
import red.mlz.service.CardService;

@RestController
public class CardInfoController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/card/create")
    public String insertController(Card card) {
        int result = cardService.insert(card);
        return result == 1 ? "成功" : "失败";
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
}























