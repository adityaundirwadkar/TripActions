package com.tripactions.articles;

import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.articles.domain.SearchArticlesUseCase;
import com.tripactions.base.TestDisposableObserver;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.when;

/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchArticlesUseCaseTest {

    @Mock
    private ArticlesRepository mArticlesRepository;

    @Test
    public void SearchArticles_True() {
        when(mArticlesRepository.searchArticles(null))
                .thenReturn(Observable.just(Boolean.TRUE));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<Boolean> testObserver = new TestDisposableObserver<>();
        SearchArticlesUseCase searchArticlesUseCase = new SearchArticlesUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        searchArticlesUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<Boolean> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertTrue(results.get(0));

        // Cleanup
        searchArticlesUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }

    @Test
    public void SearchArticles_False() {
        when(mArticlesRepository.searchArticles(null))
                .thenReturn(Observable.just(Boolean.FALSE));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<Boolean> testObserver = new TestDisposableObserver<>();
        SearchArticlesUseCase searchArticlesUseCase = new SearchArticlesUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        searchArticlesUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<Boolean> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertFalse(results.get(0));

        // Cleanup
        searchArticlesUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }
}
