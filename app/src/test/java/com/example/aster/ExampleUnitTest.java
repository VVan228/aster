package com.example.aster;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.aster.entities.User;
import com.example.aster.events.Event;
import com.example.aster.events.EventsBus;
import com.example.aster.events.Observer;
import com.example.aster.models.Authorization;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void authTest(){
        Authorization auth = new Authorization();
        EventsBus.register(new Observer() {
            @Override
            public void onEvent(Event event) {
                assertNull(event.message);
            }
        });
        auth.signUp("vvan228@vk.com", "1234", new User("bob", "killer","","","vvan228@vk.com"));
    }
}