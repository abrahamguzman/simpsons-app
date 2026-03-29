package com.dev.simpsonapi.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.simpsonapi.db.Episode;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
    private List<Episode> episodes = new ArrayList<>();

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode currentEpisode = episodes.get(position);

        String title = "S" + currentEpisode.season + "E" + currentEpisode.episodeNumber + ": " + currentEpisode.name;

        holder.itemTitle.setText(title);
        holder.itemSynopsis.setText(currentEpisode.synopsis);
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public Episode getEpisodeAtPosition(int position) {
        return episodes.get(position);
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemSynopsis;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(android.R.id.text1);
            itemSynopsis = itemView.findViewById(android.R.id.text2);
        }
    }
}
