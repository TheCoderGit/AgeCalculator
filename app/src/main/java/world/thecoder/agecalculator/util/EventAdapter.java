package world.thecoder.agecalculator.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import world.thecoder.agecalculator.R;
import world.thecoder.agecalculator.models.Event;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(@NonNull Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Event event = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_item,parent,false);
        }

        TextView tvYear = convertView.findViewById(R.id.textView_event_list_item_year);
        TextView tvText = convertView.findViewById(R.id.textView_event_list_item_text);

        tvYear.setText(event.year);
        tvText.setText(event.text);

        return convertView;
    }
}
