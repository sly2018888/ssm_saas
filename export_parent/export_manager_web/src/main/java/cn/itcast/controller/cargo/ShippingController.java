package cn.itcast.controller.cargo;


import cn.itcast.controller.BaseController;
import cn.itcast.domain.cargo.PackingListC;
import cn.itcast.domain.cargo.PackingListCExample;
import cn.itcast.domain.cargo.Shipping;
import cn.itcast.domain.cargo.ShippingExample;
import cn.itcast.service.cargo.PackingService;
import cn.itcast.service.cargo.ShippingService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/cargo/shipping")
public class ShippingController extends BaseController {

    @Reference
    private ShippingService shippingService;

    @Reference
    private PackingService packingService;

    @RequestMapping(value = "/list", name = "进入委托书查询界面")
    public String findAll(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        ShippingExample shippingExample = new ShippingExample();
        ShippingExample.Criteria criteria = shippingExample.createCriteria();
        criteria.andStateEqualTo(7);
        shippingExample.setOrderByClause("create_time desc");
        PageInfo<Shipping> list = shippingService.findPage(shippingExample, pageNum, pageSize);
        request.setAttribute("page", list);
        return "/cargo/shipping/shipping-list";
    }

    @RequestMapping(value = "toAdd", name = "进入委托书添加界面")
    public String toAdd() {
        PackingListCExample example = new PackingListCExample();
        PackingListCExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        PageInfo page = packingService.findAll(example,1, 10);
        List<PackingListC> PackingListCs = page.getList();
        List<PackingListC> PackingListCfu = new ArrayList<>();

        for (PackingListC packingListC : PackingListCs) {
            Shipping shipping1 = shippingService.findById(packingListC.getPackingListId());
            if(shipping1==null){
                    PackingListCfu.add(packingListC);

            }
        }
        page.setList(PackingListCfu);
        request.setAttribute("page", page);
        return "/cargo/shipping/shipping-add";
    }

    @RequestMapping(value = "toUpdate", name = "进入委托书修改界面")
    public String toUpdate(String id) {
        Shipping shipping = shippingService.findById(id);
        PackingListC packingListC = packingService.findById(id);
        request.setAttribute("packingListC", packingListC);
        request.setAttribute("shipping", shipping);
        return "/cargo/shipping/shipping-update";
    }

    @RequestMapping(value = "edit", name = "进行委托书保存操作")
    public String edit(String id, Shipping shipping) {
        if (id.equals(shipping.getShippingOrderId())) {
            shippingService.update(shipping);
        } else  {
            shipping.setShippingOrderId(id);
            shipping.setCreateTime(new Date());
            shipping.setState(7);
            shippingService.save(shipping);
        }
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "delete", name = "进行委托书删除操作")
    public String delete(String id) {
        shippingService.delete(id);
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "toView", name = "进入委托书查看界面")
    public String toView(String id) {
        Shipping shipping = shippingService.findById(id);
        PackingListC packingListC = packingService.findById(id);
        request.setAttribute("packingListC", packingListC);
        request.setAttribute("shipping", shipping);
        return "cargo/shipping/shipping-view";
    }

    @RequestMapping(value = "submit", name = "进行委托单的提交")
    public String submit(String id) {
        Shipping shipping = shippingService.findById(id);
        shipping.setShippingState(1);
        shippingService.update(shipping);
        request.setAttribute("shipping", shipping);
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "cancel", name = "取消委托单的提交")
    public String cancel(String id) {
        Shipping shipping = shippingService.findById(id);
        shipping.setShippingState(0);
        shippingService.update(shipping);
        request.setAttribute("shipping", shipping);
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Shipping findById(String id){
        Shipping  shipping = shippingService.findById(id);
        return shipping;
    }


}
