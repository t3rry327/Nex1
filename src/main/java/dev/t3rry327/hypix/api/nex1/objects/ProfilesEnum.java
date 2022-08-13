package dev.t3rry327.hypix.api.nex1.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ProfilesEnum {
    Apple, Banana, Blueberry, Coconut, Cucumber, Grapes, Kiwi, Lemon, Lime, Mango, Orange, Papaya, Pear, Peach,
    Pineapple, Pomegranate, Raspberry, Strawberry, Tomato, Watermelon, Zucchini;
    private static final List<ProfilesEnum> VALUES =Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ProfilesEnum randomProfile()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
