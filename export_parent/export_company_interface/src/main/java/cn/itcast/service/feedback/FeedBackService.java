package cn.itcast.service.feedback;

import cn.itcast.domain.feedback.FeedbackC;
import cn.itcast.domain.feedback.FeedbackCExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FeedBackService {


    PageInfo findByMy(FeedbackCExample example, Integer page, Integer pageSize);

    FeedbackC findById(String id);

    void save(FeedbackC feedbackC);

    void update(FeedbackC feedbackC);

    void deleteById(String id);


    List<FeedbackC> findAll(FeedbackCExample example);
}
