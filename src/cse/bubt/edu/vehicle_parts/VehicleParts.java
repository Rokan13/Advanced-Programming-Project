package cse.bubt.edu.vehicle_parts;

    import java.util.*;

    public class VehicleParts {

        private static final Map<String, Integer> SERVICE_CHARGES = new LinkedHashMap<>();
        private static final Map<String, Map<String, int[]>> VEHICLE_PARTS_DATA = new LinkedHashMap<>();

        static {
            // ১. Bicycle
            SERVICE_CHARGES.put("Bicycle", 300);
            Map<String, int[]> bicycle = new LinkedHashMap<>();
            bicycle.put("Handlebar (হ্যান্ডেলবার)", new int[]{450, 1200});
            bicycle.put("Grips (গ্রিপস)", new int[]{150, 450});
            bicycle.put("Brake Cable (ব্রেক কেবল)", new int[]{120, 350});
            bicycle.put("Chain (চেইন)", new int[]{350, 950});
            bicycle.put("Sprocket (স্প্রকেট/গিয়ার)", new int[]{500, 1500});
            bicycle.put("Rims (রিম)", new int[]{800, 2500});
            bicycle.put("Tyres (টায়ার)", new int[]{650, 1800});
            bicycle.put("Saddle Seat (সিট)", new int[]{400, 1300});
            VEHICLE_PARTS_DATA.put("Bicycle", bicycle);

            // ২. Standard Motorcycle
            SERVICE_CHARGES.put("Standard Motorcycle", 800);
            Map<String, int[]> stdBike = new LinkedHashMap<>();
            stdBike.put("Side Mirrors (সাইড মিরর)", new int[]{400, 1100});
            stdBike.put("Front Visor (উইন্ড ভাইজার)", new int[]{600, 1500});
            stdBike.put("Fuel Tank Paint (ফুয়েল ট্যাংক কালার)", new int[]{2500, 5500});
            stdBike.put("Handlebar (হ্যান্ডেলবার)", new int[]{750, 1800});
            stdBike.put("Clutch Cable (ক্লাচ কেবল)", new int[]{250, 650});
            stdBike.put("Brake Disc (ব্রেক ডিস্ক)", new int[]{1400, 3200});
            stdBike.put("Brake Pads (ব্রেক প্যাড)", new int[]{450, 1200});
            stdBike.put("Headlight Assembly (হেডলাইট)", new int[]{1800, 4200});
            stdBike.put("Indicators (সাইড লাইট/ইন্ডিকেটর)", new int[]{350, 900});
            stdBike.put("12V Battery (ব্যাটারি)", new int[]{1600, 3200});
            stdBike.put("Bike Seat & Cover (বাইকের সিট ও কভার)", new int[]{900, 2200});
            stdBike.put("Engine Repair & Service (ইঞ্জিন রিপেয়ার ও সার্ভিসিং)", new int[]{3000, 7000});
            VEHICLE_PARTS_DATA.put("Standard Motorcycle", stdBike);

            // ৩. Sports Bike
            SERVICE_CHARGES.put("Sports Bike", 1500);
            Map<String, int[]> sportsBike = new LinkedHashMap<>();
            sportsBike.put("Racing Visor (রেসিং ভাইজার)", new int[]{1800, 4500});
            sportsBike.put("Fairing Body Paint (বডি কালার)", new int[]{6500, 15000});
            sportsBike.put("Clip-on Handlebar (স্পোর্টস হ্যান্ডেলবার)", new int[]{2200, 5500});
            sportsBike.put("ABS Controller (এবিএস মডিউল)", new int[]{8500, 18000});
            sportsBike.put("Brake Caliper (ব্রেক ক্যালিপার)", new int[]{3500, 8500});
            sportsBike.put("Mono Shock Absorber (শক অ্যাবজর্বার)", new int[]{5500, 14000});
            sportsBike.put("Sports Tyres (স্পোর্টস টায়ার)", new int[]{4800, 11500});
            sportsBike.put("Alloy Rims (অ্যালয় রিম)", new int[]{7500, 18500});
            sportsBike.put("Exhaust Pipe (সাইলেন্সার)", new int[]{4500, 13500});
            sportsBike.put("Bike Seat & Cover (বাইকের সিট ও কভার)", new int[]{1500, 3800});
            sportsBike.put("Engine Repair & Service (ইঞ্জিন রিপেয়ার ও সার্ভিসিং)", new int[]{6000, 15000});
            VEHICLE_PARTS_DATA.put("Sports Bike", sportsBike);

            // ৪. Sedan Car
            SERVICE_CHARGES.put("Sedan Car", 2500);
            Map<String, int[]> sedan = new LinkedHashMap<>();
            sedan.put("Front Windshield Glass (সামনের গ্লাস)", new int[]{8500, 18000});
            sedan.put("Rear Glass (পেছনের গ্লাস)", new int[]{6500, 14000});
            sedan.put("Body Paint (বডি কালার)", new int[]{18000, 40000});
            sedan.put("Steering Wheel (স্টিয়ারিং হুইল)", new int[]{4500, 11000});
            sedan.put("Brake Pads (ব্রেক প্যাড)", new int[]{2500, 6000});
            sedan.put("Brake Rotors (ব্রেক রোটার/ডিস্ক)", new int[]{4800, 11500});
            sedan.put("Suspension Struts (শক অ্যাবজর্বার)", new int[]{7500, 17500});
            sedan.put("Headlight Pair (হেডলাইট)", new int[]{6500, 16000});
            sedan.put("Horn Unit (হর্ন)", new int[]{800, 2200});
            sedan.put("Car Battery (ব্যাটারি)", new int[]{6500, 12500});
            sedan.put("Car Seat & Cover (সিট ও কভার)", new int[]{7000, 18000});
            sedan.put("Engine Repair & Service (ইঞ্জিন রিপেয়ার ও সার্ভিসিং)", new int[]{12000, 28000});
            VEHICLE_PARTS_DATA.put("Sedan Car", sedan);

            // ৫. Sports Car / Race Car
            SERVICE_CHARGES.put("Sports Car / Race Car", 5000);
            Map<String, int[]> raceCar = new LinkedHashMap<>();
            raceCar.put("Racing Windshield (স্পোর্টস গ্লাস)", new int[]{18000, 42000});
            raceCar.put("Carbon Fiber Paint (স্পেশাল কালার)", new int[]{45000, 110000});
            raceCar.put("Racing Steering Wheel (রেসিং স্টিয়ারিং)", new int[]{15000, 38000});
            raceCar.put("Carbon Ceramic Brakes (রেসিং ব্রেক)", new int[]{35000, 85000});
            raceCar.put("Sports Suspension (স্পোর্টস শক)", new int[]{28000, 68000});
            raceCar.put("High Decibel Horn (স্পোর্টস হর্ন)", new int[]{2500, 6500});
            raceCar.put("Spoilers (স্পয়লার/রিয়ার উইং)", new int[]{14000, 35000});
            raceCar.put("NOS Kit (নাইট্রাস অক্সাইড কিট)", new int[]{32000, 75000});
            raceCar.put("Racing Tyres (রেসিং টায়ার)", new int[]{24000, 58000});
            VEHICLE_PARTS_DATA.put("Sports Car / Race Car", raceCar);

            // ৬. Microbus / Van
            SERVICE_CHARGES.put("Microbus / Van", 3000);
            Map<String, int[]> van = new LinkedHashMap<>();
            van.put("Front Windshield (সামনের গ্লাস)", new int[]{11000, 22000});
            van.put("Sliding Door Glass (সাইড ডোর গ্লাস)", new int[]{5500, 12000});
            van.put("Exterior Paint (বডি কালার)", new int[]{24000, 50000});
            van.put("Power Steering Wheel (পাওয়ার স্টিয়ারিং)", new int[]{8500, 19000});
            van.put("AC Compressor (এসি কম্প্রেসর)", new int[]{14000, 30000});
            van.put("Brake Shoes (ব্রেক শু)", new int[]{3200, 7500});
            van.put("Suspension Springs (সাসপেনশন স্প্রিং)", new int[]{8000, 18000});
            van.put("Van Tyres (ভ্যান টায়ার)", new int[]{9500, 20000});
            VEHICLE_PARTS_DATA.put("Microbus / Van", van);

            // ৭. Passenger Bus
            SERVICE_CHARGES.put("Passenger Bus", 5500);
            Map<String, int[]> bus = new LinkedHashMap<>();
            bus.put("Front Shield Glass (সামনের বড় গ্লাস)", new int[]{22000, 45000});
            bus.put("Passenger Windows (প্যাসেঞ্জার উইন্ডো গ্লাস)", new int[]{6500, 14000});
            bus.put("Coach Paint (বাস বডি কালার)", new int[]{45000, 95000});
            bus.put("Large Steering Wheel (বাস স্টিয়ারিং)", new int[]{12000, 26000});
            bus.put("Air Brake Valve (এয়ার ব্রেক)", new int[]{9500, 21000});
            bus.put("Air Horn Unit (এয়ার হর্ন)", new int[]{3500, 8500});
            bus.put("Air Suspension Bellows (এয়ার শক)", new int[]{16000, 35000});
            bus.put("Heavy Bus Tyres (বাসের টায়ার)", new int[]{25000, 52000});
            VEHICLE_PARTS_DATA.put("Passenger Bus", bus);

            // ৮. Truck / Cargo Vehicle
            SERVICE_CHARGES.put("Truck / Cargo Vehicle", 6000);
            Map<String, int[]> truck = new LinkedHashMap<>();
            truck.put("Cabin Windshield (ক্যাবিন গ্লাস)", new int[]{16000, 34000});
            truck.put("Cabin Paint (ক্যাবিন কালার)", new int[]{35000, 75000});
            truck.put("Truck Steering Wheel (ট্রাক স্টিয়ারিং)", new int[]{11000, 24000});
            truck.put("Drum Brakes (ড্রাম ব্রেক)", new int[]{12500, 27000});
            truck.put("Leaf Springs (হেভি পাতা লিফ স্প্রিং)", new int[]{18000, 38000});
            truck.put("Heavy Towing Hook (টোয়িং হুক)", new int[]{5500, 13000});
            truck.put("Truck Tyres (ট্রাক টায়ার)", new int[]{28000, 58000});
            VEHICLE_PARTS_DATA.put("Truck / Cargo Vehicle", truck);
        }

        // ১. ৮টি গাড়ির নামের লিস্ট রিটার্ন করবে
        public static List<String> getAllVehicles() {
            return new ArrayList<>(VEHICLE_PARTS_DATA.keySet());
        }

        // ২. নির্দিষ্ট গাড়ির পার্টসগুলোর লিস্ট রিটার্ন করবে
        public static List<String> getVehicleParts(String vehicleName) {
            Map<String, int[]> parts = VEHICLE_PARTS_DATA.get(vehicleName);
            if (parts != null) {
                return new ArrayList<>(parts.keySet());
            }
            return new ArrayList<>();
        }

        // ৩. নির্দিষ্ট গাড়ির নির্দিষ্ট পার্টসের Standard (index 0) বা Premium (index 1) দাম দেবে
        public static int getPartPrice(String vehicleName, String partName, String tier) {
            Map<String, int[]> parts = VEHICLE_PARTS_DATA.get(vehicleName);
            if (parts != null && parts.containsKey(partName)) {
                int[] prices = parts.get(partName);
                return "Premium".equalsIgnoreCase(tier) ? prices[1] : prices[0];
            }
            return 0;
        }

        // ৪. গাড়ি অনুযায়ী সার্ভিসিং চার্জ রিটার্ন করবে
        public static int getServiceCharge(String vehicleName) {
            return SERVICE_CHARGES.getOrDefault(vehicleName, 0);
        }
    }
