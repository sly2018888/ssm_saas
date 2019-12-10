package cn.itcast.controller.feedback;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.feedback.FeedbackC;
import cn.itcast.domain.feedback.FeedbackCExample;
import cn.itcast.domain.system.User;
import cn.itcast.service.feedback.FeedBackService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/feedBackC")
public class FeedBackController extends BaseController {

    @Reference
    private FeedBackService feedBackService;

    @RequestMapping("/list")
    public String findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){

        User user = getUser();
        Integer degree = user.getDegree();

        FeedbackCExample example = new FeedbackCExample();
        FeedbackCExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());

        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }

        //设置排序
        example.setOrderByClause("create_time desc");

        PageInfo pageInfo = feedBackService.findByMy(example,page,pageSize);
        request.setAttribute("page",pageInfo);
        request.setAttribute("myId",getUser().getId());
        request.setAttribute("degree",degree);
        return "feedBack/feedBack-list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "feedBack/feedBack-add";
    }

    @RequestMapping("/delete")
    public String delete(String id){
        feedBackService.deleteById(id);
        return "redirect:/system/feedBackC/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        FeedbackC feedbackC = feedBackService.findById(id);
        request.setAttribute("feedbackC",feedbackC);
        request.setAttribute("myId",getUser().getId());
        request.setAttribute("degree",getUser().getDegree());
        return "feedBack/feedBack-update";
    }

    @RequestMapping("/edit")
    public String edit(FeedbackC feedbackC){
        if(StringUtils.isEmpty(feedbackC.getFeedbackId())){
            feedbackC.setFeedbackId(UUID.randomUUID().toString());
            feedbackC.setInputBy(getUser().getUserName());
            feedbackC.setCreateBy(getUser().getId());
            feedbackC.setInputTime(new Date());
            feedbackC.setCompanyId(getCompanyId());
            feedbackC.setCreateDept(getUser().getDeptId());
            feedbackC.setState("0");
            feedBackService.save(feedbackC);
        }else {
            if(!StringUtils.isEmpty(feedbackC.getSolveMethod())){
                feedbackC.setAnswerBy(getUser().getUserName());
                feedbackC.setState("1");
                feedbackC.setAnswerTime(new Date());
            }
            feedBackService.update(feedbackC);
        }
        return "redirect:/system/feedBackC/list.do";
    }


    @RequestMapping("/toView")
    public String toView(String id){
        FeedbackC feedbackC = feedBackService.findById(id);
        request.setAttribute("feedbackC",feedbackC);
        return "feedBack/feedBack-view";
    }

    @RequestMapping("/listNew")
    @ResponseBody
    public List<FeedbackC> listNew(){
        FeedbackCExample example = new FeedbackCExample();
        FeedbackCExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo("0");
        example.setOrderByClause("INPUT_TIME desc");
        List<FeedbackC> list = feedBackService.findAll(example);
        return list;
    }

    @RequestMapping("/toList")
    public String toList(){
        return "feedBack/feedBack-list";
    }

    @RequestMapping("/comback")
    @ResponseBody
    public List<FeedbackC> comback(){
        User user = getUser();
        Integer degree = user.getDegree();

        FeedbackCExample example = new FeedbackCExample();
        FeedbackCExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());
        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }
        criteria.andStateEqualTo("1");
        //设置排序
        example.setOrderByClause("create_time desc");

        List<FeedbackC> list = feedBackService.findAll(example);
        return  list;
    }


    @RequestMapping("/findById")
    @ResponseBody
    public FeedbackC findById(String id){
        FeedbackC feedbackC = feedBackService.findById(id);
        session.setAttribute("feedbackC",feedbackC);
        return feedbackC;
    }



}
