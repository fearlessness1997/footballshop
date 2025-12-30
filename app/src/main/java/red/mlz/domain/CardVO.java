package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class CardVO {
    private List<String> coverImages;
    private String name;
    private Float price;
    private String introduction;
}
