package com.tripactions.presentation.screen.article;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tripactions.articles.data.response.Doc;
import com.tripactions.image.ImageLoader;

import javax.inject.Inject;

/**
 *
 */
public class ArticleViewModel extends ViewModel {

    private Doc mDoc;
    private Bitmap mBitmap;
    private final ImageLoader mImageLoader;

    private final MutableLiveData<LOGO_STATES> mArticlePreview;

    @Inject
    public ArticleViewModel(ImageLoader imageLoader) {

        mImageLoader = imageLoader;
        mArticlePreview = new MutableLiveData<>();
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

    public String getArticleContents() {
        return mDoc.getLeadParagraph();
    }

    public String getArticleSnippet() {
        return mDoc.getSnippet();
    }

    public String getArticleLink() {
        return mDoc.getWebUrl();
    }

    public MutableLiveData<LOGO_STATES> getImagePreviewLiveData() {
        return mArticlePreview;
    }

    public Doc getDoc() {
        return mDoc;
    }

    public void setDoc(Doc mDoc) {
        this.mDoc = mDoc;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public enum LOGO_STATES {
        LOADING,
        LOADED,
        FAILED,
        UNKNOWN
    }
}
