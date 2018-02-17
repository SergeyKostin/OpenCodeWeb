package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@SpringBootApplication
public class Application {
    @Autowired
    UsersRepository usersRepository;    //интерфейс для работы с бд
    Users user;
    List<String> list_attempt=new ArrayList<String>();    //список предыдущих попыток
    int [] number;     //число загаданное компьютером
    double ret;    //рейтинг

    public static void main(String [] args){
        SpringApplication.run(Application.class,args);   //запускаем приложение

    }

    @RequestMapping("/")
    public String index(){     //метод по запросу "/" открывает главную начальную страницу
        this.generateNumber();  //вызываем метод генерацие числа
        return "index";   //возвращаем название страницы которую хотим отправить
    }
    @RequestMapping(value = "/newGame", method = RequestMethod.POST)
    public String newGame(Map<String, Object> map){      //метод перехода на новую игру
        this.generateNumber();   //вызываем метод генерации числа
        this.list_attempt.clear();
        this.user.setCount(user.getCount()+1);   //увеличиваем количество игр у пользователя
        this.usersRepository.save(user);  //изменяем счетчик количества игр пользователя в бд
        String str="";
        map.put("user",user);    //отправляем на страницу данные
        map.put("rez",str);
        map.put("list_attempt",list_attempt);
        map.put("ret",ret);
        return "hello";   //название страницы которую хотим открыть по заданному запросу
    }

    @RequestMapping(value = "/control", method = RequestMethod.POST)      //метод получает число которое ввел пользователь проверяет с загаданным и возвращает результат
    public String control(Map<String, Object> map, @RequestParam("0") String str0,@RequestParam("1") String str1,@RequestParam("2") String str2,@RequestParam("3") String str3){
        int [] user_number=new int[4];      //создаем массив чисел для хранения числа которое ввель пользоваетль
        user_number[0]=Integer.parseInt(str0);  //заполняем массив
        user_number[1]=Integer.parseInt(str1);
        user_number[2]=Integer.parseInt(str2);
        user_number[3]=Integer.parseInt(str3);
        String rez=null;
        int b=0;
        int k=0;
        for(int i=0;i<number.length;i++){
            if(user_number[i]==number[i])    //проверяем есть ли совпаление цифра на позиции если есть то увеличиваем счетчик быка
                b++;
            for(int j=0;j<user_number.length;j++){
                if(i!=j)
                    if(user_number[j]==number[i]){   //проверяем есть ли совпадения цифры не на позиции есл есть увеличиваем счетчик коровы
                    k++;
                    }
            }
        }
        rez=String.valueOf(b)+"b"+String.valueOf(k)+"k";   // формируем лезльтат
        String attempt=str0+str1+str2+str3+"----"+b+"b"+k+"k";    //формируем попытку
        this.list_attempt.add(attempt);   //добавляем новую попытку в список попыток
        this.ret=this.user.getRating();
        if(number[0]==user_number[0] && number[1]==user_number[1] && number[2]==user_number[2]&& number[3]==user_number[3]){
            ret+=list_attempt.size();   //если число угаданно формируем рейтинг как количество ходов
            if(user.getCount()>1)
            ret=ret/2;         //если игра не первая вычисляем среднее значение как предыдущий рейтинг плюс новый деленый попалам
            this.user.setRating(ret);
            usersRepository.save(this.user);  //изменяем рейтинг в бд
        }
        map.put("user",user);   //отправляем на страницу данные
        map.put("rez",rez);
        map.put("list_attempt",list_attempt);
        map.put("ret",ret);
        return "hello";   //возвращаем название страницы которую хотим открыть
    }
    @RequestMapping(value = "/get_registration", method = RequestMethod.POST)
    public String get_registration(){
        return "addUser";
    }   //метод возвращает страницу регистрации

    @RequestMapping(value = "/get_login", method = RequestMethod.POST)
    public String get_login(){
        return "login";
    }  //метод возвращет страницу входа

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)  //метод добавляет нового пользователя
    public String addUser(Map<String, Object> map, @RequestParam("login") String login,@RequestParam("pass") String pass){
        this.list_attempt.clear();
        byte [] password=pass.getBytes();   //получаем пароль со страницы типа String, преобразуем в массив байтов для безопасного хранения в бд
        Users user=new Users();    //создаем объект пользователя и заполняем его поля
        user.setLogin(login);
        user.setPassword(password);
        user.setRating(0);
        user.setCount(1);
        usersRepository.save(user);   //сохраняем в базе данных
        this.user=user;
        map.put("user",user);     //отправляем данные на страницу
        String rez="";
        map.put("rez",rez);
        map.put("list_attempt",list_attempt);
        map.put("ret",user.getRating());
        return "hello";  //возвращаем название страницы которую открываем
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)    //метод для входа в систему
    public String login(Map<String, Object> map, @RequestParam("login") String login, @RequestParam("pass") String pass){
        this.list_attempt.clear();
        byte [] password=pass.getBytes();
        Users user=usersRepository.findByLoginAndPassword(login,password);   //получаем пользователя по логину и пароля из бд
        int newCount=user.getCount()+1;    //увеличиваем счетчик количества игр
        user.setCount(newCount);
        this.user=user;
        usersRepository.save(user);   //изменеям значение счетчика в бд
        map.put("user",user);  //отправляем данные на страницу
        String rez="";
        map.put("rez",rez);
        map.put("list_attempt",list_attempt);
        map.put("ret",user.getRating());
        return "hello";  //возвращаем название страницы которую хотим открыть
    }
     void generateNumber(){  //метод генерирует случйное четырезначное число
        this.number=new int[4];
        Random random=new Random();
        for(int i=0;i<number.length;i++){
            number[i]=random.nextInt(10);
            if(i>0){
                for(int j=0;j<i+1;j++)
                    if(i!=j)
                while(number[i]==number[j]){   //проверяем есть ли совпадающие цифры в числе если есть то генирируем заново
                number[i]=random.nextInt(10);}
            }
        }
     }
}
