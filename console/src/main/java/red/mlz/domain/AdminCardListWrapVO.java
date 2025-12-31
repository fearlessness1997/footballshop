package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class AdminCardListWrapVO {
    private List<AdminCardList> list;
    private Long total;
    private Integer pageSize;
}
