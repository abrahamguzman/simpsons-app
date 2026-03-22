package com.dev.simpsonapi;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dev.simpsonapi.dao.EpisodeDao;
import com.dev.simpsonapi.db.AppDatabase;
import com.dev.simpsonapi.db.Episode;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EpisodeActivity extends AppCompatActivity {

    EditText etAirDate;
    EditText etEpisodeNumber;
    EditText etName;
    EditText etSeason;
    EditText etSynopsis;
    ListView listView;
    AppDatabase db;
    EpisodeDao episodeDao;
    Button btnSaveData;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_episode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getDatabase(this);
        episodeDao = db.episodeDao();

        etAirDate = findViewById(R.id.etAirDate);
        etEpisodeNumber = findViewById(R.id.etEpisodeNumber);
        etName = findViewById(R.id.etName);
        etSeason = findViewById(R.id.etSeason);
        etSynopsis = findViewById(R.id.etSynopsis);
        listView = findViewById(R.id.list_episodes_data);

        seedEpisodesIfEmpty();
        loadEpisodes();

        btnSaveData = findViewById(R.id.btnSaveData);
        btnSaveData.setOnClickListener(v -> saveEpisodeFromForm());
    }

    private void seedEpisodesIfEmpty() {
        executor.execute(() -> {
            if (!episodeDao.getAll().isEmpty()) {
                return;
            }
            db.episodeDao().insert(new Episode("December 17, 1989", 13, "Simpsons Roasting on an Open Fire", 2, "The Simpsons' first Christmas special."));
            db.episodeDao().insert(new Episode("January 14, 1990", 1, "Bart the Genius", 2, "Bart is sent to a school for gifted children."));
        });
    }

    private void saveEpisodeFromForm() {
        String airDate = etAirDate.getText().toString();
        int episodeNumber = Integer.parseInt(etEpisodeNumber.getText().toString());
        String name = etName.getText().toString();
        int season = Integer.parseInt(etSeason.getText().toString());
        String synopsis = etSynopsis.getText().toString();

        Episode episode = new Episode(airDate, episodeNumber, name, season, synopsis);

        executor.execute(() -> {
            episodeDao.insert(episode);
            runOnUiThread(this::loadEpisodes);
        });
    }

    private void loadEpisodes() {
        executor.execute(() -> {
            ArrayList<Episode> episodes = (ArrayList<Episode>) episodeDao.getAll();

            runOnUiThread(() -> {
                ArrayList<String> dataList = new ArrayList<>();
                for (Episode episode : episodes) {
                    dataList.add("S" + episode.season + "E" + episode.episodeNumber + ": " + episode.name + " (" + episode.airDate + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
            });
        });
    }
}