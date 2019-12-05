package com.tripactions.presentation;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.readystatesoftware.chuck.Chuck;
import com.tripactions.BuildConfig;
import com.tripactions.ComponentProvider;
import com.tripactions.R;
import com.tripactions.base.presentation.TripActionsActivity;
import com.tripactions.base.presentation.TripActionsFragment;
import com.yalantis.jellytoolbar.listener.JellyListener;
import com.yalantis.jellytoolbar.widget.JellyToolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends TripActionsActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private MainActivityViewModel mViewModel;

    @BindView(R.id.tb_trip_actions)
    JellyToolbar mToolbar;

    // Inflated on demand
    private AppCompatEditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentProvider.get().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel.class);
        initToolbar();
        mViewModel.navigateToNews();
    }

    @Override
    public void onBackPressed() {

        // Imitate backpress to quit the app instead of popping the last fragment.
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        // Provide "onBackPressed" to the topmost fragment.
        final int top = fragments.size() - 1;
        boolean shouldDestroyActivity = false;
        if (fragments.get(top) instanceof TripActionsFragment) {
            shouldDestroyActivity = ((TripActionsFragment)fragments.get(top)).shouldDestroyActivity();
        }
        if (shouldDestroyActivity) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void initToolbar() {
        mToolbar.getToolbar().setPadding(0, getStatusBarHeight(), 0, 0);
        if (BuildConfig.DEBUG) {
            mToolbar.getToolbar().setNavigationIcon(R.drawable.ic_menu);
            mToolbar.getToolbar().setNavigationOnClickListener(view -> startActivity(Chuck.getLaunchIntent(MainActivity.this)));
        }
        mToolbar.setJellyListener(new JellyListener() {
            @Override
            public void onCancelIconClicked() {
                if (TextUtils.isEmpty(mEditText.getText())) {
                    mToolbar.collapse();
                } else {
                    mEditText.getText().clear();
                }
            }
        });

        initSearchText();
        mToolbar.setContentView(mEditText);
        setFullScreen();
    }

    private void initSearchText() {
        mEditText = (AppCompatEditText) LayoutInflater.from(this).inflate(R.layout.view_toolbar_input, null);
        mEditText.setBackgroundResource(R.color.colorTransparent);
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchArticles(v.getText().toString());
                closeKeyboard();
                return true;
            }
            return false;
        });
    }

    private void searchArticles(String keyword) {
        Log.d(TAG, "searchArticles(" + keyword + ")");
        // Call use case to fetch articles.
        mViewModel.searchNewsArticles(keyword);
    }

    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        mToolbar.setVisibility(View.GONE);
    }
}
