package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.model.request.permission.PermissionRequest;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.service.PermissionService;
import com.phamvanviet.losoxa.service.RoleService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/permission")
public class AdminPermissionController {
    private final int limit = PageConstant.LIMIT_PERMISSION_ADMIN;

    @Autowired
    private PageService pageService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ModelAndView getListPermission(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/permission/list-permission");
        List<PermissionResponse> permissionResponses = permissionService.getPagePermission((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminPermission((page - 1) * limit, limit, page, permissionResponses);
        List<PermissionResponse> permissionResponseList = permissionService.findAll();
        mav.addObject("permissions", permissionResponses);
        mav.addObject("permissionList", permissionResponseList);
        mav.addObject("model", pageResponse);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreatePermission(@ModelAttribute(value = "permissionRequest") PermissionRequest permissionRequest) {
        ModelAndView mav = new ModelAndView("/admin/permission/create-permission");
        List<PermissionResponse> permissionResponses = permissionService.findAll();
        mav.addObject("permission", permissionResponses);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView getUpdatePermission(@PathVariable(value = "id") Long id, @ModelAttribute(value = "permissionRequest") PermissionRequest permissionRequest) {
        ModelAndView mav = new ModelAndView("/admin/permission/update-permission");
        PermissionResponse permissionResponse = permissionService.findById(id);
        mav.addObject("permission",permissionResponse);
        List<PermissionResponse> permissionResponses = permissionService.findPermissionNotId(id);
        mav.addObject("permissions", permissionResponses);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return "redirect:/admin/permission";
    }
}
