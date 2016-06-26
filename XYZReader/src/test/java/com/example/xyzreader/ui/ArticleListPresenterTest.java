package com.example.xyzreader.ui;

import com.example.xyzreader.data.UpdaterService;
import com.example.xyzreader.ui.list.ArticleListPresenter;
import com.example.xyzreader.ui.list.ArticleListView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ArticleListPresenterTest {
    @Test
    public void testThatArticleListIsNotClickableWhenProgressBarIsShown() {
        ArticleListView articleListView = mock(ArticleListView.class);
        ArticleListPresenter articleListPresenter = new ArticleListPresenter(articleListView);
        boolean isRefreshing = true;
        articleListPresenter.toggleProgressView(isRefreshing);
        verify(articleListView).showProgressBar();
    }

    @Test
    public void testThatArticleListIsClickableWhenProgressBarIsNotShown() {
        ArticleListView articleListView = mock(ArticleListView.class);
        ArticleListPresenter articleListPresenter = new ArticleListPresenter(articleListView);
        boolean isRefreshing = false;
        articleListPresenter.toggleProgressView(isRefreshing);
        verify(articleListView).hideProgressBar();
    }

    @Test
    public void testThatArticleDetailsScreenCanBeSeenWhenArticlesAreNotBeingFetched() {
        ArticleListView articleListView = mock(ArticleListView.class);
        ArticleListPresenter articleListPresenter = new ArticleListPresenter(articleListView);
        articleListPresenter.onArticleListItemClick(1l, false);
        verify(articleListView).showArticleDetails(1l);
    }

    @Test
    public void testThatArticleDetailsScreenCannotBeSeenWhenArticlesAreBeingFetched() {
        ArticleListView articleListView = mock(ArticleListView.class);
        ArticleListPresenter articleListPresenter = new ArticleListPresenter(articleListView);
        articleListPresenter.onArticleListItemClick(1l, true);
        verifyNoMoreInteractions(articleListView);
    }

    @Test
    public void testThatArticlesLoadingShouldBeStartedWhenArticlesStateIsUnknown() {
        ArticleListView articleListView = mock(ArticleListView.class);
        ArticleListPresenter articleListPresenter = new ArticleListPresenter(articleListView);
        articleListPresenter.onArticlesStateChange(UpdaterService.ARTICLES_STATUS_UNKNOWN);
        verify(articleListView).onArticlesLoadingStarted();
    }
}
