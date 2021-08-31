package cc.java8.java8.FunctionalInterface;


@FunctionalInterface
interface GreetingService
{
	void sayMessage(String message);
}

public class FunctionalInterfaceTester {
	public static void main(String[] args) {
		GreetingService greetService1 = message -> System.out.println("Hello " + message);
		greetService1.sayMessage("a");
	}
}
