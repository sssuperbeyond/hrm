package hrm.spring.controller;

import hrm.spring.pojo.Dept;
import hrm.spring.service.HrmService;
import hrm.spring.util.pageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/26
 * Time: 18:14
 */
@Controller
public class DeptController {
    @Autowired
    @Qualifier("hrmservice")
    HrmService hrmService;

    //查询
    @RequestMapping(value="/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex,
                             @ModelAttribute Dept dept){
        pageModel pageModel = new pageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        /** 查询用户信息     */
        List<Dept> depts = hrmService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);
        return "dept/dept";
    }

    //添加
    @RequestMapping(value="/dept/addDept")
    public ModelAndView addDept(
            String flag,
            @ModelAttribute Dept dept,
            ModelAndView mv){
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("dept/showAddDept");
        }else{
            // 执行添加操作
            hrmService.addDept(dept);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/dept/selectDept");
        }
        // 返回
        return mv;
    }

    //删除
    @RequestMapping(value="/dept/removeDept")
    public ModelAndView removeDept(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除部门
            hrmService.removeDeptById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/dept/selectDept");
        // 返回ModelAndView
        return mv;
    }

    //修改
    @RequestMapping(value="/dept/updateDept")
    public ModelAndView updateDpet(
            String flag,
            @ModelAttribute Dept dept,
            ModelAndView mv,
            HttpServletRequest request){
        if(flag.equals("1")){
            request.getServletContext().setAttribute("id", dept.getId());
            // 根据id查询部门
            Dept target = hrmService.findDeptById(dept.getId());
            // 设置Model数据
            mv.addObject("dept", target);
            // 设置跳转到修改页面
            mv.setViewName("dept/showUpdateDept");
        }else{
            int id = (int) request.getServletContext().getAttribute("id");
            dept.setId(id);
            // 执行修改操作
            hrmService.modifyDept(dept);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/dept/selectDept");
        }
        // 返回
        return mv;
    }
}
