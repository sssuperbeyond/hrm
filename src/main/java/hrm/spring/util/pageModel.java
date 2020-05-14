package hrm.spring.util;

/**
 * Created by IntelliJ IDEA.
 * User: 球王老李
 * Date: 2020/4/20
 * Time: 15:18
 */

/**
 *
 * 此类定义page的相关属性
 *
 * */
public class pageModel {
    //当前页
    private Integer pageIndex = 1;
    //每页多少条数据
    private Integer pageSize = HrmConst.PAGE_DEFAULT_SIZE;
    //分页总数据条数
    private Integer recordCount;
    //总页数
    private Integer totalSize;

    public int getRecordCount() {
        this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
        return recordCount;
    }
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageIndex() {
        //当前页是否小于最小页面1，做相应的处理
        this.pageIndex = this.pageIndex < 1 ? 1:this.pageIndex;
        //当前页是否大于总页面，做相应的处理
        this.pageIndex = this.pageIndex > this.getTotalSize() ? getTotalSize():this.pageIndex;
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize(){
        return this.pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    //此方法返回当前数据位于第几条，以方便数据库的查询
    public Integer getFirstLimitParam(){
        return (this.getPageIndex() - 1) * this.pageSize;
    }
}
