package red.mlz.mapper;

import org.apache.ibatis.annotations.*;
import red.mlz.entity.Card;

import java.util.List;

@Mapper
public interface CardMapper {

    @Select("select id,cover_images ,name,price from card")
    List<Card> getList();

    @Select("select * from card where id=#{id}")
    Card getInfo(@Param("id") Integer id);

    int insert(@Param("card") Card card);

    @Update("update card set is_deleted=1 where id=#{id}")
    int delete(@Param("id") Integer id);

    int update(@Param("id") Integer id, @Param("coverImages") String coverImages,
               @Param("name") String name, @Param("price") Float price,
               @Param("introduction") String introduction,
               @Param("updateTime") Long updateTime);

    @Select("select * from card limit #{pageSize} OFFSET #{offset}")
    List<Card> getPageList(@Param("offset") int offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from card")
    Long getTotal();
}
