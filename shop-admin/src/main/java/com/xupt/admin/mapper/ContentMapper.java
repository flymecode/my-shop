package com.xupt.admin.mapper;

import com.xupt.domain.Content;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author maxu
 * @date 2019/6/1
 */
@Mapper
public interface ContentMapper {


    @Select("select * from tb_content")
    List<Content> searchContents();

    @Insert("insert into " +
            "tb_content" +
            "(category_id,title,sub_title,title_desc,url,pic,pic2,content,created,updated)" +
            "values" +
            "(#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    int saveContent(Content content);

    @Delete("delete from tb_content where id = #{id}")
    int deleteContent(String id);

    @Select("select a.id,a.category_id As categoryId,a.title, a.sub_title As subTitle," +
            "a.title_desc As titleDesc,a.url,a.pic,a.pic2,a.content,a.created,a.updated," +
            "b.name As 'contentCategory.name' from tb_content as a left " +
            "join tb_content_category as b on a.category_id = b.id where a.id = #{id}")
    Content searchContent(Integer id);
}
