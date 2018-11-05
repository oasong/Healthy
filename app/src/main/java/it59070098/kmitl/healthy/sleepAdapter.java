package it59070098.kmitl.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class sleepAdapter extends RecyclerView.Adapter<sleepAdapter.ViewHolder> {
    private List<sleepitem> sleepTimes;
    private Context mContext;
    private onItemClickListener onItemClickListener;

    public sleepAdapter(Context context, List<sleepitem> dataset){
        mContext = context;
        sleepTimes = dataset;
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView duration;
        public TextView date;
        public TextView startTime;

        public ViewHolder(View view, final onItemClickListener listener){
            super(view);
            date = view.findViewById(R.id.sleep_date);
            duration = view.findViewById(R.id.sleep_duration);
            startTime = view.findViewById(R.id.sleep_start);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_sleepitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // set data for display
        sleepitem sleepTime = sleepTimes.get(i);
        viewHolder.date.setText(sleepTime.getDateString());
        viewHolder.startTime.setText(sleepTime.getStartTime()+" - "+sleepTime.getEndTime());
        viewHolder.duration.setText(sleepTime.getDuration());
    }

    @Override
    public int getItemCount() {
        return sleepTimes.size();
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.onItemClickListener = listener;
    }


}
