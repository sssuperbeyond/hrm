package hrm.spring.service;

import hrm.spring.pojo.*;
import hrm.spring.util.pageModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/23
 * Time: 14:23
 */
public interface HrmService {
    /**
     * 处理User相关DAO
     * */
    //登录查询
    User login(String loginname, String password);

    //根据id查询用户
    User findUserById(Integer id);

    //查询当前页面员工
    List<User> findUser(User user, pageModel pagemodel);

    //根据id删除用户
    void removeUserById(Integer id);

    //修改用户
    void modifyUser(User user);

    //添加用户
    void addUser(User user);

    /**
     * 处理Employee相关DAO
     * */
    //查询当前页面的员工
    List<Employee> findEmployee(Employee employee, pageModel pagemodel);

    //根据id删除员工
    void removeEmployeeById(Integer id);

    //根据id查询员工
    Employee findEmployeeById(Integer id);

    //添加员工
    void addEmployee(Employee employee);

    //修改员工
    void modifyEmployee(Employee employee);

    /**
     * 处理Dept相关DAO
     * */
    //查询当前页面的部门
    List<Dept> findDept(Dept dept, pageModel pagemodel);

    //获得所有部门的List集合
    List<Dept> findAllDept();

    //根据id删除部门
    public void removeDeptById(Integer id);

    //添加部门
    void addDept(Dept dept);

    //根据id查询部门
    Dept findDeptById(Integer id);

    //修改部门
    void modifyDept(Dept dept);

    /**
     * 处理Job相关DAO
     * */
    //获得所有职位的List集合
    List<Job> findAllJob();

    //查询当前页面的职位
    List<Job> findJob(Job job, pageModel pagemodel);

    //根据id删除职位
    public void removeJobById(Integer id);

    //添加职位
    void addJob(Job job);

    //根据id查询职位
    Job findJobById(Integer id);

    //修改职位
    void modifyJob(Job job);

    /**
     * 处理Notice相关DAO
     * */
    //查询当前页面的公告
    List<Notice> findNotice(Notice notice, pageModel pagemodel);

    //根据id查询公告
    Notice findNoticeById(Integer id);

    //根据id删除公告
    public void removeNoticeById(Integer id);

    //添加公告
    void addNotice(Notice notice);

    //修改公告
    void modifyNotice(Notice notice);

}
