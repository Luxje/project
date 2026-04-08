package vn.fpoly.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.fpoly.project.model.user;
import vn.fpoly.project.repo.userRepo;
import vn.fpoly.project.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private userRepo repo;

    @InjectMocks
    private UserService userService;

    // ===== TEST LOGIN =====
    @Test
    public void testLoginSuccess() {
        user u = new user();
        u.setPhone("123");
        u.setPassword("123");

        when(repo.findByPhone("123")).thenReturn(u);

        boolean result = userService.validateLogin("123", "123");

        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        when(repo.findByPhone("123")).thenReturn(null);

        boolean result = userService.validateLogin("123", "123");

        assertFalse(result);
    }

    // ===== TEST ROLE =====
    @Test
    public void testRoleAdmin() {
        user u = new user();
        u.setPhone("123");
        u.setRole("ADMIN");

        when(repo.findByPhone("123")).thenReturn(u);

        String role = userService.validateRole("123");

        assertEquals("ADMIN", role);
    }

    @Test
    public void testRoleNull() {
        when(repo.findByPhone("123")).thenReturn(null);

        String role = userService.validateRole("123");

        assertNull(role);
    }

    // ===== TEST DOI MAT KHAU =====
    @Test
    public void testPasswordChangeSuccess() {
        user u = new user();
        u.setPhone("123");

        when(repo.findByPhone("123")).thenReturn(u);

        boolean result = userService.passwordChange("123", "abc", "abc");

        assertTrue(result);
    }

    @Test
    public void testPasswordChangeFail() {
        boolean result = userService.passwordChange("123", "abc", "xyz");

        assertFalse(result);
    }

    // ===== TEST SEARCH =====
    @Test
    public void testFindUser() {
        when(repo.findByNameContaining("Bao")).thenReturn(new ArrayList<>());

        List<user> list = userService.findUserByName("Bao");

        assertNotNull(list);
    }
}
