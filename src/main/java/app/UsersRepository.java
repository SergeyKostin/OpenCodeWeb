package app;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository extends CrudRepository<Users, Long> {
    public Users findByLoginAndPassword(String login, byte[] password);  //метод возвращает из бд объект пользователя по его логину и паролю

}
