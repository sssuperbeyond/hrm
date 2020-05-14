package hrm.spring.dao;

import hrm.spring.dao.provider.DeptProvider;
import hrm.spring.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import static hrm.spring.util.HrmConst.DEPTTABLE;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/20
 * Time: 16:57
 */
public interface DeptDao {
    //当前页面动态查询
    @SelectProvider(type = DeptProvider.class, method = "queryByPage")
    public List<Dept> queryByPage(Map<String, Object> params);

    //动态查询数量
    @SelectProvider(type = DeptProvider.class, method = "queryCount")
    public Integer queryCount(Map<String, Object> params);

    //查询所有
    @Select("select * from " + DEPTTABLE)
    public List<Dept> selectAllDept();

    //根据id查询
    @Select("select * from " + DEPTTABLE + " where ID = #{id}")
    public Dept selectById(Integer id);

    //根据id删除部门
    @Delete(" delete from " + DEPTTABLE + " where ID = #{id} ")
    public void deleteById(Integer id);

    //动态插入部门
    @SelectProvider(type = DeptProvider.class, method = "insertDept")
    public void insertDept(Dept dept);

    //动态修改部门信息
    @SelectProvider(type = DeptProvider.class, method = "updateDept")
    public void updateDept(Dept dept);
}
