package hrm.spring.pojo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/19
 * Time: 15:38
 */
//部门pojo
public class Dept implements Serializable {
    private Integer id;		// id
    private String name;	// 部门名称
    private String remark;	// 详细描述
    // 无参数构造器
    public Dept() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
