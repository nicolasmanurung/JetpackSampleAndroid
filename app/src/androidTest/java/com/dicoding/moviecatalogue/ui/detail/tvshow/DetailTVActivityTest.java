package com.dicoding.moviecatalogue.ui.detail.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.data.source.local.entity.TVShowEntity;
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

public class DetailTVActivityTest {
    private TVShowEntity movieEntity = FakeDataDummy.generateDummyTVDetail();

    @Rule
    public ActivityTestRule<DetailTVActivity> activityTestRule = new ActivityTestRule<DetailTVActivity>(DetailTVActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(context, DetailTVActivity.class);
            intent.putExtra(DetailTVActivity.EXTRA_TVSHOW, movieEntity.getId());
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
    public void loadTVShowDetail() {
        onView(withId(R.id.tvshow_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_name)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_director)).check(matches(isDisplayed()));
        onView(withId(R.id.tvshow_description)).check(matches(isDisplayed()));
        onView(withId(R.id.floatingButtonTVDetail)).check(matches(isDisplayed()));
    }

}