package com.rabbiter.oes.exam.controller;

import com.rabbiter.oes.common.resp.ApiResult;
import com.rabbiter.oes.common.resp.ApiResultHandler;
import com.rabbiter.oes.exam.entity.Admin;
import com.rabbiter.oes.exam.service.impl.AdminServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private final AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }

    @Operation(summary = "查询全部")
    @GetMapping("/admins")
    public ApiResult findAll(){
        return ApiResultHandler.success(adminService.findAll());
    }

    @Operation(summary = "根据ID查找")
    @GetMapping("/admin/{adminId}")
    public ApiResult findById(@PathVariable("adminId") Integer adminId){
        return ApiResultHandler.success(adminService.findById(adminId));
    }

    @DeleteMapping("/admin/{adminId}")
    public ApiResult deleteById(@PathVariable("adminId") Integer adminId){
        adminService.deleteById(adminId);
        return ApiResultHandler.success();
    }

    @PutMapping("/admin/{adminId}")
    public ApiResult update(@PathVariable("adminId") Integer adminId, Admin admin){
        return ApiResultHandler.success(adminService.update(admin));
    }

    @PostMapping("/admin")
    public ApiResult add(Admin admin){
        return ApiResultHandler.success(adminService.add(admin));
    }

    @GetMapping("/admin/resetPsw/{adminId}/{oldPsw}/{newPsw}")
    public ApiResult resetPsw(@PathVariable("adminId") Integer adminId, @PathVariable("newPsw") String newPsw, @PathVariable("oldPsw") String oldPsw) {
        return ApiResultHandler.success(adminService.resetPsw(adminId, newPsw, oldPsw));
    }
}
