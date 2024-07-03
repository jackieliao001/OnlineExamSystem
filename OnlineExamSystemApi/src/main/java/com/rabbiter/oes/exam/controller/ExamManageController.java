package com.rabbiter.oes.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.common.enums.ResponseCode;
import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.ExamManage;
import com.rabbiter.oes.exam.service.impl.ExamManageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamManageController {

    @Autowired
    private ExamManageServiceImpl examManageService;

    @Operation(summary = "不分页查询所有试卷")
    @GetMapping("/exams")
    public ApiResult findAll(){
        ApiResult apiResult = ApiResultHandler.success(examManageService.findAll());
        return apiResult;
    }

    @Operation(summary = "分页查询所有试卷")
    @GetMapping("/exams/{page}/{size}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAll(examManage);
        apiResult = ApiResultHandler.success(all);
        return apiResult;
    }

    @GetMapping("/exam/{examCode}")
    public ApiResult findById(@PathVariable("examCode") Integer examCode){
        ExamManage res = examManageService.findById(examCode);
        if(res == null) {
            return ApiResultHandler.failure(ResponseCode.NOT_FOUND_KEY);
        }
        return ApiResultHandler.success(res);
    }

    @DeleteMapping("/exam/{examCode}")
    public ApiResult deleteById(@PathVariable("examCode") Integer examCode){
        int res = examManageService.delete(examCode);
        return ApiResultHandler.success(res);
    }

    @Operation(summary = "更新考试信息")
    @PutMapping("/exam")
    public ApiResult update(@RequestBody ExamManage exammanage){
        int res = examManageService.update(exammanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        return ApiResultHandler.success(res);
    }

    @PostMapping("/exam")
    public ApiResult add(@RequestBody ExamManage exammanage){
        int res = examManageService.add(exammanage);
        if (res ==1) {
            return ApiResultHandler.success(res);
        } else {
            return ApiResultHandler.failure();
        }
    }

    @GetMapping("/examManagePaperId")
    public ApiResult<ExamManage> findOnlyPaperId() {
        ExamManage res = examManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.success(res);
        }
        return ApiResultHandler.failure();
    }
}
