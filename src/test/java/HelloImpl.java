/**
 * Created by niceyuanze on 17-5-23.
 */
public class HelloImpl implements Hello{

    @Override
    public void say(String name) {
        System.out.println("Heelo! " + name);
    }

    @Override
    public void say1(String name) {
        System.out.println("helloo1111"+name);
    }
}
