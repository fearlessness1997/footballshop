package red.mlz.mapper;

import org.apache.ibatis.annotations.*;
import red.mlz.entity.Card;
import red.mlz.entity.DTO.CardDTO;

import java.util.List;

@Mapper
public interface CardMapper {

    @Select("select id,cover_images ,name,price from card")
    List<Card> getList();

    @Select("select * from card where id=#{id}")
    Card getInfo(@Param("id") Integer id);

    @Insert("insert into card (cover_images,name,price,introduction,create_time,update_time) values " +
            "(#{cardDTO.coverImages},#{cardDTO.name},#{cardDTO.price},#{cardDTO.introduction},#{cardDTO.createTime},#{cardDTO.updateTime})")
    int insert(@Param("cardDTO") CardDTO cardDTO);

    @Update("update card set is_deleted=1 where id=#{id}")
    int delete(@Param("id") Integer id);

    @Update("update card set cover_images=#{coverImages},name=#{name}," +
            "price=#{price},introduction=#{introduction},update_time=#{updateTime} where id=#{id} ")
    int update(@Param("id") Integer id, @Param("coverImages") String coverImages,
               @Param("name") String name, @Param("price") Float price,
               @Param("introduction") String introduction,
               @Param("updateTime") Long updateTime);
}
