package hrm.spring.dao;

import hrm.spring.dao.provider.JobProvider;
import hrm.spring.pojo.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static hrm.spring.util.HrmConst.JOBTABLE;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/21
 * Time: 14:46
 */
public interface JobDao {
    //根据id查询职位
    @Select("select * from "+JOBTABLE+" where ID = #{id}")
    Job selectById(int id);

    //查询所有职位
    @Select("select * from "+JOBTABLE+" ")
    List<Job> selectAllJob();

    //动态查询当前页面的职位
    @SelectProvider(type= JobProvider.class,method="selectWhitParam")
    List<Job> selectByPage(Map<String, Object> params);

    //动态查询职位的数量
    @SelectProvider(type=JobProvider.class,method="jobCount")
    Integer jobCount(Map<String, Object> params);

    // 动态插入职位
    @SelectProvider(type=JobProvider.class,method="insertJob")
    void insertJob(Job job);

    // 动态修改职位
    @SelectProvider(type=JobProvider.class,method="updateJob")
    void updateJob(Job job);

    // 根据id删除部门
    @Delete(" delete from "+JOBTABLE+" where id = #{id} ")
    void deleteById(Integer id);
}
