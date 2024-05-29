package com.ra.projectmd03_nhom4.controller.admin;

import com.ra.projectmd03_nhom4.dto.request.BannerRequest;
import com.ra.projectmd03_nhom4.model.Banner;
import com.ra.projectmd03_nhom4.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/banner")
public class BannerController {
    @Autowired
    IBannerService bannerService;

    @GetMapping("/list")
    public String listBanner(Model model,
                             @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                             @RequestParam(value = "size", defaultValue = "5", required = false) int size,
                             @RequestParam(value = "sortField", defaultValue = "isDisplay", required = false) String sortField,
                             @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
                             @RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery,
                             @ModelAttribute("message") String message) {

        List<Banner> banners = bannerService.findAll(page, size, sortField, sortDirection, searchQuery);
        long totalItems = bannerService.countAllBanner();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("banners", banners);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("searchQuery", searchQuery);
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "admin/banner/list";
    }

    @GetMapping("/add")
    public String addBanner(Model model) {
        model.addAttribute("bannerRequest", new BannerRequest());
        return "admin/banner/add";
    }

    @PostMapping("/insertBanner")
    public String insertBanner(@ModelAttribute("banner") BannerRequest bannerRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/banner/add";
        } else {
            bannerService.save(bannerRequest);
            redirectAttributes.addFlashAttribute("message", "Banner added successfully!");
            return "redirect:/admin/banner/list";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBanner(@PathVariable("id") Long id, Model model) {
        Banner banner = bannerService.findById(id);
        BannerRequest bannerRequest = BannerRequest.builder()
                .id(banner.getId())
                .title(banner.getTitle())
                .isDisplay(banner.getIsDisplay())
                .url(banner.getUrl()).build();
        model.addAttribute("banner", bannerRequest);
        return "admin/banner/edit";
    }

    @PostMapping("/updateBanner/{id}")
    public String updateBanner(@PathVariable Long id, @ModelAttribute("banner") BannerRequest bannerRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/banner/edit";
        } else {
            bannerRequest.setId(id);
            bannerService.save(bannerRequest);
            redirectAttributes.addFlashAttribute("message", "Update banner success!");
            return "redirect:/admin/banner/list";
        }
    }

    @GetMapping("/delete")
    public String deleteBanner(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        bannerService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Banner deleted successfully!");
        return "redirect:/admin/banner/list";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("newStatus") Boolean newStatus, RedirectAttributes redirectAttributes) {
        bannerService.updateStatus(id, newStatus);
        redirectAttributes.addFlashAttribute("message", "Update display success!");
        return "redirect:/admin/banner/list";
    }
}
