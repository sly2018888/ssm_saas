package cn.itcast.dao.feedback;

import cn.itcast.domain.feedback.FeedbackC;
import cn.itcast.domain.feedback.FeedbackCExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackCDao {
    long countByExample(FeedbackCExample example);

    void deleteByExample(FeedbackCExample example);

    void deleteByPrimaryKey(String feedbackId);

    void insert(FeedbackC record);

    void insertSelective(FeedbackC record);

    List<FeedbackC> selectByExample(FeedbackCExample example);

    FeedbackC selectByPrimaryKey(String feedbackId);

    void updateByExampleSelective(@Param("record") FeedbackC record, @Param("example") FeedbackCExample example);

    void updateByExample(@Param("record") FeedbackC record, @Param("example") FeedbackCExample example);

    void updateByPrimaryKeySelective(FeedbackC record);

    void updateByPrimaryKey(FeedbackC record);

    List<FeedbackC> findAll();

    List<FeedbackC> findNewInfo();
}