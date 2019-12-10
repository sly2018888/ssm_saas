package cn.itcast.controller.cargo;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.ExportExample;
import cn.itcast.domain.cargo.PackingListC;
import cn.itcast.domain.cargo.PackingListCExample;
import cn.itcast.domain.system.User;
import cn.itcast.service.cargo.ExportService;
import cn.itcast.service.cargo.PackingService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RequestMapping("/cargo/packing")
@Controller
public class PackingController  extends BaseController {

    @Reference
    private PackingService packingService;
    @Reference
    private ExportService exportService;

    @RequestMapping(value = "/toSave",name = "可选择的报运单列表")
    public String toSave(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        ExportExample example = new ExportExample();
        ExportExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(Long.parseLong("1"));
        criteria.andCompanyIdEqualTo(getCompanyId());
        example.setOrderByClause("create_time desc");
        PageInfo pageInfo = exportService.findAll(example, page, pageSize);
        request.setAttribute("page",pageInfo);
        return "/cargo/packing/packing-add";
    }

    @RequestMapping(value = "/list",name = "装箱单列表")
    public String list(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        User user = getUser();
        Integer degree = user.getDegree();

        PackingListCExample example = new PackingListCExample();
        PackingListCExample.Criteria criteria = example.createCriteria();
        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }

        //设置排序
        example.setOrderByClause("create_time desc");

        PageInfo pageInfo = packingService.findAll(example,page,pageSize);
        request.setAttribute("page",pageInfo);
        return "/cargo/packing/packing-list";
    }

    @RequestMapping(value = "/edit",name = "新建装箱单/修改装箱单")
    public String edit(PackingListC packingListC, String id){
        packingListC.setExportIds(id);
        if(StringUtils.isEmpty(packingListC.getPackingListId())){
            packingListC.setPackingListId(UUID.randomUUID().toString());
            packingListC.setCreateBy(getUser().getId());
            packingListC.setExportIds(id);
            packingListC.setCreateTime(new Date());
            packingListC.setState(0);
            packingListC.setCreateBy(getUser().getId());
            packingListC.setCreateTime(new Date());
            packingListC.setCreateBy(getUser().getDeptId());
            packingService.save(packingListC);
        }else {
            packingService.update(packingListC);
        }
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "/delete",name = "删除装箱单")//装箱单处于草稿状态 删除恢复状态  处于已上报状态 不可删除
    public String delete(String id){
        packingService.delete(id);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        PackingListC packingListC = packingService.findById(id);
        request.setAttribute("packingListC",packingListC);
        ExportExample example = new ExportExample();
        ExportExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(Long.parseLong("2"));
        criteria.andCompanyIdEqualTo(getCompanyId());
        example.setOrderByClause("create_time desc");
        PageInfo pageInfo = exportService.findAll(example, page, pageSize);

        List<Export> list = new ArrayList<>();
        String[] exportIds = packingListC.getExportIds().split(",");
        for (String exportId : exportIds) {
            Export export = exportService.findById(exportId);
            pageInfo.getList().add(export);
        }
        request.setAttribute("page",pageInfo);
        return "/cargo/packing/packing-update";
    }


    @RequestMapping(value = "/submit",name = "提交装箱单")
    public String submit(String id){
        PackingListC packingListC = new PackingListC();
        packingListC.setPackingListId(id);
        packingListC.setState(1);
        packingService.update(packingListC);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "/cancel",name = "取消提交装箱单")
    public String cancel(String id){
        PackingListC packingListC = new PackingListC();
        packingListC.setPackingListId(id);
        packingListC.setState(0);
        packingService.update(packingListC);
        return "redirect:/cargo/packing/list.do";
    }

    @RequestMapping(value = "/toView",name = "查看装箱单")
    public String toView(String id){
        PackingListC packingListC = packingService.findById(id);
        String[] exportIds = packingListC.getExportIds().split(",");
        List<Export> listCList = new ArrayList<>();
        for (String exportId:exportIds){
            Export export = exportService.findById(exportId);
            listCList.add(export);
        }
        request.setAttribute("packingListC",packingListC);
        request.setAttribute("list",listCList);
        return "/cargo/packing/packing-view";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public  PackingListC findById(String id){
        PackingListC packingListC = packingService.findById(id);
        return packingListC;
    }


}
