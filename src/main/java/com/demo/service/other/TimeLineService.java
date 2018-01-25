package com.demo.service.other;

import com.demo.dao.other.TimeLineDao;
import com.demo.model.other.TimeLine;
import com.demo.utils.common.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenzhi
 * @date 2018/1/25 0025
 * @description
 */
@Service
public class TimeLineService {

    @Autowired
    private TimeLineDao timeLineDao;

    /**
     * 分页查询时间线列表
     * @param page
     * @param limit
     * @param timeLine
     * @return
     */
    public PageInfo<TimeLine> findPage(Integer page, Integer limit, TimeLine timeLine){
        if(page > 0 && limit > 0){
            PageHelper.startPage(page, limit);
        }
        List<TimeLine> timeLineList = timeLineDao.select(timeLine);
        PageInfo<TimeLine> pageInfo = new PageInfo(timeLineList);
        return pageInfo;
    }

    /**
     * 新增时间线
     * @param timeLine
     */
    @Transactional
    public void insert(TimeLine timeLine){
        timeLine.setId(UUIDGenerator.uuid());
        timeLineDao.insert(timeLine);
    }

    /**
     * 修改时间线信息
     * @param timeLine
     */
    @Transactional
    public void update(TimeLine timeLine){
        timeLineDao.update(timeLine);
    }

    /**
     * 根据ID获取时间线信息
     * @param id
     * @return
     */
    public TimeLine getById(String id){
        TimeLine timeLine = timeLineDao.get(id);
        return timeLine;
    }

    @Transactional
    public void delete(String id){
        timeLineDao.delete(id);
    }

    @Transactional
    public void batchDel(String[] ids){
        for (String id : ids) {
            timeLineDao.delete(id);
        }
    }

    public List<TimeLine> selectList(){
        List<TimeLine> list = timeLineDao.select(new TimeLine());
        return  list;
    }
}
