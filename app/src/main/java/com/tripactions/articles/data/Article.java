package com.tripactions.articles.data;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.tripactions.articles.data.response.Headline;
import com.tripactions.articles.data.response.Multimedia;
import com.tripactions.base.data.TripActionsModel;

/**
 *
 */
@Entity(tableName = Article.TABLE_NAME)
public class Article extends TripActionsModel {

    public static final String TABLE_NAME = "articles";

    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    @PrimaryKey
    @NonNull
    public String _id;

    @ColumnInfo(name = "apiIndex")
    @NonNull
    public Long apiIndex;

    @ColumnInfo(name = "web_url")
    @SerializedName("web_url")
    @NonNull
    public String webUrl;

    @ColumnInfo(name = "snippet")
    @SerializedName("snippet")
    @NonNull
    public String snippet;

    @ColumnInfo(name = "lead_paragraph")
    @SerializedName("snippet")
    @NonNull
    public String leadParagraph;

    @SerializedName("headline")
    @Ignore
    public Headline headline;

    @SerializedName("multimedia")
    @Ignore
    public Multimedia multimedia;

    public Article() {

    }

    public boolean hasPreviewImage() {
        return false; // return multimedia != null && !ListUtils.isEmpty(multimedia.imageList());
    }

    public String getPreviewImageUrl() {
        if (!hasPreviewImage()) {
            return null;
        }

        return null; // return "https://www.nytimes.com/" + multimedia.imageList().get(0).url();
    }

    public static DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem._id.equals(newItem._id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        Article article = (Article) obj;
        return article._id.equals(this._id);
    }
}