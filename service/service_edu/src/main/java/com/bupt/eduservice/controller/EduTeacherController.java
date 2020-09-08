package com.bupt.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bupt.eduservice.service.EduTeacherService;
import com.bupt.commonutils.result.R;
import com.bupt.eduservice.entity.EduTeacher;
import com.bupt.eduservice.entity.vo.QueryVo;
import com.bupt.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-05-26
 */
@Api(description = "讲师管理")
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "讲师列表")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> teachers = eduTeacherService.list();
        return R.ok().data("teachers",teachers);
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("remove/{id}")
    public R deleteTeacherById(@ApiParam(name = "id",value = "讲师的id",required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable long current,@PathVariable long limit) {
//        创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        try {
            int i = 10 / 0;
        }catch (Exception e) {
            throw new GuliException(20001,"进行了自定义异常处理");
        }

        eduTeacherService.page(page, null);

        long total = page.getTotal();
        List<EduTeacher> eduTeachers = page.getRecords();

     /*   Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",eduTeachers);
        return R.ok().data(map);*/
        return R.ok().data("total",total).data("rows",eduTeachers);
    }

    @ApiOperation(value = "组合条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody @ApiParam(name = "query",value = "组合条件",required = false)
            QueryVo queryVo) {
        Page<EduTeacher> page = new Page<>(current,limit);

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVo.getName())) {
           queryWrapper.like("name",queryVo.getName());
        }
        if(!StringUtils.isEmpty(queryVo.getLevel())) {
            queryWrapper.eq("level",queryVo.getLevel());
        }
        if(!StringUtils.isEmpty(queryVo.getBegin())) {
            queryWrapper.ge("join_date",queryVo.getBegin());
        }
        if(!StringUtils.isEmpty(queryVo.getEnd())) {
            queryWrapper.le("join_date",queryVo.getEnd());
        }

        queryWrapper.orderByDesc("join_date");
        eduTeacherService.page(page, queryWrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.save(eduTeacher);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacherById/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
}

