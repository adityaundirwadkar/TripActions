package com.tripactions.articles;

import com.tripactions.articles.data.ArticlesRepository;
import com.tripactions.articles.domain.GetArticlesSearchStatusUseCase;
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
public class GetArticlesSearchStatusUseCaseTest {

    @Mock
    private ArticlesRepository mArticlesRepository;

    @Test
    public void SearchStatus_UNKNOWN() {
        when(mArticlesRepository.getArticlesSearchStatusSubject())
                .thenReturn(Observable.just(ArticlesRepository.SEARCH_STATUS.UNKNOWN));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<ArticlesRepository.SEARCH_STATUS> testObserver = new TestDisposableObserver<>();
        GetArticlesSearchStatusUseCase getArticlesSearchStatusUseCase = new GetArticlesSearchStatusUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        getArticlesSearchStatusUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<ArticlesRepository.SEARCH_STATUS> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0), ArticlesRepository.SEARCH_STATUS.UNKNOWN);

        // Cleanup
        getArticlesSearchStatusUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }

    @Test
    public void SearchStatus_LOADED() {
        when(mArticlesRepository.getArticlesSearchStatusSubject())
                .thenReturn(Observable.just(ArticlesRepository.SEARCH_STATUS.LOADED));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<ArticlesRepository.SEARCH_STATUS> testObserver = new TestDisposableObserver<>();
        GetArticlesSearchStatusUseCase getArticlesSearchStatusUseCase = new GetArticlesSearchStatusUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        getArticlesSearchStatusUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<ArticlesRepository.SEARCH_STATUS> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0), ArticlesRepository.SEARCH_STATUS.LOADED);

        // Cleanup
        getArticlesSearchStatusUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }

    @Test
    public void SearchStatus_LOADING() {
        when(mArticlesRepository.getArticlesSearchStatusSubject())
                .thenReturn(Observable.just(ArticlesRepository.SEARCH_STATUS.LOADING));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<ArticlesRepository.SEARCH_STATUS> testObserver = new TestDisposableObserver<>();
        GetArticlesSearchStatusUseCase getArticlesSearchStatusUseCase = new GetArticlesSearchStatusUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        getArticlesSearchStatusUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<ArticlesRepository.SEARCH_STATUS> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0), ArticlesRepository.SEARCH_STATUS.LOADING);

        // Cleanup
        getArticlesSearchStatusUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }

    @Test
    public void SearchStatus_FAILED() {
        when(mArticlesRepository.getArticlesSearchStatusSubject())
                .thenReturn(Observable.just(ArticlesRepository.SEARCH_STATUS.FAILED));

        // Set up variables for test
        TestScheduler testScheduler = new TestScheduler();
        TestDisposableObserver<ArticlesRepository.SEARCH_STATUS> testObserver = new TestDisposableObserver<>();
        GetArticlesSearchStatusUseCase getArticlesSearchStatusUseCase = new GetArticlesSearchStatusUseCase(testScheduler, testScheduler, mArticlesRepository);

        // Execute Test
        getArticlesSearchStatusUseCase.execute(null, testObserver);
        testScheduler.triggerActions();

        // Verify Results
        Assert.assertFalse(testObserver.isDisposed());
        Assert.assertEquals(testObserver.getStarts(), 1);
        Assert.assertEquals(testObserver.getCompletions(), 1);
        List<Throwable> errors = testObserver.getErrors();
        Assert.assertNotNull(errors);
        Assert.assertEquals(errors.size(), 0);
        List<ArticlesRepository.SEARCH_STATUS> results = testObserver.getValues();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0), ArticlesRepository.SEARCH_STATUS.FAILED);

        // Cleanup
        getArticlesSearchStatusUseCase.dispose();
        Assert.assertTrue(testObserver.isDisposed());
    }
}
