package com.dev.simpsonapi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simpsonapi.dao.EpisodeDao;
import com.dev.simpsonapi.db.AppDatabase;
import com.dev.simpsonapi.db.Episode;
import com.dev.simpsonapi.ui.EpisodeAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EpisodeActivity extends AppCompatActivity {

    EditText etAirDate;
    EditText etEpisodeNumber;
    EditText etName;
    EditText etSeason;
    EditText etSynopsis;
    AppDatabase db;
    EpisodeDao episodeDao;
    Button btnSaveData;
    Button btnDeleteData;

    private RecyclerView recyclerView;
    private EpisodeAdapter episodeAdapter;

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

        setupRecyclerView();

        initViews();
        initObservers();

        btnSaveData = findViewById(R.id.btnSaveData);
        btnSaveData.setOnClickListener(v -> saveEpisodeFromForm());

        btnDeleteData = findViewById(R.id.btnDeleteData);
        btnDeleteData.setOnClickListener(v -> deleteEpisode());
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
        });
    }

/*    private void loadEpisodes() {
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
    }*/

    private void initViews() {
        etAirDate = findViewById(R.id.etAirDate);
        etEpisodeNumber = findViewById(R.id.etEpisodeNumber);
        etName = findViewById(R.id.etName);
        etSeason = findViewById(R.id.etSeason);
        etSynopsis = findViewById(R.id.etSynopsis);
    }

    private void initObservers() {
        episodeDao.getAll().observe(this, episodes -> {
            episodeAdapter.setEpisodes(episodes);
        });
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rvEpisodes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodeAdapter = new EpisodeAdapter();
        recyclerView.setAdapter(episodeAdapter);
    }

    private void deleteEpisode() {
        executor.execute(() -> {
            episodeDao.deleteAll();
        });
    }
}
