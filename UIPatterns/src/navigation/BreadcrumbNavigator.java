
package navigation;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbNavigator {

    private List<String> route;
    private String currentTab;

    public BreadcrumbNavigator() {
        route = new ArrayList<>();
        route.add("Home");
        currentTab = "Home";
    }

    public void navigateTo(String section) {
        if (section == null || section.trim().isEmpty()) {
            return;
        }

        route.add(section);
        currentTab = section;
    }

    public void changeTab(String tab) {
        if (tab == null || tab.trim().isEmpty()) {
            return;
        }

        if (route.size() > 1) {
            route.remove(route.size() - 1);
        }

        if (!route.get(route.size() - 1).equals(tab)) {
            route.add(tab);
        }

        currentTab = tab;
    }

    public void goBack() {
        if (route.size() > 1) {
            route.remove(route.size() - 1);
        }

        currentTab = route.get(route.size() - 1);
    }

    public String getCurrentRoute() {
        return String.join(" > ", route);
    }

    public String getCurrentTab() {
        return currentTab;
    }
}
