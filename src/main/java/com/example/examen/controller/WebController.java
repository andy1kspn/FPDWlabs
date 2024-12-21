package com.example.examen.controller;
import com.example.examen.model.*;
import com.example.examen.service.MenuService;
import com.example.examen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("menuItems", menuService.getAllMenuItems());
        return "client/menu";
    }

    @GetMapping("/admin/menu-management")
    @PreAuthorize("hasRole('ADMIN')")
    public String showMenuManagement(Model model) {
        model.addAttribute("menuItems", menuService.getAllMenuItems());
        model.addAttribute("categories", menuService.getAllCategories());
        return "admin/menu-management";
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminDashboard(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('CLIENT')")
    public String showOrders(Model model, Principal principal) {
         List<Order> orders = orderService.getUserOrders(principal.getName());
        model.addAttribute("orders", orders);
        return "client/orders";
    }

    @PostMapping("/admin/menu/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {
        menuService.addMenuItem(menuItem);
        return "redirect:/admin/menu-management";
    }

    @PostMapping("/admin/menu/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
        return "redirect:/admin/menu-management";
    }

    @PostMapping("/order/create")
    @PreAuthorize("hasRole('CLIENT')")
    public String createOrder(@ModelAttribute Order order, Principal principal) {
        orderService.createOrder(order, principal.getName());
        return "redirect:/orders";
    }
}