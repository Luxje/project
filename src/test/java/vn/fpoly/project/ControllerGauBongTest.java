package vn.fpoly.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpoly.project.controller.ControllerGauBong;
import vn.fpoly.project.repo.productRepo;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerGauBong.class)
public class ControllerGauBongTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private productRepo repo;

    // ===== HOME =====
    @Test
    public void testHome() throws Exception {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("object"))
                .andExpect(view().name("index"));
    }

    // ===== GIO HANG =====
    @Test
    public void testGioHang() throws Exception {
        mockMvc.perform(get("/giohang"))
                .andExpect(status().isOk())
                .andExpect(view().name("giohang"));
    }

    // ===== SAN PHAM =====
    @Test
    public void testSanPham() throws Exception {
        mockMvc.perform(get("/sanpham"))
                .andExpect(status().isOk())
                .andExpect(view().name("sanpham"));
    }

    // ===== LOGIN =====
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    // ===== ADMIN QLSP =====
    @Test
    public void testAdminQLSP() throws Exception {
        mockMvc.perform(get("/admin/qlsp"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/qlsp"));
    }

    // ===== ADMIN QLKM =====
    @Test
    public void testAdminQLKM() throws Exception {
        mockMvc.perform(get("/admin/qlkm"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/qlkm"));
    }

    // ===== ADMIN QLNV =====
    @Test
    public void testAdminQLNV() throws Exception {
        mockMvc.perform(get("/admin/qlnv"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/qlnv"));
    }

    // ===== ADMIN QLHD =====
    @Test
    public void testAdminQLHD() throws Exception {
        mockMvc.perform(get("/admin/qlhd"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/qlhd"));
    }

    // ===== THONG KE =====
    @Test
    public void testThongKe() throws Exception {
        mockMvc.perform(get("/admin/tkdt"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/tkdt"));
    }

    // ===== TAI KHOAN =====
    @Test
    public void testTaiKhoan() throws Exception {
        mockMvc.perform(get("/taikhoan"))
                .andExpect(status().isOk())
                .andExpect(view().name("taikhoan"));
    }
}
