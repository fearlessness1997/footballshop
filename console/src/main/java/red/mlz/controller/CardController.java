package red.mlz.controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import red.mlz.domain.AdminCardInfoVO;
import red.mlz.domain.AdminCardList;
import red.mlz.domain.AdminCardListWrapVO;
import red.mlz.entity.Card;
import red.mlz.entity.CardWithCount;
import red.mlz.service.CardService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        List<AdminCardList> result = new ArrayList<>();
        for (Card card : cardLists) {
            AdminCardList vo = new AdminCardList();
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

    @RequestMapping("/card/admininfo")
    public AdminCardInfoVO adminCardInfoVO(@RequestParam("cardId") Integer id){
        Card info = cardService.getAdminCard(id);

        AdminCardInfoVO cardVO = new AdminCardInfoVO();
        cardVO.setName(info.getName());
        cardVO.setPrice(info.getPrice());
        cardVO.setIntroduction(info.getIntroduction());
        String coverImagesStr = info.getCoverImages();
        cardVO.setCoverImages(List.of(StringUtils.isNotBlank(coverImagesStr) ? coverImagesStr.split("\\$") : new String[0]));
        // 关键转换
        cardVO.setCreateTime(timestampToLocalDateTime(info.getCreateTime()));
        cardVO.setUpdateTime(timestampToLocalDateTime(info.getUpdateTime()));

        return cardVO;
    }
    private LocalDateTime timestampToLocalDateTime(Long timestamp) {
        if (timestamp == null) return null;
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),  // 数据库int存的是秒级时间戳
                ZoneId.systemDefault()
        );
    }

}