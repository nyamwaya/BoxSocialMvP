package com.example.administrator.boxsocialmvp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.boxsocialmvp.R;
import com.example.administrator.boxsocialmvp.ShowAdapter;
import com.example.administrator.boxsocialmvp.TvCard;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2/27/2015.
 */
public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewholder> {

    private final String TAG = this.getClass().getSimpleName();
    private final LayoutInflater inflator;
    String imgPath;
    TvCard showInfo;
    private boolean isTopLayout;
    private boolean isNormalLayout;
    private boolean isSecondLayout;
    private Context context;
    String IMG_ENDPOINT = "http://image.tmdb.org/t/p/w500";


    public SocialAdapter(Context context, TvCard showInfo) {
        inflator = LayoutInflater.from(context);
        this.showInfo = showInfo;
        this.imgPath = showInfo.bannerImg;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        int viewType = 0;
       switch (position){
           case 0:
               viewType = 0;
               break;
           case 1:
               viewType = 1;
               break;
           case 2:
               viewType = 2;
               break;
       }

        return position;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        MyViewholder holder;

        switch (viewType){
            case 0:
                isTopLayout = true;
                isNormalLayout = false;
                isSecondLayout = false;
                view = inflator.inflate(R.layout.show_img_layout,parent,false);
                holder = new MyTopItem(view);
                break;
            case 1:
                isSecondLayout = true;
                isTopLayout = false;
                isNormalLayout = false;
                view = inflator.inflate(R.layout.social_filter_buttons,parent,false);
                holder = new MyViewholder(view);
                break;
            default:
                isTopLayout = false;
                isSecondLayout = false;
                isNormalLayout = true;
                view = inflator.inflate(R.layout.social_network_layout,parent,false);
                holder = new MySocialHolder(view);
                break;

        }


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        if(position==0){
            Picasso.with(context).load(IMG_ENDPOINT +imgPath).error(R.drawable.ic_book_black_48dp).into(holder.bannerImage);
        }


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        protected TextView nearMe;
        protected TextView all;
        protected TextView friends;
        protected ImageView bannerImage;
        TextView twitterHandle;
        TextView tweet;
        ImageView profileImg;

        public MyViewholder(View itemView) {
            super(itemView);
         nearMe = (TextView)itemView.findViewById(R.id.near_me);
         all = (TextView)itemView.findViewById(R.id.all);
         friends = (TextView)itemView.findViewById(R.id.friends);

        }
    }

    public class MyTopItem extends MyViewholder {


        public MyTopItem(View itemView) {
            super(itemView);

                bannerImage = (ImageView) itemView.findViewById(R.id.show_img);


        }
    }

    public class MySocialHolder extends MyViewholder {




        public MySocialHolder(View itemView) {
            super(itemView);

                twitterHandle = (TextView)itemView.findViewById(R.id.twitter_handle);
                tweet = (TextView)itemView.findViewById(R.id.tweet_text);
                profileImg = (ImageView)itemView.findViewById(R.id.profile_image);

        }
    }


}
