package cn.itcast.controller.cargo;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.FactoryExample;
import cn.itcast.service.cargo.FactoryService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/factory")
public class FactoryController extends BaseController {

    @Reference
    private FactoryService factoryService;

    @RequestMapping(value = "/list",name = "展示所有厂家信息")
    public String list(@RequestParam(name="page",defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10")  int size){
        FactoryExample example = new FactoryExample();
        example.createCriteria().andCtypeEqualTo("货物");
        example.setOrderByClause("create_time desc");
        PageInfo page = factoryService.findAllAndPage(example,pageNum,size);
        request.setAttribute("page",page);
        return "cargo/factory/factory-list";
    }

    @RequestMapping(value = "/toAdd",name = "进入添加厂家页面")
    public String toAdd(){
        return "cargo/factory/factory-add";
    }

    @RequestMapping(value = "/edit",name = "保存厂家信息的方法")
    public String edit(Factory factory){
        if(StringUtils.isEmpty(factory.getId())){
            factory.setId(UUID.randomUUID().toString());
            factory.setCreateTime(new Date());
            factory.setCompanyId(getCompanyId());
            factory.setCompanyName(getCompanyName());
            factory.setCreateBy(getUser().getId()); //当前登陆人的id
            factory.setCreateDept(getUser().getDeptId());//当前登陆人部门的id
            factoryService.save(factory);
        }else{
            factory.setUpdateTime(new Date());
            factoryService.update(factory);
        }
        return "redirect:/cargo/factory/list.do";       //重定向到列表页面
    }

    @RequestMapping(value = "/toUpdate",name = "进入修改页面")
    public String toUpdate(String id){
        Factory factory = factoryService.findById(id);
        request.setAttribute("factory",factory);
        return "cargo/factory/factory-update";
    }

    @RequestMapping(value = "/delete",name = "删除厂家的方法")
    public String delete(String id){
        factoryService.delete(id);
        return "redirect:/cargo/factory/list.do";
    }

}
