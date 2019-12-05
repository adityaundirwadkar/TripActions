package com.tripactions.presentation.screen.article;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.tripactions.ComponentProvider;
import com.tripactions.R;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.presentation.TripActionsActivity;
import com.tripactions.base.presentation.TripActionsFragment;
import com.tripactions.presentation.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

/**
 *
 */
public class ArticleFragment extends TripActionsFragment<ArticleViewModel> {


    public final static String TAG = ArticleFragment.class.getSimpleName();

    @BindView(R.id.iv_status_bar_replacement)
    ImageView mIvStatusBar;

    @BindView(R.id.tv_article_header)
    TextView mTvHeader;

    @BindView(R.id.tv_article_snippet)
    TextView mTvSnippet;

    @BindView(R.id.iv_article_preview)
    ImageView mIvPreview;

    @BindView(R.id.iv_article_share)
    ImageView mIvShare;

    @BindView(R.id.tv_article_contents)
    TextView mTvContents;

    private Doc mDoc;

    public static ArticleFragment create(Doc doc) {
        ArticleFragment fragment = new ArticleFragment();
        fragment.mDoc = doc;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ComponentProvider.get()
                .provideArticleComponent(new ArticleModule(this))
                .inject(this);

        View root = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, root);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ArticleViewModel.class);

        mViewModel.setDoc(mDoc);
        initArticleScreen();
        initImageObserver();
        mViewModel.loadArticlePreviewImage(getContext());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideToolbar();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).showToolbar();
    }

    @OnClick(R.id.iv_article_share)
    public void onShareClicked() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mViewModel.getArticleLink());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, mViewModel.getArticleHeadline());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void initArticleScreen() {
        mIvStatusBar.getLayoutParams().height = ((TripActionsActivity)getActivity()).getStatusBarHeight();
        mIvStatusBar.requestLayout();
        mTvHeader.setText(mViewModel.getArticleHeadline());
        mTvSnippet.setText(mViewModel.getArticleSnippet());
        mTvContents.setText(mViewModel.getArticleContents());
    }

    private void initImageObserver() {
        mViewModel.getImagePreviewLiveData().observe(this, logo_states -> {
            switch (logo_states) {
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
                    mIvPreview.setImageDrawable(getContext().getDrawable(R.drawable.ic_error));
                    mIvPreview.setColorFilter(getContext().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
                    mIvPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
            }
        });
    }
}
