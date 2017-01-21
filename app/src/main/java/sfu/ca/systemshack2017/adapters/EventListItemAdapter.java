package sfu.ca.systemshack2017.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import sfu.ca.systemshack2017.Event;
import sfu.ca.systemshack2017.R;

/**
 * Created by brandon on 1/21/2017.
 */
public class EventListItemAdapter extends ArrayAdapter<Event> {
    public EventListItemAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alarm_list_item, parent, false);
        }
        // Lookup view for data population
        TextView timeTxt = (TextView) convertView.findViewById(R.id.eventTimeTxt);
        TextView nameTxt = (TextView) convertView.findViewById(R.id.eventNameTxt);
        // Populate the data into the template view using the data object
//        tvName.setText(event.name);
//        tvHome.setText(event.hometown);
        // Return the completed view to render on screen

        ImageButton expandBtn = (ImageButton) convertView.findViewById(R.id.eventExpanderBtn);
        return convertView;
    }
}