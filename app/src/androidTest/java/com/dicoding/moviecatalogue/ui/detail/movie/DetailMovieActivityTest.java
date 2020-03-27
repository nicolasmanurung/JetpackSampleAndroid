package com.dicoding.moviecatalogue.ui.detail.movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.data.source.local.entity.MovieEntity;
import com.dicoding.moviecatalogue.utils.EspressoIdlingResource;
import com.dicoding.moviecatalogue.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailMovieActivityTest {
    private MovieEntity movieEntity = FakeDataDummy.generateDummyMovieDetail();

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieEntity.getMvId());
            return intent;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovieDetail() {
        onView(withId(R.id.img_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_name)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_date)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_director)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_description)).check(matches(isDisplayed()));
        onView(withId(R.id.floatingButtonMovieDetail)).check(matches(isDisplayed()));
    }
}