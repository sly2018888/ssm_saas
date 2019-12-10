package cn.itcast.controller.cargo;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.cargo.*;
import cn.itcast.domain.system.User;
import cn.itcast.service.cargo.InvoiceService;
import cn.itcast.service.cargo.PackingService;
import cn.itcast.service.cargo.ShippingService;
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

@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {

    @Reference
    private InvoiceService invoiceService;

    @Reference
    private ShippingService shippingService;

    @Reference
    private PackingService packingService;

    @RequestMapping(value = "/toSave",name = "可选择的委托单列表")
    public String toSave(){
        //委托单信息    已提交的委托单

        ShippingExample example = new ShippingExample();
        ShippingExample.Criteria criteria = example.createCriteria();
        criteria.andShippingStateEqualTo(1);
        PageInfo page = shippingService.findPage(example,1,10);

        List<Shipping> Shippings = page.getList();
        List<Shipping> Shippingfu = new ArrayList<>();
        for (Shipping shipping : Shippings) {
            Invoice invoice = invoiceService.findById(shipping.getShippingOrderId());
            if(invoice==null){
                    Shippingfu.add(shipping);
            }
        }
        page.setList(Shippingfu);
        request.setAttribute("page",page);
        return "/cargo/invoice/invoice-add";
    }

    @RequestMapping(value = "/list",name = "发票列表")
    public String list(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        User user = getUser();
        Integer degree = user.getDegree();

        InvoiceExample example = new InvoiceExample();
        InvoiceExample.Criteria criteria = example.createCriteria();

        if(degree == 4){
            criteria.andCreateByEqualTo(user.getId());
        }else if(degree ==3){
            criteria.andCreateDeptEqualTo(user.getDeptId());
        }else if(degree == 2){
            criteria.andCreateDeptLike(user.getDeptId() + "%");
        }

        //设置排序
        example.setOrderByClause("create_time desc");

        PageInfo pageInfo = invoiceService.findAll(example,page,pageSize);
        request.setAttribute("page",pageInfo);
        return "/cargo/invoice/invoice-list";
    }

    @RequestMapping(value = "/edit",name = "新建发票/修改发票")
    public String edit(Invoice invoice, String id){

        if(StringUtils.isEmpty(invoice.getInvoiceId())) {
            invoice.setInvoiceId(id);
            PackingListC packingListC = packingService.findById(id);
            invoice.setScNo(packingListC.getExportNos());
            invoice.setState(0);
            invoice.setCreateBy(getUser().getId());
            invoice.setCreateTime(new Date());
            invoice.setCreateDept(getUser().getDeptId());
            invoiceService.save(invoice);
        }else {
            invoiceService.update(invoice);
        }
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/delete",name = "删除发票")//装箱单处于草稿状态 删除恢复状态  处于已上报状态 不可删除
    public String delete(String id){
        invoiceService.delete(id);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "跳转到修改发票页面")
    public String toUpdate(String id,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize){
        Invoice invoice = invoiceService.findById(id);
        request.setAttribute("invoice",invoice);//查询要修改的发票信息
        Shipping shipping = shippingService.findById(id);
        request.setAttribute("Shipping",shipping);//查询原来的委托单信息
        return "/cargo/invoice/invoice-update";
    }


    @RequestMapping(value = "/submit",name = "提交发票")
    public String submit(String id){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(id);
        invoice.setState(1);
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/cancel",name = "取消提交发票")
    public String cancel(String id){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(id);
        invoice.setState(0);
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/toView",name = "查看发票")
    public String toView(String id){
        Invoice invoice = invoiceService.findById(id);
        request.setAttribute("invoice",invoice);
        Shipping shipping =shippingService.findById(id);
        request.setAttribute("shippingOrder",shipping);
        return "/cargo/invoice/invoice-view";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Invoice findById(String id){
        Invoice invoice = invoiceService.findById(id);
        return invoice;
    }



}
