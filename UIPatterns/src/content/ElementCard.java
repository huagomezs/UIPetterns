
package content;

public class ElementCard {

    private String name;
    private String status;
    private String technicalDetails;
    private boolean showAdvanced;

    public ElementCard(String name, String status,
                       String technicalDetails) {
        this.name = name;
        this.status = status;
        this.technicalDetails = technicalDetails;
        this.showAdvanced = false;
    }

    public void showSummary() {
        System.out.println(
            "[Summary View] " + name + " - Status: " + status
        );
    }

    public void toggleAdvancedDetails() {
        showAdvanced = !showAdvanced;

        if (showAdvanced) {
            System.out.println(
                "[Advanced Details] " + technicalDetails
            );
        } else {
            System.out.println(
                "[Advanced Details Hidden]"
            );
        }
    }

    public boolean isShowAdvanced() {
        return showAdvanced;
    }
}
