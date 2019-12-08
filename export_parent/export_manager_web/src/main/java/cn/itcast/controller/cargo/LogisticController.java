package cn.itcast.controller.cargo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cargo/logistic")
public class LogisticController {


    @RequestMapping(value = "/list",name = "进入物流管理页面")
    public String toList(){

        return "/cargo/logistic/logistic-list";
    }
}
