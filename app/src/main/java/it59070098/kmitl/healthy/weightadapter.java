package it59070098.kmitl.healthy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class weightadapter extends ArrayAdapter<Weight>{
    List<Weight> weights = new ArrayList<Weight>();
    Context context;

    public weightadapter(Context context, int resource, List<Weight> objects){
        super(context, resource, objects);
        this.weights = objects;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View weightitem = LayoutInflater.from(context).inflate(
                R.layout.addweightmini,parent,false);

        TextView date = weightitem.findViewById(R.id.date_mini);
        TextView weight = weightitem.findViewById(R.id.weight_mini);

        Weight row = weights.get(position);
        date.setText(row.getDate());
        weight.setText(row.getWeight() + "kg");

        return weightitem;
    }
}
