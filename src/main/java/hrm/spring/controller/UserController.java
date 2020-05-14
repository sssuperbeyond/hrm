package hrm.spring.controller;

import hrm.spring.pojo.User;
import hrm.spring.service.HrmService;
import hrm.spring.util.HrmConst;
import hrm.spring.util.pageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.support.nativejdbc.WebLogicNativeJdbcExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/23
 * Time: 14:59
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier("hrmservice")
    HrmService hrmService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv){
        User user =  hrmService.login(loginname, password);
        if(user == null){
            mv.addObject("message", "登录名或密码错误!请重新输入");
            mv.setViewName("forward:/loginForm");
        }else{
            session.setAttribute(HrmConst.USER_SESSION, user);
            mv.setViewName("redirect:/main");
        }
        return mv;
    }

    @RequestMapping("/user/selectUser")
    public String selectUser(@ModelAttribute User user, Integer pageIndex, Model model){
        pageModel pageModel = new pageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
//        /** 查询用户信息     */
        List<User> users = hrmService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
    }

    //添加user方法
    @RequestMapping(value="/user/addUser")
    public ModelAndView addUser(
            @RequestParam("flag") String flag,
            @ModelAttribute User user,
            ModelAndView mv){
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("user/showAddUser");
        }else{
            // 执行添加操作
            hrmService.addUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        // 返回
        return mv;
    }

    //删除用户
    @RequestMapping(value = "/user/removeUser")
    public String removeUser(@RequestParam("ids") String ids){
        String[] idsArray = ids.split(",");
        for (String str: idsArray) {
            hrmService.removeUserById(Integer.parseInt(str));
        }
        return "redirect:/user/selectUser";
    }

    //修改用户数据
    @RequestMapping(value="/user/updateUser")
    public ModelAndView updateUser(
            String flag,
            @ModelAttribute User user,
            ModelAndView mv,
            HttpServletRequest request){
        if(flag.equals("1")){
            request.getServletContext().setAttribute("id", user.getId());
            // 根据id查询用户
            User target = hrmService.findUserById(user.getId());
            // 设置Model数据
            mv.addObject("user", target);
            // 返回修改员工页面
            mv.setViewName("user/showUpdateUser");
        }else{
            int id = (int) request.getServletContext().getAttribute("id");
            user.setId(id);
            // 执行修改操作
            hrmService.modifyUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        // 返回
        return mv;
    }
}