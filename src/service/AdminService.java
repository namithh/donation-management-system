package service;

import dao.AdminDAO;
import model.Admin;

import java.util.List;

public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();

    public boolean addAdmin(Admin admin) {
        return adminDAO.insertAdmin(admin);
    }

    public Admin loginAdmin(String email, String password) {
        return adminDAO.getAdminByEmailAndPassword(email, password);
    }

    public Admin getAdminById(int id) {
        return adminDAO.getAdminById(id);
    }

    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    public boolean updateAdmin(Admin admin) {
        return adminDAO.updateAdmin(admin);
    }

    public boolean deleteAdmin(int id) {
        return adminDAO.deleteAdmin(id);
    }
}
