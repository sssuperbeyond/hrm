package hrm.spring.controller;

import hrm.spring.pojo.Job;
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
 * Time: 18:15
 */
@Controller
public class JobController {
    @Autowired
    @Qualifier("hrmservice")
    HrmService hrmService;

    @RequestMapping(value="/job/selectJob")
    public String selectJob(Model model, Integer pageIndex,
                            @ModelAttribute Job job){
        pageModel pagemodel = new pageModel();
        if(pageIndex != null){
            pagemodel.setPageIndex(pageIndex);
        }
        /** 查询用户信息     */
        List<Job> jobs = hrmService.findJob(job, pagemodel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pagemodel", pagemodel);
        return "job/job";
    }

    @RequestMapping(value="/job/removeJob")
    public ModelAndView removeJob(String ids, ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除职位
            hrmService.removeJobById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/job/selectJob");
        // 返回ModelAndView
        return mv;
    }

    @RequestMapping(value="/job/addJob")
    public ModelAndView addJob(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv){
        if(flag.equals("1")){
            // 设置跳转到添加页面
            mv.setViewName("job/showAddJob");
        }else{
            // 执行添加操作
            hrmService.addJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:job/selectJob");
        }
        // 返回
        return mv;
    }

    @RequestMapping(value="/job/updateJob")
    public ModelAndView updateDpet(
            String flag,
            @ModelAttribute Job job,
            ModelAndView mv,
            HttpServletRequest request){
        if(flag.equals("1")){
            request.getServletContext().setAttribute("id", job.getId());
            // 根据id查询部门
            Job target = hrmService.findJobById(job.getId());
            // 设置Model数据
            mv.addObject("job", target);
            // 设置跳转到修改页面
            mv.setViewName("job/showUpdateJob");
        }else{
            int id = (int) request.getServletContext().getAttribute("id");
            job.setId(id);
            // 执行修改操作
            hrmService.modifyJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        // 返回
        return mv;
    }

}
