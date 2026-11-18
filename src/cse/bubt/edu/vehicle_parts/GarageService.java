package cse.bubt.edu.vehicle_parts;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

    public class GarageService {

        public static class PartItem {
            public String partName;
            public String tier;
            public int price;

            public PartItem(String partName, String tier, int price) {
                this.partName = partName;
                this.tier = tier;
                this.price = price;
            }
        }

        private String customerName;
        private String serviceType;
        private String vehicleName;
        private int serviceCharge;
        private List<PartItem> selectedParts;
        private String status;

        public GarageService(String customerName, String serviceType) {
            this.customerName = customerName;
            this.serviceType = serviceType;
            this.vehicleName = null;
            this.serviceCharge = 0;
            this.selectedParts = new ArrayList<>();
            this.status = "Checked-In";
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setVehicle(String vehicleName) {
            this.vehicleName = vehicleName;
            this.serviceCharge = VehicleParts.getServiceCharge(vehicleName);
            this.selectedParts.clear();
        }

        public PartItem addPart(String partName, String tier) {
            if (this.vehicleName == null) return null;
            int price = VehicleParts.getPartPrice(this.vehicleName, partName, tier);
            PartItem item = new PartItem(partName, tier, price);
            this.selectedParts.add(item);
            return item;
        }

        public void startRepairing() {
            this.status = "Repair Completed";
        }

        // [0] = serviceCharge, [1] = partsTotal, [2] = grandTotal
        public int[] calculateBill() {
            int partsTotal = 0;
            for (PartItem item : selectedParts) {
                partsTotal += item.price;
            }
            int grandTotal = serviceCharge + partsTotal;
            return new int[]{serviceCharge, partsTotal, grandTotal};
        }

        public String generateBillMemo() {
            int[] bill = calculateBill();
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a"));

            StringBuilder sb = new StringBuilder();
            sb.append("====================================================\n");
            sb.append("            PRO AUTO GARAGE & SERVICE CENTER        \n");
            sb.append("                 OFFICIAL CASH MEMO                 \n");
            sb.append("====================================================\n");
            sb.append(String.format("Date & Time   : %s\n", now));
            sb.append(String.format("Customer Name : %s\n", customerName));
            sb.append(String.format("Vehicle Type  : %s\n", vehicleName));
            sb.append(String.format("Service Mode  : %s\n", serviceType));
            sb.append(String.format("Repair Status : %s\n", status));
            sb.append("----------------------------------------------------\n");
            sb.append("DESCRIPTION                                 AMOUNT  \n");
            sb.append("----------------------------------------------------\n");
            sb.append(String.format("General Service & Repair Charge         %8d BDT\n", bill[0]));

            if (!selectedParts.isEmpty()) {
                sb.append("----------------------------------------------------\n");
                sb.append("Replaced Parts:\n");
                for (int i = 0; i < selectedParts.size(); i++) {
                    PartItem item = selectedParts.get(i);
                    sb.append(String.format("%d. %s\n", (i + 1), item.partName));
                    sb.append(String.format("   Tier: [%-8s]                     %8d BDT\n", item.tier, item.price));
                }
            }

            sb.append("====================================================\n");
            sb.append(String.format("Parts Subtotal                        : %8d BDT\n", bill[1]));
            sb.append(String.format("Service Charge                        : %8d BDT\n", bill[0]));
            sb.append("----------------------------------------------------\n");
            sb.append(String.format("GRAND TOTAL                           : %8d BDT\n", bill[2]));
            sb.append("====================================================\n");
            sb.append("         Thank you for choosing our service!        \n");
            sb.append("====================================================\n");

            return sb.toString();
        }
    }
