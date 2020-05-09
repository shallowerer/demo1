package com.huaxianvwa.school.service;

import java.util.List;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.UserDao;
import com.huaxianvwa.school.entity.AdminRole;
import com.huaxianvwa.school.entity.User;


/** 
 * 对 UserDAO 进行了二次封装，一般来讲，我们在 DAO 中只定
 * 义基础的增删改查操作，而具体的操作，需要由 Service 来
 * 完成。当然，由于我们做的操作原本就比较简单，所以
 * 这里看起来只是简单地重命名了一下，比如把 “通过用
 * 户名及密码查询并获得对象” 这种方法命名为 get
*/

@Service
public class UserService {
    @Autowired
    UserDao userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    public List<User> list() {
        List<User> users =  userDAO.list();
        List<AdminRole> roles;
        for (User user : users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
       
    	
    	System.out.println(55);
    	 return users;
    }
    public User getById(Integer id){
    	User user = userDAO.getOne(id);
    	return user;
    }

    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null!=user;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void addOrUpdate(User user) {
        userDAO.save(user);
    }
    
    public void deleteById(Integer id){
    	adminUserRoleService.deleteAllByUid(id);
    	userDAO.delete(id);
    }
    public int setSimpleInfo(User user) {
    	User u = userDAO.getOne(user.getId());
    	u.setUsername(user.getUsername());
    	System.out.println(user.getUsername());
        u.setPhone(user.getPhone());
        u.setEmail(user.getEmail());
//        username = HtmlUtils.htmlEscape(username);
//        user.setUsername(username);
//        phone = HtmlUtils.htmlEscape(phone);
//        user.setPhone(phone);
//        email = HtmlUtils.htmlEscape(email);
//        user.setEmail(email);
//        
        if (user.getUsername().equals("")) {
            return 0;
        }

        boolean exist = isExist(user.getUsername());

		try {
			 userDAO.save(u);
		} catch (Exception e) {
			return 2;
		}
		return 1;
	}
    


    public int register(User user) {
        String username = user.getUsername();
        String name = user.getTruename();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setTruename(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userDAO.save(user);

        return 1;
    }

    public boolean updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        try {
            userDAO.save(userInDB);
        } catch (IllegalArgumentException e) {
            return false;
        } return true;
    }
    
    public boolean setPassword(User user) {
        User userInDB = userDAO.getOne(user.getId());
        String password = user.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        userInDB.setPassword(encodedPassword);
        try {
            userDAO.save(userInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        try {
            userDAO.save(userInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean editUser(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setTruename(user.getTruename());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        try {
            userDAO.save(userInDB);
            adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
}