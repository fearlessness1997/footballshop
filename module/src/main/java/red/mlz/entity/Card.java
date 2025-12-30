package red.mlz.entity;

import lombok.Data;

@Data
public class Card {
    private Integer id;
    private String coverImages;
    private String name;
    private Float price;
    private String introduction;
    private Long createTime;
    private Long updateTime;
    private int isDeleted;
}
