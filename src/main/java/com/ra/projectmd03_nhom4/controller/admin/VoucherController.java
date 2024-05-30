package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.dto.request.FromAddUser;
import com.ra.projectmd03_nhom4.model.User;
import com.ra.projectmd03_nhom4.model.Voucher;
import com.ra.projectmd03_nhom4.service.iplm.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private HttpSession session;

    @GetMapping("/list")
    public String listUser(Model model,
                           @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                           @RequestParam(value = "size", defaultValue = "5", required = false) int size,
                           @RequestParam(value = "sortField", defaultValue = "voucherId", required = false) String sortField,
                           @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
                           @RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery,
                           @ModelAttribute("message") String message) {

        List<Voucher> vouchers = voucherService.findAll(page, size, sortField, sortDirection, searchQuery);
        long totalItems = voucherService.findAllCode(searchQuery);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("vouchers", vouchers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("searchQuery", searchQuery);

        session.setAttribute("activePage", "voucher");

        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "admin/voucher/list";
    }

    @GetMapping("/add")
    public String addVoucher(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "admin/voucher/add";
    }

    @PostMapping("/insertVoucher")
    public String insertUser(@Valid @ModelAttribute("voucher")  Voucher voucher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/voucher/add";
        } else {
            voucherService.save(voucher);
            return "redirect:/admin/voucher/list";
        }
    }

    @GetMapping("/edit/{id}")
    public String editVoucher(@PathVariable("id") Long id, Model model) {
        Voucher voucher = voucherService.findByCode(id);
        model.addAttribute("voucher", voucher);
        return "admin/voucher/edit";
    }

    @PostMapping("/update")
    public String updateVoucher(@Valid @ModelAttribute("voucher")  Voucher voucher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/voucher/edit";
        } else {
            voucherService.update(voucher);
            return "redirect:/admin/voucher/list";
        }
    }

    @GetMapping("/delete")
    public String deleteVoucher(@RequestParam("id") Long id) {
        Voucher voucher = voucherService.findByCode(id);
        voucherService.delete(voucher);
        return "redirect:/admin/voucher/list";
    }
}
