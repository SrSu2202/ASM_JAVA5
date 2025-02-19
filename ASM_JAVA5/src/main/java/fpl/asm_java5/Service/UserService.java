package fpl.asm_java5.Service;

import fpl.asm_java5.Beans.UserBean;
import fpl.asm_java5.Entities.User;
import fpl.asm_java5.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    ImageService imageService;

    @Autowired
    UserJPA userJPA;

    public String insert(UserBean userBean) {
        try{
            List<User> users = userJPA.findByUsernameOrEmail(userBean.getUsername(), userBean.getEmail());
            if(users.size() > 0){
                return "Username hoặc email đã tồn tại trong hệ thống";
            }
            String fileName = imageService.saveImage(userBean.getAvatar());
            if(fileName == null){
                return "Avatar lỗi";
            }
            User user = new User();
            user.setUsername(userBean.getUsername());
            user.setEmail(userBean.getEmail());
            user.setName(userBean.getName());
            user.setActive(true);
            user.setPassword(userBean.getPassword());
            user.setAvatar(fileName);
            userJPA.save(user);
        } catch (Exception e){
            return "Khi them user da xay ra loi";
        }
        return null;
    }
    public String update(UserBean userBean) {
        try{
            List<User> users = userJPA.findByUsernameOrEmailAndId(userBean.getUsername(), userBean.getEmail(), userBean.getId());
            if(users.size() > 0){
                return "Username hoặc email đã tồn tại trong hệ thống";
            }
            String fileName = imageService.saveImage(userBean.getAvatar());
            if(fileName == null){
                return "Avatar lỗi";
            }
            User user = new User();
            user.setId(userBean.getId());
            user.setUsername(userBean.getUsername());
            user.setEmail(userBean.getEmail());
            user.setName(userBean.getName());
            user.setActive(true);
            user.setPassword(userBean.getPassword());
            user.setAvatar(fileName);
            userJPA.save(user);

        } catch (Exception e){
            return "Khi cập nhật sản phẩm đã xảy ra lỗi";
        }
        return null;
    }
    public Boolean deleteUser(int id) {
        try {
            Optional<User> user = userJPA.findById(id);
            if(!user.isPresent()){
                return false;
            }
            userJPA.delete(user.get());

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
