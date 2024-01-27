package dataextractor;

public class SharedDataHolder {
    private static ThreadLocal<String> browserTypeThreadLocal = new ThreadLocal<>();

    private SharedDataHolder(){}

    public static String getBrowserType() {
        return browserTypeThreadLocal.get();
    }

    public static void setBrowserType(String browserType) {
        browserTypeThreadLocal.set(browserType);
    }

    public static void cleanBrowseType(){
        browserTypeThreadLocal.remove();
    }
}
