package ua.ho.andro.preciousmetalstracker.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ua.ho.andro.preciousmetalstracker.DataMetalList;
import ua.ho.andro.preciousmetalstracker.R;


public class MLAdapter extends RecyclerView.Adapter<MLAdapter.ViewHolder> {
    private DataMetalList[] Items;
    private Context Context;

    public MLAdapter(Context context, DataMetalList[] item) {
        this.Items = item;
        this.Context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView metalName, metalVol, metalCoast;
        public ImageView metalImage, metalTrend;
        public CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            metalImage = (ImageView) itemView.findViewById(R.id.metal_photo);
            metalName = (TextView) itemView.findViewById(R.id.metal_name);
            metalTrend = (ImageView) itemView.findViewById(R.id.trend);
            metalCoast = (TextView) itemView.findViewById(R.id.metal_coast);
            metalVol = (TextView) itemView.findViewById(R.id.metal_volue);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Context);
        View v = inflater.inflate(R.layout.card_metal, null);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataMetalList item = Items[position];
        holder.cv.findViewById(R.id.cv);
        holder.metalImage.setImageResource(item.dataPhotoId);
        holder.metalName.setText(item.dataMetalName);
        holder.metalTrend.setImageResource(item.dataTrendId);
        holder.metalCoast.setText(item.dataMetalCoast);
        holder.metalVol.setText(item.dataMetalVol);
    }

    @Override
    public int getItemCount() {
        return Items.length;
    }
}