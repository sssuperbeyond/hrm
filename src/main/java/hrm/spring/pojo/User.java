package hrm.spring.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 15:35
 */
//用户pojo
public class User implements Serializable {
    private Integer id;			// id
    private String username;	// 用户名
    private String loginname;	// 登录名
    private String password;	// 密码
    private String status;		// 状态
    private Date createDate;	// 建档日期

    public User(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
