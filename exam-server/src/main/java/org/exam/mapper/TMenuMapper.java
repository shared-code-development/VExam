package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenuExample;

public interface TMenuMapper {
    long countByExample(TMenuExample example);

    int deleteByExample(TMenuExample example);

    @Delete({
        "delete from t_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    List<TMenu> selectByExample(TMenuExample example);

    @Select({
        "select",
        "id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, ",
        "is_del, create_time, update_time, creater, updater, version",
        "from t_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("org.exam.mapper.TMenuMapper.BaseResultMap")
    TMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByPrimaryKeySelective(TMenu record);

    @Update({
        "update t_menu",
        "set url = #{url,jdbcType=VARCHAR},",
          "path = #{path,jdbcType=VARCHAR},",
          "component = #{component,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "icon_cls = #{iconCls,jdbcType=VARCHAR},",
          "keep_alive = #{keepAlive,jdbcType=BIT},",
          "require_auth = #{requireAuth,jdbcType=BIT},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "is_del = #{is_del,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "creater = #{creater,jdbcType=INTEGER},",
          "updater = #{updater,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TMenu record);

    List<TMenu> getAllMenu();

    /**
     * 根据userId获取菜单权限
     * @return
     */
    List<TMenu> getMenusByUserId(String userId);

    List<TMenu> getMenusByParentId(Integer parentId);
}