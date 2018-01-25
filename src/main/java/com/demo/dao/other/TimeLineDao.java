package com.demo.dao.other;

import com.demo.model.other.TimeLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzhi
 * @date 2018/1/25 0025
 * @description
 */
public interface TimeLineDao {
    public void insert(TimeLine timeLine);
    public void update(TimeLine timeLine);
    public void delete(String id);
    public TimeLine get(@Param("id") String id);
    public List<TimeLine> select(TimeLine timeLine);
}
