package vn.fpoly.project;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpoly.project.controller.UserController;
import vn.fpoly.project.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // ===== TEST GET LOGIN =====
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    // ===== TEST LOGIN SUCCESS CUSTOMER =====
    @Test
    public void testLoginCustomer() throws Exception {
        when(userService.validateLogin("123", "123")).thenReturn(true);
        when(userService.validateRole("123")).thenReturn("CUSTOMER");

        mockMvc.perform(post("/user/login")
                        .param("phone", "123")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    // ===== TEST LOGIN ADMIN =====
    @Test
    public void testLoginAdmin() throws Exception {
        when(userService.validateLogin("123", "123")).thenReturn(true);
        when(userService.validateRole("123")).thenReturn("ADMIN");

        mockMvc.perform(post("/user/login")
                        .param("phone", "123")
                        .param("password", "123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/page"));
    }

    // ===== TEST LOGIN FAIL =====
    @Test
    public void testLoginFail() throws Exception {
        when(userService.validateLogin("123", "123")).thenReturn(false);

        mockMvc.perform(post("/user/login")
                        .param("phone", "123")
                        .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    // ===== TEST REGISTER PAGE =====
    @Test
    public void testRegisterPage() throws Exception {
        mockMvc.perform(get("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    // ===== TEST CHANGE PASSWORD SUCCESS =====
    @Test
    public void testChangePasswordSuccess() throws Exception {
        when(userService.passwordChange("123", "123", "123")).thenReturn(true);

        mockMvc.perform(post("/user/passwordchange")
                        .param("phone", "123")
                        .param("password", "123")
                        .param("confirmPassword", "123"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("changepass"));
    }

    // ===== TEST CHANGE PASSWORD FAIL =====
    @Test
    public void testChangePasswordFail() throws Exception {
        when(userService.passwordChange("123", "123", "456")).thenReturn(false);

        mockMvc.perform(post("/user/passwordchange")
                        .param("phone", "123")
                        .param("password", "123")
                        .param("confirmPassword", "456"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("changepass"));
    }
}
