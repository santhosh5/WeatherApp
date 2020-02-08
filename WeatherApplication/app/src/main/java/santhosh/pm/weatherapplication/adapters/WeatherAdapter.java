package santhosh.pm.weatherapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import santhosh.pm.weatherapplication.R;
import santhosh.pm.weatherapplication.models.WeatherData;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private Context context;
    private List<WeatherData> weatherDetailsList;

    public WeatherAdapter (Context context, List<WeatherData> weatherDetailsList) {
        this.context = context;
        this.weatherDetailsList = weatherDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_row, parent, false);
        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder ( @NonNull ViewHolder holder, int position ) {
        WeatherData weatherDetails = weatherDetailsList.get(position);
       if(weatherDetails != null) {
           holder.tvCityName.setText(weatherDetails.getCityName());
           holder.tvTemp.setText(weatherDetails.getMinTemp()+"°"+" / "+weatherDetails.getMaxTemp()+"°");
           holder.ivCloud.setImageResource(R.drawable.cloudsunny);
       }
    }


    @Override
    public int getItemCount ( ) {
        return weatherDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCityName;
        TextView tvTemp;
        ImageView ivCloud;

        public ViewHolder ( @NonNull View itemView ) {
            super(itemView);

            tvCityName = itemView.findViewById(R.id.tv_cityname);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            ivCloud = itemView.findViewById(R.id.iv_cloud);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick ( View view ) {
                        ItemClickI clickI = (ItemClickI) context;
                        clickI.itemClick(weatherDetailsList.get(getAdapterPosition()));
                    }
                });
        }
    }

    public interface ItemClickI {
        void itemClick(WeatherData data);
    }
}
