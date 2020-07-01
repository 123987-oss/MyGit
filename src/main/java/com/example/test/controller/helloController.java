package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class helloController {
    @Autowired
    UserService userService;


    @Autowired
    UserMapper userMapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /*查询用户列表*/
    @RequestMapping("/list")
    public String userList(Model model, @RequestParam(required = false,value = "pageNum",defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize) {
//        List<User> cs = userService.userList();
//        // 将查询的数据以分页的形式展示
//        PageHelper.startPage(start,size,"id desc");// 还可以指定排序规则
//        PageInfo<User> page = new PageInfo<>(cs);
//        model.addAttribute("page",page);
//        return "list";
//        model.addAttribute("users", userService.userList());
//        return "list";
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 3;    //设置默认每页显示的数据数
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<User> userList = userService.userList();
            System.out.println("分页数据："+userList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<User> pageInfo = new PageInfo<>(userList,pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "list";
    }

    /*删除用户*/
    @RequestMapping("/del")
    public String deleteUser(Integer id) {
        userService.delete(id);
        return "redirect:/list";
    }

    /*添加用户页面*/
    @RequestMapping("/add")
    public String addUser(ModelMap map) {
        map.addAttribute("user", new User());
        return "add";
    }

    /*更新用户页面*/
    @RequestMapping("/update")
    public String updateUser(ModelMap map,Integer id) {
        User user = userService.queryUserById(id);
        map.addAttribute("user", user);
        return "update";
    }

    /*添加完用户后重定向到list页面*/
    @RequestMapping("/saveI")
    public String saveI(@ModelAttribute User user) {

        userService.insert(user);
        return "redirect:/list";
    }

    /*更新完用户后重定向到list页面*/
    @RequestMapping("/saveU")
    public String saveU(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/list";

    }
}
