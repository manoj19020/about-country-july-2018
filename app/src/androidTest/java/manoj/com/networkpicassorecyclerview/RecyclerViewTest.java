package manoj.com.networkpicassorecyclerview;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import manoj.com.networkpicassorecyclerview.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Manoj.Kumar04 on 7/30/2018.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    /**
     * The M activity test rule.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Recycler view test.
     */
    @Test
    public void recyclerViewTest() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.card_recycler_view)).perform(

                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition(10)
                );
        /*ViewInteraction recyclerView = onView(
                allOf(withId(R.id.card_recycler_view), isDisplayed()));
        onView(withId(R.id.card_recycler_view)).check(ViewAssertions.matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));*/

//        ViewInteraction textView = onView(allOf(withId(R.id.label_header), withText("Beavers"), childAtPosition(childAtPosition(withId(R.id.card_recycler_view), 0), 0), isDisplayed()));
        ViewInteraction textView = onView(allOf(withId(R.id.label_header), withText("Beavers"), atPosition(0, withId(R.id.card_recycler_view)), isDisplayed()));
        textView.check(matches(withText(R.id.label_header)));

    }

    /**
     * At position matcher.
     *
     * @param position    the position
     * @param itemMatcher the item matcher
     * @return the matcher
     */
    public static Matcher<View> atPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

}
