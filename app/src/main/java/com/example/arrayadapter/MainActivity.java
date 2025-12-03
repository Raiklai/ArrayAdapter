package com.example.arrayadapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPeople;
    private Button btnAddRandom;
    private Button btnAddTyped;
    private EditText etLastName;
    private EditText etFirstName;


    private PersonAdapter adapter;
    private List<Person> people = new ArrayList<>();
    private Random random = new Random();

    private String[] lastNames;
    private String[] firstNames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPeople = findViewById(R.id.listViewPeople);
        btnAddRandom = findViewById(R.id.btnAddRandom);
        btnAddTyped = findViewById(R.id.btnAddTyped);
        etLastName = findViewById(R.id.etLastName);
        etFirstName = findViewById(R.id.etFirstName);

        lastNames = getResources().getStringArray(R.array.last_names);
        firstNames = getResources().getStringArray(R.array.first_names);

        for (int i = 0; i < 30; i++) addRandomPerson(false);

        adapter = new PersonAdapter(this, people);
        listViewPeople.setAdapter(adapter);

        listViewPeople.setSelector(android.R.color.transparent);

        btnAddRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRandomPerson(true);
                listViewPeople.smoothScrollToPosition(people.size() - 1);
            }
        });

        btnAddTyped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = etFirstName.getText().toString().trim();
                String last = etLastName.getText().toString().trim();

                if (!TextUtils.isEmpty(last) && !TextUtils.isEmpty(first)) {
                    people.add(new Person(last, first));
                    adapter.notifyDataSetChanged();
                    listViewPeople.smoothScrollToPosition(people.size() - 1);

                    etFirstName.setText("");
                    etLastName.setText("");
                }
            }
        });

        listViewPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPosition(position);
            }
        });
    }

    private void addRandomPerson(boolean needNotify) {
        int firstIndex = random.nextInt(firstNames.length);
        int lastIndex = random.nextInt(lastNames.length);

        String first = firstNames[firstIndex];
        String last = lastNames[lastIndex];

        people.add(new Person(last, first));

        if (needNotify && adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}