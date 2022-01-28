package by.epam.medicalweb.controller.command;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String page;
    private RouterType routerType = RouterType.FORWARD;

    public Router(String pagePath, RouterType routerType) {
        this.page = pagePath;
        this.routerType = routerType;
    }

    public Router(String pagePath) {
        this.page = pagePath;
    }

    public Router() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }
}
