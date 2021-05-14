package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.model.request.role.RoleRequest;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import com.phamvanviet.losoxa.model.response.RoleResponse;
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
@RequestMapping("/admin/role")
public class AdminRoleController {
    private final int limit = PageConstant.LIMIT_ROLE_ADMIN;

    @Autowired
    private PageService pageService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ModelAndView getListRole(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/role/list-role");
        List<RoleResponse> roleResponses = roleService.getPageRole((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminRole((page - 1) * limit, limit, page, roleResponses);
        mav.addObject("role", roleResponses);
        mav.addObject("model", pageResponse);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreateRole(@ModelAttribute(value = "roleRequest") RoleRequest roleRequest) {
        ModelAndView mav = new ModelAndView("/admin/role/create-role");
        List<PermissionResponse> permissionResponses = permissionService.findByLevel1();
        mav.addObject("permission", permissionResponses);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView getUpdateRole(@PathVariable(value = "id") Long id, @ModelAttribute(value = "roleRequest") RoleRequest roleRequest) {
        ModelAndView mav = new ModelAndView("/admin/role/update-role");
        RoleResponse roleResponse = roleService.findById(id);
        mav.addObject("role",roleResponse);
        List<String> permissionNames = roleService.getPermissionNameByRole(id);
        mav.addObject("permissionNames", permissionNames);
        List<PermissionResponse> permissionResponses = permissionService.findByLevel1();
        mav.addObject("permission", permissionResponses);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "redirect:/admin/role";
    }

}
