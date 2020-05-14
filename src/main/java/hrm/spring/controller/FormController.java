package hrm.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/22
 * Time: 20:13
 */
@Controller
public class FormController {
    @RequestMapping(value="/{formName}")
    public String loginForm(@PathVariable String formName){
        // 动态跳转页面
        return formName;
    }
}
