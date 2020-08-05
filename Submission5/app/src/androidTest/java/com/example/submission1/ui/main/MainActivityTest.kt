package com.example.submission1.ui.main

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.submission1.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Thread.sleep(2000)
        onView(withId(rv_team))
            .check(matches(isDisplayed()))

        onView(withId(navigation))
            .check(matches(isDisplayed()))
        onView(withId(navigation_match)).perform(click())

        Thread.sleep(2000)
        onView(withId(rv_match))
            .check(matches(isDisplayed()))

        onView(withId(search))
            .check(matches(isDisplayed()))
        onView(withId(search)).perform(click())

        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("arsenal"), pressImeActionButton())

        Thread.sleep(2000)
        onView(withId(rv_search))
            .check(matches(isDisplayed()))

        onView(isAssignableFrom(EditText::class.java)).perform(clearText())

        Thread.sleep(2000)
        onView(withId(rv_search))
            .check(matches(isDisplayed()))

        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("arsenal tula"), pressImeActionButton())

        Thread.sleep(2000)
        onView(withId(rv_search))
            .check(matches(isDisplayed()))

        onView(withId(rv_search)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )

        onView(withId(rv_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )
        Thread.sleep(2000)
        onView(withId(content_detail))
            .check(matches(isDisplayed()))
    }
}