package food.wilder.gui.profile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import food.wilder.R;
import food.wilder.common.ITripData;
import food.wilder.gui.map.RouteActivity;

public class ProfileForagesRecyclerViewAdapter extends RecyclerView.Adapter<ProfileForagesRecyclerViewAdapter.ViewHolder> {

    private List<ITripData> tripData;

    public ProfileForagesRecyclerViewAdapter(List<ITripData> tripData) {
        this.tripData = tripData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recycler_view_layout, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ITripData trip = tripData.get(position);
        holder.tripId.setText(trip.getId());
        holder.timestamp.setText(String.valueOf(trip.getStartTime()));
        if(position % 2 == 0) {
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.recyclerLayoutEven));
        } else {
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.recyclerLayoutUneven));
        }
    }

    @Override
    public int getItemCount() {
        return tripData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tripId;
        public TextView timestamp;
        public LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);

            tripId = itemView.findViewById(R.id.recyclerLayoutName);
            timestamp = itemView.findViewById(R.id.recyclerLayoutLevel);
            layout = itemView.findViewById(R.id.recyclerLayout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("ONCLICK_PROFILE", "Clicked");
            Intent intent = new Intent(view.getContext(), RouteActivity.class);
            intent.putExtra("trip_id", tripId.getText());
            view.getContext().startActivity(intent);
        }
    }
}
