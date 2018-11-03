package dz.cdta.smartbin.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.cdta.smartbin.model.DateConverter;
import dz.cdta.smartbin.model.SmartBin;
import dz.cdta.smartbin.model.Tache;
import dz.cdta.smartbin.model.Task;

@Database(entities = {Task.class}, version = 1)
@TypeConverters({DateConverter.class,SmartBinTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "Room Database";
    private static AppDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static AppDatabase getAppDatabase(final Context context) {
        if (INSTANCE == null) {
            Log.d(TAG,"Null Instance");
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tasksDB")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("TasksDB", "populating with data...");
                                }
                            })
                            .build();

            new FetchSecuredResourceTask(INSTANCE).execute();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    private static class FetchSecuredResourceTask extends AsyncTask<Void, Void, String> {

        private final TaskDao taskDao;

        private FetchSecuredResourceTask(AppDatabase db) {
            this.taskDao = db.taskDao();
        }

        @Override
        protected String doInBackground(Void... voids) {

            final String urlTaches = "http://192.168.0.174:8080" + "/taches";
            final String urlBins = "http://192.168.0.174:8080" + "/smartBins/task/";


            // Create a new RestTemplate instance
            List<dz.cdta.smartbin.model.Task> tasksList = new ArrayList<>();
            /*
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ResponseEntity<List<Tache>> response = restTemplate.exchange(
                    urlTaches,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Tache>>(){});
               */
            /*
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<Tache> taches = new ArrayList<>();

            try {
                URL url = new URL(urlTaches);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                Log.e("Response: ", "> " + line);   //here u ll get whole response...... :-)

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.e("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }

                String jString = buffer.toString();

                Type collectionType = new TypeToken<List<Tache>>() {
                }.getType();
                taches = (List<Tache>) new Gson()
                        .fromJson(jString, collectionType);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e(TAG, " Tasks : " + taches.toString());
            */


            /**************************************/
            /*
            for (Tache t : taches) {

                List<SmartBin> smartBins = new ArrayList<>();
            */
                /*RestTemplate restTemplateBins = new RestTemplate();
                restTemplateBins.getMessageConverters().add(new StringHttpMessageConverter());
                ResponseEntity<List<SmartBin>> responseBins = restTemplateBins.exchange(
                        urlBins + t.getIdTask(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SmartBin>>() {
                        });*/
                /*
                try {
                    URL url = new URL(urlBins+t.getIdTask());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();


                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                        Log.e("Response: ", "> " + line);   //here u ll get whole response...... :-)
                    }

                    String jString = buffer.toString();

                    Type collectionType = new TypeToken<List<SmartBin>>() {
                    }.getType();
                    smartBins = (List<SmartBin>) new Gson()
                            .fromJson(jString, collectionType);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, smartBins.toString());
                tasksList.add(new dz.cdta.smartbin.model.Task(t, smartBins));
            }
            */
            tasksList.add(new Task((long) 1,new Date(),new Date(),"Description",1,null));
            tasksList.add(new Task((long) 2,new Date(),new Date(),"Description",1,null));
            tasksList.add(new Task((long) 3,new Date(),new Date(),"Description",1,null));
            tasksList.add(new Task((long) 4,new Date(),new Date(),"Description",1,null));

            Log.d(TAG,"Size of the list : " + tasksList.size() + tasksList.toString());
            taskDao.insertAll(tasksList);
            System.out.println("Hey" + taskDao.getUpcomingTasks().getValue().size());
            return null;
        }
    }
}