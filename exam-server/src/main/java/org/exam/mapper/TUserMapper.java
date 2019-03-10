package org.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.exam.bean.entity.TRole;
import org.exam.bean.entity.TUser;
import org.exam.bean.entity.TUserExample;

public interface TUserMapper {
    /**
     * 登录
     * @param username
     * @return
     */
    TUser loadUserByUsername(String username);
    List<TRole> getRolesById(Long id);
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    @Delete({
        "delete from t_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    @Select({
        "select",
        "id, user_id, name, phone, tele_phone, address, is_del, username, password, userface, ",
        "remark, create_time, update_time, creater, updater, version",
        "from t_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("org.exam.mapper.TUserMapper.BaseResultMap")
    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    @Update({
        "update t_user",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=CHAR},",
          "tele_phone = #{telePhone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=BIT},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "userface = #{userface,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "creater = #{creater,jdbcType=INTEGER},",
          "updater = #{updater,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TUser record);
}