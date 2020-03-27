package com.dicoding.moviecatalogue.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.ui.home.MainActivity;
import com.dicoding.moviecatalogue.utils.EspressoIdlingResource;
import com.dicoding.moviecatalogue.utils.RecyclerViewItemCountAssertion;
import com.dicoding.moviecatalogue.utils.TabLayoutViewAction;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieCatalogueTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void MovieDetailActivity() {
        onView(withId(R.id.navigation_movie)).perform(click());
        onView(withId(R.id.rv_fragment_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_fragment_movie)).check(new RecyclerViewItemCountAssertion(20 + 1));
        onView(withId(R.id.rv_fragment_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.img_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_name)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_date)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_director)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()));

        onView(withId(R.id.floatingButtonMovieDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.floatingButtonMovieDetail)).perform(click());
    }

    @Test
    public void TVShowDetailActivity() {
        onView(withId(R.id.navigation_tvshow)).perform(click());
        onView(withId(R.id.rv_fragment_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_fragment_tv_show)).check(new RecyclerViewItemCountAssertion(20 + 1));
        onView(withId(R.id.rv_fragment_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tvshow_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_name)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_director)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_description)).check(matches(isDisplayed()));

        onView(withId(R.id.floatingButtonTVDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.floatingButtonTVDetail)).perform(click());
    }

    @Test
    public void C_toFavoritesMovieFragment() {
        onView(withId(R.id.navigation_favorite)).perform(click());
        onView(withId(R.id.tabs)).perform(new TabLayoutViewAction(0));
    }

    @Test
    public void D_toFavoritesTvShowFragment() {
        onView(withId(R.id.navigation_favorite)).perform(click());
        onView(withId(R.id.tabs)).perform(new TabLayoutViewAction(1));
    }
}
