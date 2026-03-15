package com.dev.simpsonapi;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dev.simpsonapi.dao.CharacterDao;
import com.dev.simpsonapi.db.Character;
import com.dev.simpsonapi.db.DbHelper;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {
    DbHelper dbHelper;
    CharacterDao characterDao;
    ListView listView;
    EditText etId;
    EditText etName;
    EditText etAge;
    EditText etBirthdate;
    EditText etImage;
    EditText etOccupation;
    EditText etStatus;
    Button btnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_characters);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);
        characterDao = new CharacterDao(dbHelper);

        listView = findViewById(R.id.list_characters_data);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etBirthdate = findViewById(R.id.etBirthdate);
        etImage = findViewById(R.id.etImage);
        etOccupation = findViewById(R.id.etOccupation);
        etStatus = findViewById(R.id.etStatus);
        btnSaveData = findViewById(R.id.btnSaveData);

        seedCharactersIfEmpty();
        loadCharacters();

        btnSaveData.setOnClickListener(v -> saveCharacterFromForm());
    }

    private void seedCharactersIfEmpty() {
        if (!characterDao.getAll().isEmpty()) {
            return;
        }

        Character homero = new Character(1, "Homer Simpson", 39, "1956-05-12", "homer.png", "Safety Inspector", "Active");
        Character marge = new Character(2, "Marge Simpson", 36, "1956-10-01", "marge.png", "Housewife", "Active");
        Character bart = new Character(3, "Bart Simpson", 10, "1980-02-23", "bart.png", "Student", "Active");
        Character lisa = new Character(4, "Lisa Simpson", 8, "1982-05-09", "lisa.png", "Student", "Active");
        Character maggie = new Character(5, "Maggie Simpson", 1, "1989-04-19", "maggie.png", "Baby", "Active");

        characterDao.insert(homero);
        characterDao.insert(marge);
        characterDao.insert(bart);
        characterDao.insert(lisa);
        characterDao.insert(maggie);
    }

    private void saveCharacterFromForm() {
        String idText = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String ageText = etAge.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();
        String image = etImage.getText().toString().trim();
        String occupation = etOccupation.getText().toString().trim();
        String status = etStatus.getText().toString().trim();

        if (TextUtils.isEmpty(idText) || TextUtils.isEmpty(name) || TextUtils.isEmpty(ageText)
                || TextUtils.isEmpty(birthdate) || TextUtils.isEmpty(image)
                || TextUtils.isEmpty(occupation) || TextUtils.isEmpty(status)) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            int age = Integer.parseInt(ageText);
            Character character = new Character(id, name, age, birthdate, image, occupation, status);
            characterDao.insert(character);
            loadCharacters();
            clearForm();
            Toast.makeText(this, "Personaje guardado", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Id y edad deben ser numericos", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        etId.setText("");
        etName.setText("");
        etAge.setText("");
        etBirthdate.setText("");
        etImage.setText("");
        etOccupation.setText("");
        etStatus.setText("");
    }

    private void loadCharacters() {
        ArrayList<Character> characters = characterDao.getAll();
        ArrayList<String> dataList = new ArrayList<>();
        for (Character character : characters) {
            dataList.add(character.getName() + " - " + character.getOccupation() + " - " + character.getAge());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}