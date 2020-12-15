package com.fdm.w6.IandO;

import org.junit.Test;

public class UserRegistryTest {
    @Test
    public void question_one(){
        System.out.println(UserRegistry.countCharacter('e'));
    }

    @Test
    public void question_three(){
        UserRegistry registry = new UserRegistry();
        registry.presentUser();
    }
}
