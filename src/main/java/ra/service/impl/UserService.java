package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.impl.UserDao;
import ra.dto.request.FormLoginDto;
import ra.dto.request.FormRegisterDto;
import ra.model.User;
import ra.service.IGenericService;

import java.util.List;
@Service
public class UserService  {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }


    public User findById(int id) {
        return userDao.findById(id);
    }


    public void save(FormRegisterDto formRegiterDto) {
        // chuyen doi dto sang model
        User user = new User();
        user.setUsername(formRegiterDto.getUsername());
        user.setPassword(formRegiterDto.getPassword());
        user.setEmail(formRegiterDto.getEmail());
        userDao.save(user);
    }

    public boolean checkUsername(String username) {
      return   userDao.checkUsername(username);
    }
    public boolean checkEmail(String email) {
        return userDao.checkEmail(email);
    }


    public void delete(Long id) {

    }
    public User login(FormLoginDto formLoginDto){
        return userDao.login(formLoginDto);
    }

    public void toggleUserStatus(int id){
        userDao.tongleUserStatus(id);
    }
}
