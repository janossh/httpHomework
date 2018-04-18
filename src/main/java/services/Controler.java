package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

public class Controler {

    private static final String USER_AGENT = "Mozilla/5.0";

    //Create user
    public static void createUser(User user) {
        String url = "http://petstore.swagger.io/v2/user";

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(user);

        try {
            sendPost(jsonString, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Creates list of users with given input array
    public static void createUserInputArray(User[] arrayOfUsers) {

        String url = "http://petstore.swagger.io/v2/user/createWithArray";

        String jsonString = "[";

        Boolean first = true;

        for (User user : arrayOfUsers) {

            jsonString = jsonString + (first ? "" : ",");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String jsonUser = gson.toJson(user);
            jsonString = jsonString + jsonUser;
            first = false;

        }

        jsonString = jsonString + "]";

        try {
            sendPost(jsonString, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Creates list of users with given input array
    public static void createUserInputList(List<User> userList) {

        String url = "http://petstore.swagger.io/v2//user/createWithList";

        String jsonString = "[";

        Boolean first = true;

        for (User user : userList) {

            jsonString = jsonString + (first ? "" : ",");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String jsonUser = gson.toJson(user);
            first = false;

        }

        jsonString = jsonString + "]";

        try {
            sendPost(jsonString, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Logs user into the system
    public static void logsUser(String username, String password) {
        String url = "http://petstore.swagger.io/v2/user/login";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        try {
            sendGet(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Logs out current logged in user session
    public static void logOutUser() {
        String url = "http://petstore.swagger.io/v2/user/logout";

        Map<String, String> params = new HashMap<>();

        try {
            sendGet(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get user by user name
    public static void getUserByName(String userName) {
        String url = "http://petstore.swagger.io/v2/user/" + userName;

        Map<String, String> params = new HashMap<>();

        try {
            sendGet(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Updated user
    public static void updateUser(String userName, User user) {
        String url = "http://petstore.swagger.io/v2/user" + userName;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(user);

        try {
            sendPut(jsonString, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete user
    public static void deleteUserByName(String userName) {
        String url = "http://petstore.swagger.io/v2/user/" + userName;

        Map<String, String> params = new HashMap<>();

        try {
            sendDelete(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPost(String jsonString, String url) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("accept", "application/xml");
        post.setHeader("Content-Type", "application/json");

        post.setEntity(new StringEntity(jsonString));

        HttpResponse response = client.execute(post);
        System.out.println("Sending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Sending object : " + jsonString);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

    private static void sendPut(String jsonString, String url) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);

        put.setHeader("User-Agent", USER_AGENT);
        put.setHeader("accept", "application/xml");
        put.setHeader("Content-Type", "application/json");

        put.setEntity(new StringEntity(jsonString));

        HttpResponse response = client.execute(put);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + put.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

    private static void sendGet(String url, Map<String, String> params) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        URIBuilder uriBuilder = new URIBuilder(get.getURI());

        Set<Map.Entry<String, String>> set = params.entrySet();

        for (Map.Entry<String, String> me : set) {
            uriBuilder.addParameter(me.getKey(), me.getValue());
        }

        URI uri = uriBuilder.build();

        get.setHeader("accept", "application/xml");

        get.setURI(uri);

        HttpResponse response = client.execute(get);
        System.out.println("\nSend 'GET' request to URL : " + url);
        System.out.println("Get parameters : " + get.getURI());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

    private static void sendDelete(String url, Map<String, String> params) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpDelete delete = new HttpDelete(url);

        URIBuilder uriBuilder = new URIBuilder(delete.getURI());

        Set<Map.Entry<String, String>> set = params.entrySet();

        for (Map.Entry<String, String> me : set) {
            uriBuilder.addParameter(me.getKey(), me.getValue());
        }

        URI uri = uriBuilder.build();

        delete.setHeader("accept", "application/xml");

        delete.setURI(uri);

        HttpResponse response = client.execute(delete);
        System.out.println("\nDelete user from the system : ");
        System.out.println("Delete parameters : " + delete.getURI());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }

}
