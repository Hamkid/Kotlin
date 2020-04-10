package hu.renes.kotlin.view.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import hu.renes.kotlin.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLayout() {
        Espresso.onView(withId(R.id.nameText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recylerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.swipeLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}