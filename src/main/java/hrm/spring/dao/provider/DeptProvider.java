package hrm.spring.dao.provider;

import hrm.spring.pojo.Dept;
import hrm.spring.util.pageModel;
import org.apache.ibatis.jdbc.SQL;
import static hrm.spring.util.HrmConst.DEPTTABLE;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/20
 * Time: 16:58
 */
public class DeptProvider {
    //当前页面动态查询
    public String queryByPage(final Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();

        if(params.get("pagemodel") != null){
            pageModel pagemodel = (pageModel) params.get("pagemodel");
            sql += " limit #{pagemodel.firstLimitParam} , #{pagemodel.pageSize}  ";
        }
        return sql;
    }

    //动态查询总数
    public String queryCount(final Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    //动态插入部门
    public String insertDept(final Dept dept){
        String sql = new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if(dept.getName() != null && !dept.getName().equals("")){
                    VALUES("NAME", "#{name}");
                }
                if(dept.getRemark() != null && dept.getRemark().equals("")){
                    VALUES("REMARK", "#{remark}");
                }
            }
        }.toString();
        return sql;
    }

    //动态修改部门信息
    public String updateDept(final Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if(dept.getName() != null && !dept.getName().equals("")){
                    SET(" name = #{name} ");
                }
                if(dept.getRemark() != null && !dept.getRemark().equals("")){
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
