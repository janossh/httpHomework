import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import services.Controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RunMe {


    public static void main(String[] args) {

        User user = new User();
        user.setUserStatus(0);
        user.setUsername("Doggy0");
        user.setPhone("0974980000");
        user.setPassword("000000");
        user.setEmail("j000.ssh@gmail.com");
        user.setLastName("QuinDog0");
        user.setFirstName("Bobby0");
        user.setId(123);

        User user1 = new User();
        user1.setUserStatus(0);
        user1.setUsername("Doggy1");
        user1.setPhone("0974981111");
        user1.setPassword("111111");
        user1.setEmail("j111.ssh@gmail.com");
        user1.setLastName("QuinDog1");
        user1.setFirstName("Bobby1");
        user1.setId(123);

        User user2 = new User();
        user2.setUserStatus(0);
        user2.setUsername("Doggy2");
        user2.setPhone("0974982222");
        user2.setPassword("222222");
        user2.setEmail("j222@gmail.com");
        user2.setLastName("QuinDog2");
        user2.setFirstName("Bobby2");
        user2.setId(123);

        //Create user
        //Creates list of users with given input array
        //Creates list of users with given input array
        //Logs user into the system
        //Logs out current logged in user session
        //Get user by user name
        //Updated user
        //Delete user


        try {
            System.out.println("Отправить запрос:\n" +
                    "1. Create user;\n" +
                    "2. Creates list of users with given input array;\n" +
                    "3. Creates list of users with given input array;\n" +
                    "4. Logs user into the system;\n" +
                    "5. Logs out current logged in user session;\n" +
                    "6. Get user by user name;\n" +
                    "7. Updated user;\n" +
                    "8. Delete user;\n" +
                    "9. Exit\n");

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String flEnd = "exit";
            String str;

            System.out.println("Введите номер операции и нажмите ENTER");
            try {
                while (!flEnd.equals(str = bufferedReader.readLine())) {

                    switch (str) {
                        default:
                            System.out.println("Нет такой операции");
                            break;
                        case "1":


                            Controler.createUser(user);
                            break;
                        case "2":
                            User[] users = {user1, user2};
                            Controler.createUserInputArray(users);
                            break;
                        case "3":
                            List<User> userList = new ArrayList<>();
                            userList.add(user1);
                            userList.add(user2);
                            Controler.createUserInputList(userList);
                            break;
                        case "4":
                            String password = getPassword(bufferedReader);
                            String userName = getUserName(bufferedReader);

                            if (!password.isEmpty() && !userName.isEmpty()) {
                                Controler.logsUser(userName, password);
                            } else {
                                System.out.println("Empty user or pass");
                            }

                            break;
                        case "5":
                            Controler.logOutUser();
                            break;
                        case "6":
                            String userName1 = getUserName(bufferedReader);
                            Controler.getUserByName(userName1);
                            break;
                        case "7":
                            String userName7 = getUserName(bufferedReader);
                            user.setPhone("00000000000000");
                            Controler.updateUser(userName7, user);
                            break;
                        case "8":
                            String userName8 = getUserName(bufferedReader);
                            Controler.deleteUserByName(userName8);
                            break;
                        case "9":
                            str = flEnd;
                            break;

                    }
                    if (Objects.equals(str, flEnd)) {
                        System.out.println("Работа программы завершена");
                        break;
                    }
                    System.out.println("Введите номер операции и нажмите ENTER");
                }
            } catch (IOException e) {
                System.out.println("Error read from keyboard");
            }

            try {
                inputStreamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error to close stream reader");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getUserName(BufferedReader bufferedReader) {
        String userName = "";

        System.out.println("Enter user name");

        try {
            userName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userName;
    }

    private static String getPassword(BufferedReader bufferedReader) {
        String pass = "";

        System.out.println("Enter password");

        try {
            pass = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pass;
    }

}
