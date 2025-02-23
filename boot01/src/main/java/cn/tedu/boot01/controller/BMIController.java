package cn.tedu.boot01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BMIController {
    @RequestMapping("/bmi")
    public String bmi(double h,double w){
        double bmi = w/(h*h);
        if (bmi<18.5){ return "兄弟你瘦了!"; }
        if (bmi<24){ return "非常棒!"; }
        if (bmi<27){ return "微胖!"; }
        return "该运动起来了!";
    }

    //  张三&18&男-李四&20&女-李四&20&女-李四&20&女
}
