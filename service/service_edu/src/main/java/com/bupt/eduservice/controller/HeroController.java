package com.bupt.eduservice.controller;

import com.bupt.commonutils.result.R;
import com.bupt.eduservice.entity.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:HeroController</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/9/2 16:13
 * Version 1.0
 */
@RestController
@RequestMapping("/hero")
public class HeroController {
    @GetMapping("getHeros")
    public R getHeros() {
        Hero[] heroes = new Hero[10];
        heroes[0] = new Hero(11,"Dr Nice");
        heroes[1] = new Hero(12,"Narco");
        heroes[2] = new Hero(13,"Bombasto");
        heroes[3] = new Hero(14,"Celeritas");
        heroes[4] = new Hero(15,"Magneta");
        heroes[5] = new Hero(16,"RubberMan");
        heroes[6] = new Hero(17,"Dynama");
        heroes[7] = new Hero(18,"Dr IQ");
        heroes[8] = new Hero(19,"Magma");
        heroes[9] = new Hero(20,"Tornado");
        return R.ok().data("heroes",heroes);
    }
}
