package vn.fpoly.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpoly.project.controller.admincontroller;
import vn.fpoly.project.repo.*;
import vn.fpoly.project.service.UserService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(admincontroller.class)
public class adminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean private productRepo repo;
    @MockBean private userRepo urepo;
    @MockBean private invoicesRepo repoinvoice;
    @MockBean private voucherRepo vrepo;
    @MockBean private staffRepo repostaff;
    @MockBean private UserService userService;

    // ===== LOAD ADMIN PAGE =====
    @Test
    public void testAdminPage() throws Exception {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/page"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("listProduct"))
                .andExpect(view().name("adminpage"));
    }

    // ===== SAVE PRODUCT =====
    @Test
    public void testSaveProduct() throws Exception {
        mockMvc.perform(post("/admin/product/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/page"));
    }

    // ===== DELETE PRODUCT =====
    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/admin/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/page"));
    }

    // ===== LOAD STAFF =====
    @Test
    public void testStaffPage() throws Exception {
        when(repostaff.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/staff"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("listStaff"))
                .andExpect(view().name("adminstaff"));
    }

    // ===== DELETE STAFF =====
    @Test
    public void testDeleteStaff() throws Exception {
        mockMvc.perform(get("/admin/staff/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/staff"));
    }

    // ===== SEARCH PRODUCT =====
    @Test
    public void testSearchProduct() throws Exception {
        when(repo.searchproduct("gau")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/page/search")
                        .param("keyword", "gau"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminpage"));
    }

    // ===== SEARCH USER =====
    @Test
    public void testSearchUser() throws Exception {
        when(userService.findUserByName("abc")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/user/search")
                        .param("search", "abc"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminpage"));
    }
}
