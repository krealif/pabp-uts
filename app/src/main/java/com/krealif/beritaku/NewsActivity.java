package com.krealif.beritaku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<News> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsAdapter = new NewsAdapter(this, newsList);
        recyclerView = findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Intent intent = getIntent();
        String userCategory = intent.getStringExtra(UserActivity.CATEGORY_KEY);
        int userAge = Integer.parseInt(intent.getStringExtra(UserActivity.AGE_KEY));

        try {
            JSONObject jsonObject = new JSONObject(getJSONFile());
            JSONArray jsonArray = jsonObject.getJSONArray("news");
            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject news = jsonArray.getJSONObject(i);
                String category = news.getString("category");
                int ageRating = news.getInt("ageRating");
                if ((userCategory.equals(category)) && (userAge >= ageRating)) {
                    String id = news.getString("id");
                    String title = news.getString("title");
                    Date published = new SimpleDateFormat("yyyy-MM-dd").parse(news.getString("published"));
                    String body = news.getString("body");
                    newsList.add(new News(id, title, published, category, body, ageRating));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getJSONFile() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("berita.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}