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

    public PersonAdapter(@NonNull Context context, @NonNull List<Person> objects) {
        super(context, 0, objects);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_person, parent, false);


        TextView tvFirstName = itemView.findViewById(R.id.tvFirstName);
        TextView tvLastName = itemView.findViewById(R.id.tvLastName);

        Person person = getItem(position);
        if (person != null) {
            tvLastName.setText(person.getLastName());
            tvFirstName.setText(person.getFirstName());
        }

        int defaultColor = ContextCompat.getColor(getContext(), android.R.color.black);
        int selectedColor = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);

        if (position == selectedPosition) tvLastName.setTextColor(selectedColor);
        else tvLastName.setTextColor(defaultColor);

        return itemView;
    }
}
