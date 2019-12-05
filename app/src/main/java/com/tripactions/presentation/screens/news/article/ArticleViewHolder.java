package com.tripactions.presentation.screens.news.article;

import android.graphics.PorterDuff;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.tripactions.R;
import com.tripactions.base.presentation.RecyclerView.ViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class ArticleViewHolder extends ViewHolder<ArticleViewModel> {

    @BindView(R.id.ll_article_container)
    LinearLayout mLlArticle;

    @BindView(R.id.iv_article_preview)
    ImageView mIvPreview;

    @BindView(R.id.tv_article_headline)
    TextView mTvHeadline;


    public ArticleViewHolder(ViewGroup parent) {
        super(parent, R.layout.view_article_entry);
    }

    @Override
    public void bindViewModel(ArticleViewModel viewModel) {
        super.bindViewModel(viewModel);

        // Observers.
        initPreviewImageObserver();
        //Init views.
        setupPreviewImages();
        setupText();
    }

    @Override
    public void onRecycled() {
        super.onRecycled();
        resetViews();
        // Remove live data updates.
        if (mViewModel != null) {
            mViewModel.getImagePreviewLiveData().removeObserver(mPreviewImageObserver);
        }
    }

    @OnClick(R.id.ll_article_container)
    public void onClickedArticle() {
        mViewModel.onViewHolderClicked();
    }

    private void initPreviewImageObserver() {
        mViewModel.getImagePreviewLiveData().observeForever(mPreviewImageObserver);
    }

    private void setupPreviewImages() {
        mViewModel.loadArticlePreviewImage(getContext());
    }

    private void setupText() {
        mTvHeadline.setText(mViewModel.getArticleHeadline());
    }

    private void resetViews() {
        mIvPreview.setImageBitmap(null);
        mIvPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTvHeadline.setText(null);
    }

    // Observer for image download.
    private Observer<ArticleViewModel.LOGO_STATES> mPreviewImageObserver = logoState -> {
        if (mViewModel == null) {
            return;
        }
        switch (logoState) {
            case UNKNOWN:
                mViewModel.loadArticlePreviewImage(getContext());
                mIvPreview.setColorFilter(null);
                mIvPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case LOADING:
                mIvPreview.setImageDrawable(getContext().getDrawable(R.drawable.ic_cloud_download));
                mIvPreview.setColorFilter(getContext().getColor(R.color.colorStart), PorterDuff.Mode.SRC_ATOP);
                mIvPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case LOADED:
                mIvPreview.setImageBitmap(mViewModel.getBitmap());
                mIvPreview.setColorFilter(null);
                mIvPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case FAILED:
                // Add Error Image.
                mIvPreview.setImageDrawable(getContext().getDrawable(R.drawable.ic_error));
                mIvPreview.setColorFilter(getContext().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                mIvPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            default:
                break;
        }
    };
}
