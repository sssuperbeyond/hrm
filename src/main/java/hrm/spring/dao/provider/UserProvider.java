package hrm.spring.dao.provider;

import hrm.spring.pojo.User;
import hrm.spring.util.pageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static hrm.spring.util.HrmConst.USERTABLE;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 17:29
 */
public class UserProvider {
    //动态更新数据
    public String updateUser(final User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if(user.getUsername() != null && !user.getUsername().equals("")){
                    SET("username = #{username}");
                }
                if(user.getLoginname() != null && !user.getLoginname().equals("")){
                    SET("loginname = #{loginname}");
                }
                if(user.getPassword() != null && !user.getPassword().equals("")){
                    SET("password = #{password}");
                }
                if(user.getCreateDate() != null){
                    SET("createdate = #{createdate}");
                }
                WHERE("ID = #{id}");
            }
        }.toString();
    }

    //动态查询
    public String queryWithParam(final Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
                if(params.get("user") != null){
                    User user = (User) params.get("user");
                    if(user.getUsername() != null && !user.getUsername().equals("")){
                        WHERE("username like concat('%', #{user.username}, '%')");
                    }
                    if(user.getStatus() != null && !user.getStatus().equals("")){
                        WHERE("STATUS like concat('%', #{user.status}, '%')");
                    }
                }
            }
        }.toString();
        if(params.get("pagemodel") != null){
            pageModel pagemodel = (pageModel) params.get("pagemodel");
//            sql += " limit #{pagemodel.firstLimitParam}, #{pagemodel.pageSize}";
            Integer one = pagemodel.getFirstLimitParam();
            Integer two = pagemodel.getPageSize();
            sql += " limit " + one + ", " + two + "";
        }
        return sql;
    }

    //动态查询总数量
    public String queryCount(final Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if(params.get("user") != null){
                    User user = (User) params.get("user");
                    if(user.getUsername() != null && !user.getUsername().equals("")){
                        WHERE("username like concat('%', #{user.username}, '%')");
                    }
                    if(user.getStatus() != null && !user.getStatus().equals("")){
                        WHERE("status like concat('%', #{user.status}, '%')");
                    }
                }
            }
        }.toString();
        return sql;
    }

    //动态插入
    public String insertUser(final User user){
        String sql = new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if(user.getUsername() != null && !user.getUsername().equals("")){
                    VALUES("username", "#{username}");
                }
                if(user.getStatus() != null && !user.getStatus().equals("")){
                    VALUES("status", "#{status}");
                }
                if(user.getLoginname() != null && !user.getLoginname().equals("")){
                    VALUES("loginname", "#{loginname}");
                }
                if(user.getPassword() != null && !user.getPassword().equals("")){
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
        return sql;
    }
}
