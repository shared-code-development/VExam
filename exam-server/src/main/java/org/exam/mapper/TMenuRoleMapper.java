package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TMenuRole;
import org.exam.bean.entity.TMenuRoleExample;

public interface TMenuRoleMapper {
    long countByExample(TMenuRoleExample example);

    int deleteByExample(TMenuRoleExample example);

    @Delete({
        "delete from t_menu_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_menu_role (menu_id, role_id)",
        "values (#{menuId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(TMenuRole record);

    int insertSelective(TMenuRole record);

    List<TMenuRole> selectByExample(TMenuRoleExample example);

    @Select({
        "select",
        "id, menu_id, role_id",
        "from t_menu_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("org.exam.mapper.TMenuRoleMapper.BaseResultMap")
    TMenuRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMenuRole record, @Param("example") TMenuRoleExample example);

    int updateByExample(@Param("record") TMenuRole record, @Param("example") TMenuRoleExample example);

    int updateByPrimaryKeySelective(TMenuRole record);

    @Update({
        "update t_menu_role",
        "set menu_id = #{menuId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TMenuRole record);
}