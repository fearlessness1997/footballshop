package red.mlz.domain;

import lombok.Data;

import java.util.List;

@Data
public class AdminCardListWrapVO {
    private List<AdminCardListVO> list;
    private Long total;
    private Integer pageSize;
}
