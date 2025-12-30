package red.mlz.mapper;

import org.apache.ibatis.annotations.*;
import red.mlz.entity.Card;

import java.util.List;

@Mapper
public interface CardMapper {

    @Select("SELECT id,cover_images ,name,price FROM card")
    List<Card> getList();

    @Select("SELECT * FROM card WHERE id=#{id}")
    Card getInfo(@Param("id") Integer id);

    int insert(@Param("card") Card card);

    @Update("update card SET is_deleted=1 WHERE id=#{id}")
    int delete(@Param("id") Integer id);

    int update(@Param("id") Integer id, @Param("coverImages") String coverImages,
               @Param("name") String name, @Param("price") Float price,
               @Param("introduction") String introduction,
               @Param("updateTime") Long updateTime);

    @Select("SELECT * FROM card LIMIT #{pageSize} OFFSET #{offset}")
    List<Card> getPageList(@Param("offset") int offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM card")
    Long getTotal();

    @Select("SELECT cover_images,name,price,introduction,FROM_UNIXTIME(create_time) AS createTime," +
            "FROM_UNIXTIME(update_time) AS updateTime FROM card;")
    Card getAdminCard(Integer id);
}
