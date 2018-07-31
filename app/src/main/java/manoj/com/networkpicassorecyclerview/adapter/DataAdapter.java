package manoj.com.networkpicassorecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import manoj.com.networkpicassorecyclerview.R;
import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import manoj.com.networkpicassorecyclerview.model.RowsItem;

/**
 * Created by Manoj.Kumar04 on 7/27/2018.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private AboutCountryResponse countryResponses;
    private Context context;
    private List<RowsItem> rowsItems;

    /**
     * Instantiates a new Data adapter.
     *
     * @param context         the context
     * @param countryResponse the country response
     */
    public DataAdapter(Context context, AboutCountryResponse countryResponse) {
        this.context = context;
        this.countryResponses = countryResponse;
        this.rowsItems = countryResponse.getRows();
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_header.setText(rowsItems.get(i).getTitle());
        viewHolder.tv_desc.setText(rowsItems.get(i).getDescription());
//        Picasso.with(context).load(android_versions.get(i).getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);
        Picasso.with(context).load(rowsItems.get(i).getImageHref()).resize(60, 40).placeholder(R.drawable.error).into(viewHolder.img_item);
    }

    @Override
    public int getItemCount() {
        return rowsItems.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Tv header.
         */
        TextView tv_header;
        /**
         * The Tv desc.
         */
        TextView tv_desc;
        /**
         * The Img item.
         */
        ImageView img_item;

        /**
         * Instantiates a new View holder.
         *
         * @param view the view
         */
        public ViewHolder(View view) {
            super(view);

            tv_header = (TextView) view.findViewById(R.id.label_header);
            tv_desc = (TextView) view.findViewById(R.id.label_description);
            img_item = (ImageView) view.findViewById(R.id.image_desc);
        }
    }
}