package com.huaxianvwa.school.controller;
/**
 * @author zsj
 * @date 2020/3
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.entity.AdminMenu;
import com.huaxianvwa.school.service.AdminMenuService;

/**
 * Menu controller.
 *
 * @author zsj
 * @date 2020/4
 */
@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public List<AdminMenu> menu() {
        return adminMenuService.getMenusByCurrentUser();
    }

    @GetMapping("/api/admin/role/menu")
    public List<AdminMenu> listAllMenus() {
        return adminMenuService.getMenusByRoleId(1);
    }
}
