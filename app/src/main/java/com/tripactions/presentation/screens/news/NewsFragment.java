package com.tripactions.presentation.screens.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.tripactions.ComponentProvider;
import com.tripactions.R;
import com.tripactions.articles.data.response.Doc;
import com.tripactions.base.presentation.RecyclerView.RecyclerViewAdapter;
import com.tripactions.base.presentation.TripActionsFragment;
import com.tripactions.presentation.screens.news.article.ArticleViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 *
 */
public class NewsFragment extends TripActionsFragment<NewsViewModel> implements ArticleViewModel.Interactor {

    public final static String TAG = NewsFragment.class.getSimpleName();


    @BindView(R.id.sk_loading_articles)
    ProgressBar mSkLoading;

    @BindView(R.id.tv_error)
    TextView mTvError;

    @BindView(R.id.rv_articles)
    RecyclerView mRvArticles;

    @Inject
    RecyclerViewAdapter mArticlesAdapter;

    public static NewsFragment create() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ComponentProvider.get()
                .provideNewsComponent(new NewsModule(this))
                .inject(this);

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewsViewModel.class);

        initArticlesScreen();
        initArticleViewModelsObserver();
        initArticleSearchStatusObserver();
        return root;
    }

    @Override
    public boolean shouldDestroyActivity() {
        return true;
    }

    @Override
    public void onClickedArticle(Doc doc) {
        Log.d(TAG, "onClickedArticle(" + doc + ")");
        mViewModel.navigateToArticle(doc);
    }

    private void initArticlesScreen() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvArticles.setLayoutManager(linearLayoutManager);
        mRvArticles.setAdapter(mArticlesAdapter);
        mSkLoading.setIndeterminateDrawable(new DoubleBounce());
    }

    private void refreshArticles() {
        mSkLoading.setVisibility(View.VISIBLE);
        mTvError.setVisibility(View.GONE);
        mRvArticles.setVisibility(View.GONE);
    }

    private void displayArticles() {
        mSkLoading.setVisibility(View.GONE);
        mTvError.setVisibility(View.GONE);
        mRvArticles.setVisibility(View.VISIBLE);
    }

    private void displayError() {
        mSkLoading.setVisibility(View.GONE);
        mTvError.setVisibility(View.VISIBLE);
        mRvArticles.setVisibility(View.GONE);
    }

    private void initArticleViewModelsObserver() {
        mViewModel.getArticlesLiveData().observe(this, articleViewModels -> {
            for (ArticleViewModel viewModel : articleViewModels) {
                viewModel.setInteractor(this);
            }
            mArticlesAdapter.setViewModels(articleViewModels);
        });
    }

    private void initArticleSearchStatusObserver() {
        mViewModel.getArticlesSearchStatusLiveData().observe(this, search_status -> {
            switch (search_status) {
                case LOADING:
                    refreshArticles();
                    break;
                case LOADED:
                    displayArticles();
                    break;
                case FAILED:
                    displayError();
                    break;
                default:
                    break;
            }
        });
    }
}
