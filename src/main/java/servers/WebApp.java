package servers;

public interface WebApp {
	String mapMethodController(String method, String[] data);
	String getMethods();
}
