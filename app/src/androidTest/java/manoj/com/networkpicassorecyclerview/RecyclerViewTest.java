package manoj.com.networkpicassorecyclerview;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import manoj.com.networkpicassorecyclerview.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.card_recycler_view)).perform(scrollToPosition(10));

        onView(withId(R.id.title)).check(matches(withText(containsString("About Canada"))));

    }

}
