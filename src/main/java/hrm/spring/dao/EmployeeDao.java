package hrm.spring.dao;

import hrm.spring.dao.provider.EmployeeProvider;
import hrm.spring.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import static hrm.spring.util.HrmConst.EMPLOYEETABLE;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/20
 * Time: 19:29
 */
public interface EmployeeDao {
    //动态查询数量
    @SelectProvider(type = EmployeeProvider.class, method = "queryCount")
    Integer queryCount(Map<String, Object> params);

    //动态查询当前页面员工
    @SelectProvider(type = EmployeeProvider.class, method = "selectWithParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CARD_ID",property="cardId"),
            @Result(column="POST_CODE",property="postCode"),
            @Result(column="QQ_NUM",property="qqNum"),
            @Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="DEPT_ID",property="dept",
                    one=@One(select="hrm.spring.dao.DeptDao.selectById",
                            fetchType= FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one=@One(select="hrm.spring.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Employee> selectWithParam(Map<String, Object> params);

    // 动态插入员工
    @SelectProvider(type=EmployeeProvider.class,method="insertEmployee")
    void insertEmployee(Employee employee);

    // 根据id删除员工
    @Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 根据id查询员工
    @Select("select * from "+EMPLOYEETABLE+" where ID = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CARD_ID",property="cardId"),
            @Result(column="POST_CODE",property="postCode"),
            @Result(column="QQ_NUM",property="qqNum"),
            @Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="DEPT_ID",property="dept",
                    one=@One(select="hrm.spring.dao.DeptDao.selectById",
                            fetchType=FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one=@One(select="hrm.spring.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    Employee selectById(Integer id);

    // 动态修改员工
    @SelectProvider(type=EmployeeProvider.class,method="updateEmployee")
    void updateEmployee(Employee employee);
}
