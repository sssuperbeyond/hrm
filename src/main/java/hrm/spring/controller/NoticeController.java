package hrm.spring.controller;

import hrm.spring.pojo.Notice;
import hrm.spring.pojo.User;
import hrm.spring.util.HrmConst;
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
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/26
 * Time: 18:15
 */
@Controller
public class NoticeController {
    @Autowired
    @Qualifier("hrmservice")
    HrmService hrmService;

    @RequestMapping(value="/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex,
                               @ModelAttribute Notice notice){
        pageModel pagemodel = new pageModel();
        if(pageIndex != null){
            pagemodel.setPageIndex(pageIndex);
        }
        /** 查询用户信息     */
        List<Notice> notices = hrmService.findNotice(notice, pagemodel);
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel", pagemodel);
        return "notice/notice";

    }

    @RequestMapping(value="/notice/previewNotice")
    public String previewNotice(
            Integer id,Model model){

        Notice notice = hrmService.findNoticeById(id);

        model.addAttribute("notice", notice);
        // 返回
        return "notice/previewNotice";
    }

    @RequestMapping(value="/notice/removeNotice")
    public ModelAndView removeNotice(String ids, ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除公告
            hrmService.removeNoticeById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/notice/selectNotice");
        // 返回ModelAndView
        return mv;
    }

    @RequestMapping(value="/notice/addNotice")
    public ModelAndView addNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session){
        if(flag.equals("1")){
            mv.setViewName("notice/showAddNotice");
        }else{
            User user = (User) session.getAttribute(HrmConst.USER_SESSION);
            notice.setUser(user);
            hrmService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        // 返回
        return mv;
    }

    @RequestMapping(value="/notice/updateNotice")
    public ModelAndView updateNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session,
            HttpServletRequest request){
        if(flag.equals("1")){
            request.getServletContext().setAttribute("id", notice.getId());
            Notice target = hrmService.findNoticeById(notice.getId());
            mv.addObject("notice",target);
            mv.setViewName("notice/showUpdateNotice");
        }else{
            hrmService.modifyNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        // 返回
        return mv;
    }
}
