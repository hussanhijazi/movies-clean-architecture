package br.com.hussan.cubosmovies.ui.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.hussan.cubosmovies.R
import br.com.hussan.cubosmovies.ui.MOVIE_VIEW
import br.com.hussan.cubosmovies.ui.moviedetails.MovieDetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<MovieDetailsActivity> =
        object : ActivityTestRule<MovieDetailsActivity>(MovieDetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return MovieDetailsActivity.newIntent(targetContext, MOVIE_VIEW)
            }
        }

    @Test
    fun checkMovieDetails() {

        onView(withText(MOVIE_VIEW.overview)).check(matches(isDisplayed()))
        onView(withText(MOVIE_VIEW.title)).check(matches(isDisplayed()))

        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.txtOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.materialRatingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.btnShare)).check(matches(isDisplayed()))
    }

    @Test
    fun swipeViewPager() {
        onView(withId(R.id.rvVideos))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvVideos))
            .perform(ViewActions.swipeLeft())
    }
}
