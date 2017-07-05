/**
 * Created by niceyuanze on 17-5-23.
 */
public class HeeloProxy implements Hello{
    private Hello hello;

    public HeeloProxy(Hello hello) {
        this.hello = hello;
    }


    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    @Override
    public void say1(String name) {

    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }

    public static void main(String[] args) {
        Hello helloProxy = new HeeloProxy(new HelloImpl());
        helloProxy.say("123");
    }
}
