package com.example.arrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private int selectedPosition = -1;
    private final int defaultColor;
    private final int selectedColor;

    public PersonAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, 0, objects);
        defaultColor = ContextCompat.getColor(context, android.R.color.black);
        selectedColor = ContextCompat.getColor(context, android.R.color.holo_red_dark);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_person, parent, false);

        TextView tvFirstName = itemView.findViewById(R.id.tvFirstName);
        TextView tvLastName = itemView.findViewById(R.id.tvLastName);

        Person person = getItem(position);
        if (person != null) {
            tvFirstName.setText(person.getFirstName());
            tvLastName.setText(person.getLastName());
        }

        // Очень важно: ВСЕГДА явно задаём цвет, и для выбранного, и для невыбранного
        if (position == selectedPosition) tvLastName.setTextColor(selectedColor);
        else tvLastName.setTextColor(defaultColor);

        return itemView;
    }
}
