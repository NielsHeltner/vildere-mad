package food.wilder.gui.leaderboard;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import food.wilder.R;
import food.wilder.domain.UserData;

public class LeaderboardRecyclerViewAdapter extends RecyclerView.Adapter<LeaderboardRecyclerViewAdapter.ViewHolder> {

    private List<UserData> userData;

    public LeaderboardRecyclerViewAdapter(List<UserData> userData) {
        this.userData = userData;
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
        UserData user = userData.get(position);
        holder.name.setText(user.getName());
        holder.level.setText(String.valueOf(user.getLevel()));
        if(position % 2 == 0) {
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.recyclerLayoutEven));
        } else {
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.recyclerLayoutUneven));
        }
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView level;
        public LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recyclerLayoutName);
            level = itemView.findViewById(R.id.recyclerLayoutLevel);
            layout = itemView.findViewById(R.id.recyclerLayout);
        }
    }
}
