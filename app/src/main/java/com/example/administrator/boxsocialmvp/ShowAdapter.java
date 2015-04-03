package com.example.administrator.boxsocialmvp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.boxsocialmvp.Adapters.SocialAdapter;
import com.example.administrator.boxsocialmvp.Objects.Image;
import com.example.administrator.boxsocialmvp.Utils.IntentUtils;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2/21/2015.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewholder> {

    private final LayoutInflater inflator;
    private final TvLauncherActivity context;
    List<TvCard> tvCards = Collections.emptyList();
    List<Image> images = Collections.emptyList();

    String IMG_ENDPOINT = "http://image.tmdb.org/t/p/w500";

    public ShowAdapter(TvLauncherActivity context, List<TvCard> tvCards) {
        inflator = LayoutInflater.from(context);
        this.context = context;
        this.tvCards = tvCards;
    }



    @Override
    public MyViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflator.inflate(R.layout.show_list_row,viewGroup,false);
        return new MyViewholder(view);
    }



    @Override
    public void onBindViewHolder(MyViewholder myViewholder, int position) {
        final TvCard current = tvCards.get(position);
        myViewholder.showtitle.setText(current.showTitle);

        if(current.network!=null && !current.network.equalsIgnoreCase(""))
        myViewholder.showNetwork.setText(current.network);
        else
        myViewholder.showNetwork.setText("");

        if(current.showTime!=null && !current.network.equalsIgnoreCase("")) {
//            myViewholder.showNetwork.setText(current.network + " " + current.showTime);
            myViewholder.showtime.setText(current.showTime);

        }

        myViewholder.chatter.setText(current.chatter);
        myViewholder.chatter.setTextColor(Color.parseColor("#FFA500"));
        Log.e("SHOWADAPTER", "ImgUrl:" + current.previewImg);
        if(current.previewImg!=null && !current.previewImg.contains("http:")) {
            Picasso.with(context).load(IMG_ENDPOINT + current.previewImg).error(R.drawable.ic_book_black_48dp).into(myViewholder.showImg);
            Picasso.with(context).load(IMG_ENDPOINT + current.bannerImg).into(myViewholder.bannerImg);
        }else if(current.previewImg!=null && current.previewImg.contains("http:")){
            Picasso.with(context).load(current.previewImg).error(R.drawable.ic_book_black_48dp).into(myViewholder.showImg);
            Picasso.with(context).load(current.bannerImg).into(myViewholder.bannerImg);

        }
        myViewholder.chatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SocialActivity.class);
                intent.putExtra("show",current);
                IntentUtils.startPreviewActivity(context, intent);
//                context.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });

    }


    @Override
    public int getItemCount() {
        return tvCards.size();
    }

    protected class MyViewholder extends RecyclerView.ViewHolder{
        TextView showtitle;
        TextView showtime;
        TextView showNetwork;
        TextView chatter;
        ImageView showImg;
        ImageView bannerImg;

        public MyViewholder(View itemView) {
            super(itemView);
            showtime = (TextView) itemView.findViewById(R.id.showTime);
            showtitle = (TextView) itemView.findViewById(R.id.showTitle);
            showNetwork = (TextView) itemView.findViewById(R.id.showNetwork);
            chatter = (TextView) itemView.findViewById(R.id.chatter);
            showImg = (ImageView) itemView.findViewById(R.id.showImg);
            bannerImg = (ImageView) itemView.findViewById(R.id.bannerImg);

        }
    }
}
