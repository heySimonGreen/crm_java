package com.example.demo.controller;

import com.example.demo.entity.Contactaddress;
import com.example.demo.entity.Contactperson;
import com.example.demo.service.ContactaddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Contactaddress)表控制层
 *
 * @author chenwei
 * @since 2020-09-22 23:41:56
 */
@RestController
@RequestMapping("contactaddress")
@ResponseBody
@CrossOrigin
public class ContactaddressController {
    /**
     * 服务对象
     */
    @Resource
    private ContactaddressService contactaddressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Contactaddress selectOne(Integer id) {
        return this.contactaddressService.queryById(id);
    }

    @GetMapping("selectAll")
    public List<Contactaddress> selectAll() {
        return this.contactaddressService.selectAll();
    }

    @GetMapping("selectByCid")
    public List<Contactaddress> selectByCid(@RequestParam Integer cid){
        return this.contactaddressService.selectByCid(cid);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(Integer id){
        return this.contactaddressService.deleteById(id);
    }

    @PostMapping("addContactAddress")
    public String addContactPerson(@RequestBody Map<String ,Object> map) throws JsonProcessingException {
        System.out.println(map.get("data"));
        System.out.println(map.get("cid"));
//        Map<String,String> map1 = (Map<String, String>) map.get("cid");
//        int id = Integer.valueOf(map1.get("cid"));
//        if(map.get("cie") instanceof Map<String, Integer>){
//            Map<String,Integer> map1 = (Map<String, Integer>) map.get("cid");
//            System.out.println("yes");
//        }else {
//            System.out.println("no");
//        }
        Map<String,Integer> map1 = (Map<String, Integer>) map.get("cid");
        int id = map1.get("cid");
        System.out.println(map1.get("cid"));
        System.out.println("map1.get(cid)");
        List<Object> objectList = (List<Object>) map.get("data");
        for(int i=0;i<objectList.size();i++){
            System.out.println(objectList.get(i));
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInfo = objectMapper.writeValueAsString(objectList.get(i));
            Contactaddress contactaddress = objectMapper.readValue(jsonInfo,Contactaddress.class);
            System.out.println(contactaddress);
            contactaddress.setCid(id);
            contactaddressService.insert(contactaddress);
        }

        return "addContactPerson";
    }
    @PostMapping("updateAddressItem")
    public String updateAddressItem(@RequestBody Contactaddress contactaddress){
//        System.out.println(contactperson.getEmail());
        contactaddressService.update(contactaddress);
        System.out.println(contactaddress);
        System.out.println(contactaddress.getDetaileara());
        System.out.println(contactaddress.getDistrict());
        return "updateAddressItem";
    }


//    @GetMapping("insertContactaddress")
//    public String insertContactaddress(@RequestParam("contactaddressTitle") String contactaddressTitle, @RequestParam("contactaddressStampnumber") Integer contactaddressStampnumber,
//                                       @RequestParam("contactaddressCountry") String contactaddressCountry, @RequestParam("contactaddressProvince") String contactaddressProvince,
//                                       @RequestParam("contactaddressCity") String contactaddressCity, @RequestParam("contactaddressDistrict") String contactaddressDistrict){
//        Contactaddress contactaddress = new Contactaddress();
//        contactaddress.setTitle(contactaddressTitle);
//        contactaddress.setStampnumber(contactaddressStampnumber);
//        contactaddress.setCountry(contactaddressCountry);
//        contactaddress.setProvince(contactaddressProvince);
//        contactaddress.setCity(contactaddressCity);
//        contactaddress.setDistrict(contactaddressDistrict);
//        contactaddressService.insertCustomer(contactaddress);
//
//        return "hello";
//    }

}