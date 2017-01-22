package sfu.ca.systemshack2017.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

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
        final ViewHolder cache;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alarm_list_item, parent, false);

            cache = new ViewHolder();
            cache.timeTxt = (TextView) convertView.findViewById(R.id.eventTimeTxt);
            cache.eventNameTxt = (TextView) convertView.findViewById(R.id.eventNameTxt);
            cache.expandableLayout = (ExpandableLayout) convertView.findViewById(R.id.eventExpander);
            cache.expandBtn = (ImageButton) convertView.findViewById(R.id.eventExpanderBtn);
            cache.expandBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cache.expanded) {
                        Log.d("Event list", "collapsing item");
                        cache.expandableLayout.collapse();
                        cache.expandBtn.setImageResource(android.R.drawable.arrow_down_float);
                        cache.expanded = false;
                    } else {
                        Log.d("Event list", "expanding item");
                        cache.expandableLayout.expand();
                        cache.expandBtn.setImageResource(android.R.drawable.arrow_up_float);
                        cache.expanded = true;
                    }
                }
            });
            cache.eventLocationTxt = (TextView) convertView.findViewById(R.id.eventLocationTxt);

            convertView.setTag(cache);
        } else {
            cache = (ViewHolder) convertView.getTag();
        }

        final TextView timeTxt = cache.timeTxt;
        final TextView eventNameTxt = cache.eventNameTxt;
        final ExpandableLayout expandableLayout = cache.expandableLayout;
        final ImageButton expandBtn = cache.expandBtn;
        final TextView eventLocationtxt = cache.eventLocationTxt;

        SimpleDateFormat sf = new SimpleDateFormat("h:mm a");
        timeTxt.setText(sf.format(event.getCalendar().getTime()));
        eventNameTxt.setText(event.getName());
        eventLocationtxt.setText(event.getLocationName());

        return convertView;
    }

    private class ViewHolder {
        TextView timeTxt;
        TextView eventNameTxt;
        TextView eventLocationTxt;
        ImageButton expandBtn;
        ExpandableLayout expandableLayout;
        boolean expanded = false;
    }
}