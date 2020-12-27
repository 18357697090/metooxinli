package com.metoo.web.controller.tj;


import com.metoo.api.tj.TjUserApi;
import com.loongya.core.util.RE;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/tj/user")
public class TjUserController {
    
        @DubboReference
        private TjUserApi tjUserApi;

        @GetMapping("getByUserId/{id}")
        public RE getUserById(@PathVariable(value = "id") Integer id){
            return tjUserApi.getUserById(id);
        }

        @PostMapping(value = "getByUserId2")
        public RE getUserById2(Integer id){
            return tjUserApi.getUserById(id);
        }

        @GetMapping("getList")
        public RE getList(){
                return tjUserApi.getList();
        }
}
