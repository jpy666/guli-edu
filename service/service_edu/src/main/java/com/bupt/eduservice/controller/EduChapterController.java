package com.bupt.eduservice.controller;

import com.bupt.eduservice.entity.EduChapter;
import com.bupt.eduservice.entity.chapter.ChapterVo;
import com.bupt.commonutils.result.R;
import com.bupt.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-05-31
 */
@RestController
@RequestMapping("/eduservice/educhapter")
@CrossOrigin
@Api(description = "章节管理")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> chapterList = eduChapterService.getChapterVideo(courseId);
        return R.ok().data("chapterVideoList",chapterList);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @GetMapping("getChapterId/{chapterId}")
    public R getChapterId(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    /**
     * 删除章节删除视频
     * @param chapterId
     * @return
     */
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
}

