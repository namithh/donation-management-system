package controller;

import model.Admin;
import service.AdminService;

public class AdminController {
    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    public boolean addAdmin(Admin admin) {
        return adminService.addAdmin(admin);
    }

    public Admin loginAdmin(String email, String password) {
        return adminService.loginAdmin(email, password);
    }

    public Admin getAdminById(int id) {
        return adminService.getAdminById(id);
    }

    public boolean updateAdmin(Admin admin) {
        return adminService.updateAdmin(admin);
    }

    public boolean deleteAdmin(int id) {
        return adminService.deleteAdmin(id);
    }
}
