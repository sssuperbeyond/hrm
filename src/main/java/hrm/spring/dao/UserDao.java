package hrm.spring.dao;

import hrm.spring.dao.provider.UserProvider;
import hrm.spring.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static hrm.spring.util.HrmConst.USERTABLE;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 16:40
 */
public interface UserDao {
    //根据登录名和密码查询用户
    @Select("select * from " +USERTABLE+" where loginname = #{loginname} and password = #{password}")
    public User queryLoginAndPass(@Param("loginname") String loginname, @Param("password") String password);

    //根据id查询用户
    @Select("select * from "+USERTABLE+" where ID = #{id}")
    public User queryById(@Param("id") Integer id);

    //根据id删除用户
    @Delete("delete from "+USERTABLE+" where ID = #{id}")
    public void deleteById(@Param("id") Integer id);

    //动态修改用户
    @SelectProvider(type = UserProvider.class, method = "updateUser")
    public void updateUser(User user);

    //动态查询
    @SelectProvider(type = UserProvider.class, method = "queryWithParam")
    public List<User> queryWithParam(Map<String, Object> params);

    //动态查询总数量
    @SelectProvider(type = UserProvider.class, method = "queryCount")
    public Integer queryCount(Map<String, Object> params);

    //动态插入
    @SelectProvider(type = UserProvider.class, method = "insertUser")
    public void insertUser(User user);
}
