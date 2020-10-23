package com.example.demo.controller;

import com.example.demo.entity.Contactperson;
import com.example.demo.service.ContactpersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.internal.JMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Contactperson)表控制层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:57
 */
@RestController
@RequestMapping("contactperson")
@ResponseBody
@CrossOrigin
public class ContactpersonController {
    /**
     * 服务对象
     */
    @Resource
    private ContactpersonService contactpersonService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Contactperson selectOne(Integer id) {
        return this.contactpersonService.queryById(id);
    }

    @GetMapping("selectAll")
    public List<Contactperson> selectAll() {
        return this.contactpersonService.selectAll();
    }

    @GetMapping("selectByCid")
    public List<Contactperson> selectByCid(String cid) {
        return this.contactpersonService.selectByCid(cid);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(Integer id){
        return this.contactpersonService.deleteById(id);
    }

    @PostMapping("updateContactItem")
    public String updateContactItem(@RequestBody Contactperson contactperson){
//        System.out.println(contactperson.getEmail());
        contactpersonService.update(contactperson);
        return "updateContactItem";
    }

    @PostMapping("addContactPerson")
    public String addContactPerson(@RequestBody Map<String ,Object> map) throws JsonProcessingException {
        System.out.println(map.get("data"));
        System.out.println(map.get("cid"));
//        Map<String,String> map1 = (Map<String, String>) map.get("cid");
//        int id = Integer.valueOf(map1.get("cid"));
        Map<String,String> map1 = (Map<String, String>) map.get("cid");
        String id = map1.get("cid");
        System.out.println(map1.get("cid"));
        System.out.println("map1.get(cid)");

//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonInfo = objectMapper.writeValueAsString(map.get("data"));
//        Contactperson articleInfo = objectMapper.readValue(jsonInfo,Contactperson.class);

//        System.out.println(articleInfo);
        //下面的代码比较关键，本来如果只传入一个data的话，可以用@RequestBody Map<String ,List<Contactperson>> data 接收，像上面注释的代码那样，但是还需要传一个cid，用来确定给谁添加联系人
        //这里的data接收成为了object，而object这个object又是一个数字，所以下面用了循环，将单个object转换为实体对象，主要是下main的三行代码
//        String jsonInfo = objectMapper.writeValueAsString(objectList.get(i));
//        Contactperson articleInfo = objectMapper.readValue(jsonInfo,Contactperson.class);
//        System.out.println(articleInfo);
        List<Object> objectList = (List<Object>) map.get("data");
        for(int i=0;i<objectList.size();i++){
            System.out.println(objectList.get(i));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInfo = objectMapper.writeValueAsString(objectList.get(i));
            Contactperson contactperson = objectMapper.readValue(jsonInfo,Contactperson.class);
            System.out.println(contactperson);
//            contactperson.setId(i+53);
            contactperson.setCid(id);
            contactpersonService.insert(contactperson);
        }

//        List<Contactperson> list = (List<Contactperson>) data.get("data");
////        List<Contactperson> list = data.get("data");
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i).getName());
////            Contactperson contactperson = new Contactperson();
////            contactperson.setName(list.get(i).get("name"));
//
////            System.out.println("list.get(i).get(name)");
////            System.out.println(list.get(i).get("name"));
//        }
        return "addContactPerson";
    }

    @GetMapping("insertContactperson")
    public String insertContactperson(@RequestParam("contactpersonName") String contactpersonName,
                                      @RequestParam("contactpersonGender") String contactpersonGender, @RequestParam("contactpersonPhonenumber") String contactpersonPhonenumber,
                                      @RequestParam("contactpersonHomephonenumber") String contactpersonHomephonenumber, @RequestParam("contactpersonWechat") String contactpersonWechat,
                                      @RequestParam("contactpersonEmail") String contactpersonEmail, @RequestParam("contactpersonIdentity") String contactpersonIdentity){
        Contactperson contactperson = new Contactperson();
        contactperson.setName(contactpersonName);
        contactperson.setGender(contactpersonGender);
        contactperson.setPhonenumber(contactpersonPhonenumber);
        contactperson.setWechat(contactpersonWechat);
        contactperson.setHomephonenumber(contactpersonHomephonenumber);
        contactperson.setEmail(contactpersonEmail);
        contactperson.setIdentity(contactpersonIdentity);
        contactpersonService.insertCustomer(contactperson);
        return "添加联系人成功";
    }

}