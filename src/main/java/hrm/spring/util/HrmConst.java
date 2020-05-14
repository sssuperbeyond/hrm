package hrm.spring.util;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 16:22
 */

    /**
     *
     * 此类定义一些需要使用的公共常量
     *
     * */
public class HrmConst {
    // 数据库表常量
    public static final String USERTABLE = "user_inf";
    public static final String DEPTTABLE = "dept_inf";
    public static final String JOBTABLE = "job_inf";
    public static final String EMPLOYEETABLE = "employee_inf";
    public static final String NOTICETABLE = "notice_inf";
    public static final String DOCUMENTTABLE = "document_inf";

    // 登录
    public static final String LOGIN = "loginForm";
    // 用户的session对象
    public static final String USER_SESSION = "user_session";

    // 默认每页4条数据
    public static int PAGE_DEFAULT_SIZE = 4;
}
