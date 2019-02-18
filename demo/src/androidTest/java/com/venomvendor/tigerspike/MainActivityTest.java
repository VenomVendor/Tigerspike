/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike;

import com.google.gson.Gson;
import com.venomvendor.sdk.core.model.gallery.Feed;
import com.venomvendor.sdk.core.model.gallery.PublicFeed;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;
import okhttp3.mockwebserver.MockResponse;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.venomvendor.tigerspike.test.util.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.Assert.assertEquals;

public class MainActivityTest extends BaseTest {

    private static final int CLICK_AT = 2;
    private static final long WAIT_TIME = 3000;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void testViewCount() throws InterruptedException {
        String response = testHelper.read("response-200-ok.json");
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response));

        PublicFeed resObj = new Gson().fromJson(response, PublicFeed.class);

        // Should be replaced by idling resource
        Thread.sleep(WAIT_TIME);

        onView(withId(R.id.feed_list))
                .perform(RecyclerViewActions.scrollToPosition(resObj.getFeeds().size() - 1));

        Thread.sleep(1000);
        onView(withId(R.id.feed_list)).check(withItemCount(resObj.getFeeds().size()));
    }

    @Test
    public void testContentInView() throws InterruptedException {
        String response = testHelper.read("response-200-ok.json");
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response));
        Feed resObj = new Gson().fromJson(response, PublicFeed.class).getFeeds().get(CLICK_AT);

        // Should be replaced by idling resource
        Thread.sleep(WAIT_TIME);

        // In recycler view find
        onView(allOf(withId(R.id.author),
                withText(resObj.getAuthor())))
                .check(matches(withText(resObj.getAuthor())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testNavigationToNextFragment() throws InterruptedException {
        String response = testHelper.read("response-200-ok.json");
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response));

        // Should be replaced by idling resource
        Thread.sleep(WAIT_TIME);

        onView(withId(R.id.feed_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(CLICK_AT, click()));

        List<Fragment> fragments = mActivityTestRule.getActivity()
                .getSupportFragmentManager()
                .getFragments();

        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                assertEquals("fragment_gallery_detail",
                        ((NavHostFragment) fragment).getNavController().getCurrentDestination()
                                .getLabel());
                break;
            }
        }
    }

    @Test
    public void testDetailedFragment() throws InterruptedException {
        String response = testHelper.read("response-200-ok.json");
        Feed resObj = new Gson().fromJson(response, PublicFeed.class).getFeeds().get(CLICK_AT);
        testNavigationToNextFragment();

        Thread.sleep(WAIT_TIME / 2);
        onView(withId(R.id.author))
                .check(
                        matches(
                                withText(resObj.getTitle())
                        )
                );

        onView(withId(R.id.desc)).check(matches(withText(resObj.getDescription())));
        onView(withId(R.id.url)).check(matches(withText(resObj.getLink())));
        onView(withId(R.id.tags)).check(matches(withText(resObj.getTags())));
        onView(withId(R.id.published)).check(matches(withText(resObj.getPublished().toString())));
    }

    @Test
    public void testBackEvent() throws InterruptedException {
        testDetailedFragment();
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressBack();

        Thread.sleep(WAIT_TIME / 2);
        assertEquals(0, mActivityTestRule.getActivity()
                .getSupportFragmentManager()
                .getBackStackEntryCount());
    }
}
