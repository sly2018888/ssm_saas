package cn.itcast.controller.cargo;

import cn.itcast.controller.BaseController;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.FactoryExample;
import cn.itcast.domain.cargo.Product;
import cn.itcast.domain.cargo.ProductExample;
import cn.itcast.service.cargo.FactoryService;
import cn.itcast.service.cargo.ProductService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/goods")
public class GoodsController extends BaseController {

    @Reference
    private ProductService productService;

    @Reference
    private FactoryService factoryService;

    @RequestMapping(value = "/list",name = "货物信息的展示 ")
    public String list(@RequestParam(name="page",defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10")  int size){
        ProductExample example = new ProductExample();
        example.setOrderByClause("create_time desc");
        PageInfo page = productService.findAllAndPage(example, pageNum, size);
        request.setAttribute("page",page);
        return "cargo/goods/goods-list";
    }

    @RequestMapping(value = "/toAdd",name = "进入添加产品页面")
    public String toAdd(){
        //        生成货物的工厂
        FactoryExample example = new FactoryExample();
        example.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(example);
        request.setAttribute("factoryList",factoryList);
        return "cargo/goods/goods-add";
    }

    @RequestMapping(value = "/edit",name = "保存产品信息的方法")
    public String edit(Product product){
        if(StringUtils.isEmpty(product.getId())){
            product.setId(UUID.randomUUID().toString());
            product.setCreateTime(new Date());
            product.setCreateBy(getUser().getId()); //当前登陆人的id
            product.setCreateDept(getUser().getDeptId());//当前登陆人部门的id
            productService.save(product);
        }else{
            product.setInputTime(new Date());
            productService.update(product);
        }
        return "redirect:/cargo/goods/list.do";       //重定向到列表页面
    }

    @RequestMapping(value = "/toUpdate",name = "进入修改页面")
    public String toUpdate(String id){
//        生成货物的工厂
        FactoryExample example = new FactoryExample();
        example.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(example);
        request.setAttribute("factoryList",factoryList);

        Product product = productService.findById(id);
        request.setAttribute("goods",product);
        return "cargo/goods/goods-update";
    }

    @RequestMapping(value = "/delete",name = "删除的方法")
    public String delete(String id){
        productService.delete(id);
        return "redirect:/cargo/goods/list.do";
    }
}
