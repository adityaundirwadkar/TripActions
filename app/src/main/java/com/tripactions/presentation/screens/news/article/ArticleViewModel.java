package com.tripactions.presentation.screens.news.article;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.presentation.RecyclerView.RecyclerViewViewModel;
import com.tripactions.image.ImageLoader;

/**
 *
 */

@AutoFactory
public class ArticleViewModel extends RecyclerViewViewModel<ArticleViewModel.Interactor> {


    private static final String TAG = ArticleViewModel.class.getSimpleName();

    private final ImageLoader mImageLoader;
    private final Doc mDoc;
    private Bitmap mBitmap;

    private final MutableLiveData<LOGO_STATES> mArticlePreview;

    public ArticleViewModel(@NonNull final Doc doc,
                            @Provided ImageLoader imageLoader) {

        super(ArticleViewHolder.class);
        mDoc = doc;
        mImageLoader = imageLoader;
        mArticlePreview = new MutableLiveData<>();

        mArticlePreview.setValue(LOGO_STATES.UNKNOWN);
    }

    @Override
    public void onRecycled() {
        super.onRecycled();

        if (mDoc.hasPreviewImage()) {
            mImageLoader.cancelRequest(getArticleImageUrl());
        }
    }

    public void loadArticlePreviewImage(Context context) {

        if (!mDoc.hasPreviewImage()) {
            mArticlePreview.setValue(LOGO_STATES.FAILED);
            return;
        }

        mImageLoader.load(context, getArticleImageUrl(), new ImageLoader.Callback() {
            @Override
            public void loadingStarted(String uri) {
                mArticlePreview.setValue(LOGO_STATES.LOADING);
            }

            @Override
            public void loadingCompleted(String uri, Bitmap bitmap) {
                mBitmap = bitmap;
                mArticlePreview.setValue(LOGO_STATES.LOADED);
            }

            @Override
            public void loadingError(String uri) {
                // display error.
                mArticlePreview.setValue(LOGO_STATES.FAILED);
            }
        });
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public String getArticleHeadline() {
        return mDoc.getHeadline().getMain();
    }

    public String getArticleImageUrl() {
        return mDoc.getPreviewImageUrl();
    }

    public MutableLiveData<LOGO_STATES> getImagePreviewLiveData() {
        return mArticlePreview;
    }

    // Callback to higher level
    public void onViewHolderClicked() {
        if (mInteractor != null && mInteractor.get() != null) {
            mInteractor.get().onClickedArticle(mDoc);
        }
    }

    public enum LOGO_STATES {
        LOADING,
        LOADED,
        FAILED,
        UNKNOWN
    }

    public interface Interactor {
        public void onClickedArticle(Doc doc);
    }
}
