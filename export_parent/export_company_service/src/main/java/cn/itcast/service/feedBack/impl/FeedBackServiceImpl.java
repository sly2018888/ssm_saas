package cn.itcast.service.feedBack.impl;

import cn.itcast.dao.feedback.FeedbackCDao;
import cn.itcast.domain.feedback.FeedbackC;
import cn.itcast.domain.feedback.FeedbackCExample;
import cn.itcast.service.feedback.FeedBackService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 5000)
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedbackCDao feedBackCDao;

    @Override
    public PageInfo findByMy(FeedbackCExample example, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<FeedbackC> list = feedBackCDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public FeedbackC findById(String id) {
        FeedbackC feedbackC = feedBackCDao.selectByPrimaryKey(id);
        return feedbackC;
    }

    @Override
    public void save(FeedbackC feedbackC) {
        feedBackCDao.insertSelective(feedbackC);
    }

    @Override
    public void update(FeedbackC feedbackC) {
        feedBackCDao.updateByPrimaryKeySelective(feedbackC);
    }

    @Override
    public void deleteById(String id) {
        feedBackCDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<FeedbackC>  findAll(FeedbackCExample example) {
        return feedBackCDao.selectByExample(example);
    }
}
