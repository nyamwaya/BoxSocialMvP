package com.example.administrator.boxsocialmvp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.boxsocialmvp.Objects.Socializer;
import com.example.administrator.boxsocialmvp.R;
import com.example.administrator.boxsocialmvp.ShowAdapter;
import com.example.administrator.boxsocialmvp.SocialActivity;
import com.example.administrator.boxsocialmvp.TvCard;
import com.example.administrator.boxsocialmvp.Utils.SimpleTimeTranslator;
import com.gc.materialdesign.views.ButtonRectangle;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2/27/2015.
 */
public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewholder> {

    private final String TAG = this.getClass().getSimpleName();
    private final LayoutInflater inflator;
    private List<Socializer> chatter = Collections.emptyList();
    String imgPath;
    TvCard showInfo;
    private boolean isTopLayout;
    private boolean isNormalLayout;
    private boolean isSecondLayout;
    private SocialActivity context;
    String IMG_ENDPOINT = "http://image.tmdb.org/t/p/w500";
    private boolean colorSet = false;


    public SocialAdapter(SocialActivity context, TvCard showInfo, List<Socializer> chatter) {
        inflator = LayoutInflater.from(context);
        this.showInfo = showInfo;
        this.imgPath = showInfo.bannerImg;
        this.context = context;
        this.chatter = chatter;
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
    public void onBindViewHolder(final MyViewholder holder, int position) {

        if(position==0){
            if(imgPath!=null && !imgPath.contains("http:")) {

                Picasso.with(context).load(IMG_ENDPOINT + imgPath).error(R.drawable.ic_book_black_48dp).into(holder.bannerImage);
            }else{
                Picasso.with(context).load(imgPath).error(R.drawable.ic_book_black_48dp).into(holder.bannerImage);

            }
        }
        if(position==1){

            if(!colorSet) {
                holder.nearMe.setBackgroundColor(context.getResources().getColor(R.color.white95));
                holder.friends.setBackgroundColor(context.getResources().getColor(R.color.white95));
                holder.all.setBackgroundColor(Color.parseColor("#1E88E5"));
                holder.nearMe.setTextColor(context.getResources().getColor(R.color.black95));
                holder.friends.setTextColor(context.getResources().getColor(R.color.black95));
                colorSet = true;
            }

            holder.nearMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.nearMe.setBackgroundColor(Color.parseColor("#1E88E5"));
                    holder.nearMe.setTextColor(context.getResources().getColor(R.color.white95));
                    holder.all.setBackgroundColor(context.getResources().getColor(R.color.white95));
                    holder.all.setTextColor(context.getResources().getColor(R.color.black95));
                    holder.friends.setBackgroundColor(context.getResources().getColor(R.color.white95));
                    holder.friends.setTextColor(context.getResources().getColor(R.color.black95));
                    context.searchNearMe();
                }
            });

            holder.all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.all.setBackgroundColor(Color.parseColor("#1E88E5"));
                    holder.all.setTextColor(context.getResources().getColor(R.color.white95));
                    holder.nearMe.setBackgroundColor(context.getResources().getColor(R.color.white95));
                    holder.nearMe.setTextColor(context.getResources().getColor(R.color.black95));
                    holder.friends.setBackgroundColor(context.getResources().getColor(R.color.white95));
                    holder.friends.setTextColor(context.getResources().getColor(R.color.black95));
                    context.searchAll();
                }
            });
        }
        if(position!=0 && position!=1){
            if(!chatter.isEmpty()&& chatter.size()>position) {
                position = position-2;
                try {
                    setUpSocialBlock(holder, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void setUpSocialBlock(final MyViewholder holder, int position) {
        Picasso.with(context)
                .load(chatter.get(position).getUserImg())
                .error(R.drawable.ic_perm_contact_cal_white_36dp)
                .into(holder.profileImg);

        holder.tweet
                .setText(chatter.get(position).getStatusText());
        holder.twitterHandle
                .setText(chatter
                        .get(position)
                        .getSocialNetwork()
                        + " - " +
                        chatter.get(position).getUsername());
        Log.d("ItemDate: ",chatter.get(position).getDateTime().toString());

        String simpleTime = SimpleTimeTranslator.getTWTimeString(chatter.get(position).getDateTime());
        if(!simpleTime.contains("ago")){
            simpleTime = simpleTime +" ago";
        }

        holder.timeStamp.setText(simpleTime);
        holder.timeStamp.setTypeface(null, Typeface.ITALIC);
        if(chatter.get(position).getMediaImg()!=null &&
                !chatter.get(position).getMediaImg().equalsIgnoreCase("")){
            Picasso.with(context)
                    .load(chatter.get(position).getMediaImg())
                    .error(R.drawable.ic_3d_rotation_grey600_36dp)
                    .into(holder.mediaImg)
            ;
            holder.mediaImg.setVisibility(View.VISIBLE);
            holder.mediaImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SocialActivity.loadPhoto(holder.mediaImg, context);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return chatter.size()+2;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        protected ButtonRectangle nearMe;
        protected ButtonRectangle all;
        protected ButtonRectangle friends;
        protected ImageView bannerImage;
        ImageView mediaImg;
        TextView timeStamp;

        TextView twitterHandle;
        TextView tweet;
        ImageView profileImg;

        public MyViewholder(View itemView) {
            super(itemView);
         nearMe = (ButtonRectangle)itemView.findViewById(R.id.near_me);
         all = (ButtonRectangle)itemView.findViewById(R.id.all);
         friends = (ButtonRectangle)itemView.findViewById(R.id.friends);


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
                mediaImg = (ImageView)itemView.findViewById(R.id.media_img);
                timeStamp = (TextView)itemView.findViewById(R.id.twitter_timestamp);        }
    }


}
