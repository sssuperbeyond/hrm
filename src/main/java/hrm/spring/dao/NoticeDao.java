package hrm.spring.dao;
import static hrm.spring.util.HrmConst.NOTICETABLE;

import hrm.spring.dao.provider.NoticeProvider;
import hrm.spring.pojo.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/21
 * Time: 14:47
 */
public interface NoticeDao {
    // 动态查询当前页面的公告
    @SelectProvider(type = NoticeProvider.class,method="selectWithParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="USER_ID",property="user",
                    one=@One(select="hrm.spring.dao.UserDao.queryById",
                            fetchType= FetchType.EAGER))
    })
    List<Notice> selectWithParam(Map<String, Object> params);

    //动态查询公告数量
    @SelectProvider(type=NoticeProvider.class,method="count")
    Integer count(Map<String, Object> params);

    //根据id查询公告
    @Select("select * from "+NOTICETABLE+" where ID = #{id}")
    Notice selectById(int id);

    // 根据id删除公告
    @Delete(" delete from "+NOTICETABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入公告
    @SelectProvider(type=NoticeProvider.class,method="insertNotice")
    void insertNotice(Notice notice);

    // 动态修改公告
    @SelectProvider(type=NoticeProvider.class,method="updateNotice")
    void updateNotice(Notice notice);
}
