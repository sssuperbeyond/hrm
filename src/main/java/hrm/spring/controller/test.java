package hrm.spring.controller;

import hrm.spring.dao.EmployeeDao;
import hrm.spring.dao.UserDao;
import hrm.spring.pojo.Dept;
import hrm.spring.pojo.Employee;
import hrm.spring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 17:55
 */
@Controller
public class test {
    @Autowired
    private UserDao userdao;
    @Autowired
    private EmployeeDao emdao;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request){
//        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//        UserDao userdao = context.getBean(UserDao.class);
        User user = userdao.queryById(1);
        System.out.println("username = " + user.getUsername());
        System.out.println("loginname = " + user.getLoginname());
        System.out.println("password = " + user.getPassword());

        Map<String, Object> map = new TreeMap<String, Object>();
        List<User> list;
        User user1 = new User();
        user1.setUsername("超级管理员");
        map.put("user", user1);
        list = userdao.queryWithParam(map);
        System.out.println(list.get(0).getLoginname());

        Map<String, Object> map1 = new TreeMap<String, Object>();
        User user2 = new User();
        user2.setUsername("超级");
        map1.put("user", user2);
        System.out.println(userdao.queryCount(map1));

        Employee em = new Employee();
        Map<String, Object> map3 = new TreeMap<String, Object>();
        Dept dept = new Dept();
        dept.setId(1);
        em.setDept(dept);
        map3.put("employee", em);
        int num = emdao.queryCount(map3);
        System.out.println("num = " + num);
        return "success";
    }
}
