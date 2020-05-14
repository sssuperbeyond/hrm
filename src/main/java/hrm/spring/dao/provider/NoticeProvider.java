package hrm.spring.dao.provider;
import static hrm.spring.util.HrmConst.NOTICETABLE;

import hrm.spring.pojo.Notice;
import hrm.spring.util.pageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/21
 * Time: 14:47
 */
public class NoticeProvider {
    // 动态查询当前页面的公告
    public String selectWithParam(final Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(NOTICETABLE);
                if(params.get("notice") != null){
                    Notice notice = (Notice)params.get("notice");
                    if(notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if(notice.getContent() != null && !notice.getContent().equals("")){
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                }
            }
        }.toString();

        if(params.get("pagemodel") != null){
            pageModel pagemodel = (pageModel) params.get("pagemodel");
            sql += " limit #{pagemodel.firstLimitParam} , #{pagemodel.pageSize}  ";
        }

        return sql;
    }

    // 动态公告的数量
    public String count(final Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(NOTICETABLE);
                if(params.get("notice") != null){
                    Notice notice = (Notice)params.get("notice");
                    if(notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if(notice.getContent() != null && !notice.getContent().equals("")){
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入公告
    public String insertNotice(final Notice notice){

        return new SQL(){
            {
                INSERT_INTO(NOTICETABLE);
                if(notice.getTitle() != null && !notice.getTitle().equals("")){
                    VALUES("title", "#{title}");
                }
                if(notice.getContent() != null && !notice.getContent().equals("")){
                    VALUES("content", "#{content}");
                }
                if(notice.getUser() != null && notice.getUser().getId() != null){
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    // 动态修改公告
    public String updateNotice(final Notice notice){

        return new SQL(){
            {
                UPDATE(NOTICETABLE);
                if(notice.getTitle() != null && !notice.getTitle().equals("")){
                    SET(" title = #{title} ");
                }
                if(notice.getContent() != null && !notice.getContent().equals("")){
                    SET(" content = #{content} ");
                }
                if(notice.getUser() != null && notice.getUser().getId() != null){
                    SET(" user_id = #{user.id} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
