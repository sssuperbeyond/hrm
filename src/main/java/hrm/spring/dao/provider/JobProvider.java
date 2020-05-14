package hrm.spring.dao.provider;

import hrm.spring.pojo.Job;
import hrm.spring.util.pageModel;
import org.apache.ibatis.jdbc.SQL;
import static hrm.spring.util.HrmConst.JOBTABLE;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/21
 * Time: 14:46
 */
public class JobProvider {
    // 动态查询当前页面的职位
    public String selectWhitParam(final Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(JOBTABLE);
                if(params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if(job.getName() != null && !job.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{job.name},'%') ");
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

    //动态查询职位的数量
    public String jobCount(final Map<String, Object> params){
        String sql = new SQL(){
            {
                SELECT("COUNT(*)");
                FROM(JOBTABLE);
                if(params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if(job.getName() != null && !job.getName().equals("")){
                        WHERE("NAME LIKE CONCAT('%', #{job.name}, '%')");
                    }
                }
            }
        }.toString();
        return sql;
    }

    // 动态插入职位
    public String insertJob(final Job job){

        return new SQL(){
            {
                INSERT_INTO(JOBTABLE);
                if(job.getName() != null && !job.getName().equals("")){
                    VALUES("name", "#{name}");
                }
                if(job.getRemark() != null && !job.getRemark().equals("")){
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    // 动态修改职位
    public String updateJob(final Job job){

        return new SQL(){
            {
                UPDATE(JOBTABLE);
                if(job.getName() != null && !job.getName().equals("")){
                    SET(" name = #{name} ");
                }
                if(job.getRemark() != null && !job.getRemark().equals("")){
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
