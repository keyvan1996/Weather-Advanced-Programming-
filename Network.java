package Project_1;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Keyvan Shabani on 2/15/2020.
 **/

public class Network {

    private final OkHttpClient httpClient = new OkHttpClient();

    private static Network network_instance = null;

    public static Network getInstance()
    {
        if (network_instance == null){
            network_instance = new Network();
        }
        return network_instance;
    }

    //get cities information
    public ArrayList<Project_1.City> getCitiesInformation() throws Exception {

        ArrayList<City> citiesWithInformation =  getCitiesLocation();

        for (int i = 0; i < citiesWithInformation.size(); i++) {
            Request request = new Request.Builder().url("https://api.darksky.net/forecast/2fe4a32ecb6fddb9ee1ee884583907e1/"
                    +citiesWithInformation.get(i).getLonglat()).build();

            try (Response response = httpClient.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Get response body
                JSONObject darkSkyRes = new JSONObject(response.body().string());
                JSONObject currently = darkSkyRes.getJSONObject("currently");

                citiesWithInformation.get(i).setIcon(currently.getString("icon"));
                citiesWithInformation.get(i).setTemp(currently.getInt("temperature"));
                citiesWithInformation.get(i).setUVIndex(currently.getInt("uvIndex"));
            }
            System.out.print("%"+(i*100)/50);
        }
        return citiesWithInformation;
    }

    //get cities from file
    private ArrayList<City> getCitiesLocation(){
        ArrayList<City> citiesLocation = new ArrayList<>();

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://gist.githubusercontent.com/tacksoo/07ec0e01122d02f30ef02b3a8418391f/raw/a78acee82835ac9af0b8595651102f16362d0c62/states.csv")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                InputStream in = response.body().byteStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();

                while ((line = reader.readLine()) != null) {
                    City city = new City(line.split(",")[1], line.split(",")[0], line.split(",")[2] + "," + line.split(",")[3]);
                    citiesLocation.add(city);
                }

                response.body().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        citiesLocation.remove(0);
        return  citiesLocation;
    }
}

