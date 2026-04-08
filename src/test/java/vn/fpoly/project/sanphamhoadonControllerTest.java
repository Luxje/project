package vn.fpoly.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpoly.project.controller.SanphamvahoadonController;
import vn.fpoly.project.model.products;
import vn.fpoly.project.repo.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SanphamvahoadonController.class)
public class sanphamhoadonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ✅ CHỈ ĐỂ 1 productRepo
    @MockBean private productRepo repo;
    @MockBean private voucherRepo voucher;
    @MockBean private userRepo userRepo;
    @MockBean private invoicesRepo invoicesrepo;
    @MockBean private invoiceitemsRepo repoitemsinvoice;

    // ===== TEST MUA SAN PHAM =====
    @Test
    public void testMuaSanPham() throws Exception {
        products p = new products();
        p.price = 100f;

        when(repo.findById(1)).thenReturn(Optional.of(p));
        when(voucher.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/muasanpham")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("sp"))
                .andExpect(model().attributeExists("vouchers"))
                .andExpect(view().name("muahang"));
    }

    // ===== TEST XAC NHAN DON HANG =====
    @Test
    public void testXacNhan() throws Exception {
        products p = new products();
        p.price = 100f;

        // ✅ dùng repo luôn (không dùng productrepo nữa)
        when(repo.findById(1)).thenReturn(Optional.of(p));
        when(userRepo.findAll()).thenReturn(new ArrayList<>());
        when(invoicesrepo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/xacnhan")
                        .param("id", "1")
                        .param("customerName", "Bao")
                        .param("phone", "123")
                        .param("address", "HN")
                        .param("role", "CUSTOMER")
                        .param("total", "100")
                        .param("gender", "true")
                        .param("voucherId", "1")
                        .param("quantity", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("hoadon"));
    }

    // ===== TEST SEARCH =====
    @Test
    public void testSearchSanPham() throws Exception {
        when(repo.searchproduct("gau")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/searchsanpham")
                        .param("name", "gau"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}