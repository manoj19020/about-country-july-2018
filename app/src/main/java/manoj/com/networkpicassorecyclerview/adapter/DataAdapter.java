package manoj.com.networkpicassorecyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import manoj.com.networkpicassorecyclerview.R;
import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import manoj.com.networkpicassorecyclerview.model.RowsItem;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;

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
        Glide.with(context).load(rowsItems.get(i).getImageHref())
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,100))
                .format(PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(viewHolder.img_item);
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
        @BindView(R.id.label_header)
        TextView tv_header;
        /**
         * The Tv desc.
         */
        @BindView(R.id.label_description)
        TextView tv_desc;
        /**
         * The Img item.
         */
        @BindView(R.id.image_desc)
        ImageView img_item;

        /**
         * Instantiates a new View holder.
         *
         * @param view the view
         */
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}