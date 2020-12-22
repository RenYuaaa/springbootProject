package com.ren.springboot.dao;

import com.ren.springboot.entity.User;
import com.ren.springboot.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface UserDao {


    @Select("SELECT u.id AS userId, u.user_name, u.password, r.id AS roleId, r.role_name, p.id AS permissionId, p.permission_name " +
            "FROM `user` u LEFT JOIN user_role ur ON u.id = ur.user_id LEFT JOIN role r ON ur.role_id = r.id " +
            "LEFT JOIN role_permission rp ON r.id = rp.role_id LEFT JOIN permission p ON rp.permission_id = p.id " +
            "WHERE u.user_name = #{userName} ")
    List<UserVo> selectByUserName(@Param("userName") String userName);

    @SelectProvider(type = UserProvider.class, method = "selectUser")
    List<User> selectUser(List<Integer> userIds);

    class UserProvider{
        public String selectUser(List<Integer> userIds) {
            StringBuilder sb = new StringBuilder();
            sb.append("select * from user where id in (");
            for (Integer userId : userIds) {
                sb.append(userId);
                sb.append(",");
            }
            sb.append(sb.length() - 1);
            sb.append(")");
            return sb.toString();
        }
    }
}
