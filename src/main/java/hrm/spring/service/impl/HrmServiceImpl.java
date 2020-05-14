package hrm.spring.service.impl;

import hrm.spring.dao.*;
import hrm.spring.pojo.*;
import hrm.spring.service.HrmService;
import hrm.spring.util.pageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/23
 * Time: 14:24
 */
@Service("hrmservice")
public class HrmServiceImpl implements HrmService {
    //自动注入DAO mapper对象
    @Autowired
    UserDao userDao;

    @Autowired
    DeptDao deptDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    JobDao jobDao;

    @Autowired
    NoticeDao noticeDao;

    //用户服务接口实现
    public User login(String loginname, String password) {
        return userDao.queryLoginAndPass(loginname, password);
    }

    public User findUserById(Integer id) {
        return null;
    }

    public List<User> findUser(User user, pageModel pagemodel) {
        Map<String,Object> params = new TreeMap<String, Object>();
        params.put("user", user);
        int recordCount = userDao.queryCount(params);
        pagemodel.setRecordCount(recordCount);
        pagemodel.setTotalSize((recordCount-1) / pagemodel.getPageSize() + 1);
        if (recordCount > 0) {
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pagemodel", pagemodel);
        }
        List<User> users = userDao.queryWithParam(params);
        return users;
    }

    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }

    public void modifyUser(User user) {
        userDao.updateUser(user);
    }

    public void addUser(User user) {
        userDao.insertUser(user);
    }

    public List<Employee> findEmployee(Employee employee, pageModel pagemodel) {
        Map<String,Object> params = new HashMap<>();
        params.put("employee", employee);

        int recordCount = employeeDao.queryCount(params);
        pagemodel.setRecordCount(recordCount);
        pagemodel.setTotalSize((recordCount-1) / pagemodel.getPageSize() + 1);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pagemodel", pagemodel);
        }
        List<Employee> employees = employeeDao.selectWithParam(params);
        return employees;
    }

    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }

    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }

    public void addEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    public void modifyEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public List<Dept> findDept(Dept dept, pageModel pagemodel) {
        Map<String,Object> params = new HashMap<>();
        params.put("dept", dept);
        int recordCount = deptDao.queryCount(params);
        pagemodel.setRecordCount(recordCount);
        pagemodel.setTotalSize((recordCount-1) / pagemodel.getPageSize() + 1);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pagemodel", pagemodel);
        }

        List<Dept> depts = deptDao.queryByPage(params);

        return depts;
    }

    public List<Dept> findAllDept() {
        return null;
    }

    public void removeDeptById(Integer id) {
        deptDao.deleteById(id);
    }

    public void addDept(Dept dept) {
        deptDao.insertDept(dept);
    }

    public Dept findDeptById(Integer id) {
        return null;
    }

    public void modifyDept(Dept dept) {

    }

    public List<Job> findAllJob() {
        return null;
    }

    public List<Job> findJob(Job job, pageModel pagemodel) {
        Map<String,Object> params = new HashMap<>();
        params.put("job", job);
        int recordCount = jobDao.jobCount(params);
        pagemodel.setRecordCount(recordCount);
        pagemodel.setTotalSize((recordCount-1) / pagemodel.getPageSize() + 1);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pagemodel", pagemodel);
        }
        List<Job> jobs = jobDao.selectByPage(params);

        return jobs;
    }

    public void removeJobById(Integer id) {
        jobDao.deleteById(id);
    }

    public void addJob(Job job) {
        jobDao.insertJob(job);
    }

    public Job findJobById(Integer id) {
        return jobDao.selectById(id);
    }

    public void modifyJob(Job job) {
        jobDao.updateJob(job);
    }

    public List<Notice> findNotice(Notice notice, pageModel pagemodel) {
        Map<String,Object> params = new HashMap<>();
        params.put("notice", notice);
        int recordCount = noticeDao.count(params);
        pagemodel.setRecordCount(recordCount);
        pagemodel.setTotalSize((recordCount-1) / pagemodel.getPageSize() + 1);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pagemodel", pagemodel);
        }

        List<Notice> notices = noticeDao.selectWithParam(params);

        return notices;
    }

    public Notice findNoticeById(Integer id) {
        return noticeDao.selectById(id);
    }

    public void removeNoticeById(Integer id) {
        noticeDao.deleteById(id);
    }

    public void addNotice(Notice notice) {
        noticeDao.insertNotice(notice);
    }

    public void modifyNotice(Notice notice) {
        noticeDao.updateNotice(notice);
    }

}
