package org.rag.eval;



import java.util.List;

public class GroundTruthData {

    public static  List<String> GROUND_TRUTH_DOCS = List.of(

            // ---------------------- APPLIANCES (20) ----------------------
            "Refrigerator RF1234XYZ: Store perishable items on top shelf, keep dairy in compartments, place meat at bottom, seal leftovers, separate fruits and vegetables.",
            "Refrigerator RF8899: Set cooling at 4°C, avoid overloading shelves, clean freezer monthly, keep drinks on door shelf.",
            "Refrigerator RF5555: Discontinued, replaced by RF1234XYZ with better cooling.",
            "Washing Machine WM786HHYY: Open door, load clothes, add detergent, close door, select wash mode, press start.",
            "Washing Machine WM9000: Use eco-mode for energy saving, dry drum after wash to prevent mold.",
            "Washing Machine WMX220: Clean lint filter after every cycle, use mild detergent, avoid hot washes for wool.",
            "Dishwasher XY7788: Rinse utensils manually, load racks properly, choose intensive mode for greasy dishes.",
            "Dishwasher DX9900: Use salt refill every month, avoid mixing steel and aluminum utensils.",
            "Microwave MW9900: Use microwave-safe containers, set P50 power for reheating rice, defrost meat for 5 minutes per 500g.",
            "Microwave MW7777: Use grill mode for crisping, avoid using metal containers, wipe door seals weekly.",
            "Air Conditioner AC7766: Set temp to 22°C for cooling, clean filters monthly, close all windows for efficiency.",
            "Air Conditioner AC5544: Use sleep mode at night, clean outdoor unit every 6 months.",
            "Vacuum Cleaner VC4455: Plug power, choose suction mode, clean carpets first, empty dustbin after use.",
            "Vacuum Cleaner VC9999: Replace HEPA filter every 3 months, avoid wet surfaces, use turbo mode for pets.",
            "Oven OV3344: Preheat 10 minutes before baking, avoid opening door frequently, clean racks monthly.",
            "Oven OV5678: Use convection mode for faster cooking, set fan mode for even heating.",
            "Water Purifier WP1111: Change filters every 6 months, flush system weekly, avoid overfilling tank.",
            "Water Purifier WP2222: UV mode kills bacteria, use sediment filter for hard water.",
            "Geyser GY5566: Keep temperature below 55°C, service heating rods annually.",
            "Geyser GY8899: Avoid dry heating, ensure pressure release valve works properly.",

            // ---------------------- GADGETS (15) ----------------------
            "iPhone 15: Enable optimized charging to preserve battery health, update iOS regularly, avoid deep discharges.",
            "Samsung Galaxy S24: Use adaptive refresh rate, disable background apps for better performance.",
            "MacBook Pro M3: Use battery saver mode, clean vents, avoid blocking fans, calibrate battery quarterly.",
            "Dell XPS 13: Enable performance cooling, update BIOS for stability improvements.",
            "Lenovo ThinkPad X1: Use rapid charge mode sparingly, enable fingerprint security.",
            "Apple Watch Ultra: Enable night charging optimization, use low-power mode for workouts.",
            "Fitbit Sense: Sync data daily, replace straps every 6 months, avoid exposing to hot water.",
            "Sony WH-1000XM5: Update firmware for noise cancellation improvements, avoid folding earcups forcefully.",
            "AirPods Pro 2: Enable adaptive audio, clean ear tips weekly, store in case when unused.",
            "OnePlus 12: Enable RAM boost, use battery health optimization, disable auto brightness.",
            "Pixel 9: Enable call screening, activate Extreme Battery Saver, update security patches.",
            "iPad Pro M4: Use stage manager for multitasking, disable unused background apps.",
            "Samsung Galaxy Tab S9: Use DeX mode for desktop experience, enable secure folder for sensitive data.",
            "Garmin Forerunner 965: Use training readiness score, sync GPS weekly for accuracy.",
            "Oculus Quest 3: Enable developer mode, clean lenses with microfiber, avoid direct sunlight exposure.",

            // ---------------------- TROUBLESHOOTING (15) ----------------------
            "Dishwasher Error E12: Check water inlet pipe, clean filter mesh, reset control board.",
            "Refrigerator Not Cooling: Clean condenser coils, check thermostat, ensure door seals properly.",
            "Washing Machine Error F07: Drain pump clogged, check inlet valves, reset machine.",
            "Air Conditioner Leaking Water: Clean condensate tray, unclog drain pipe, check refrigerant levels.",
            "Microwave Not Heating: Replace magnetron, ensure door sensor works, avoid metal trays.",
            "Laptop Overheating: Use cooling pad, repaste thermal compound, disable turbo boost.",
            "Phone Battery Draining Fast: Check rogue apps, enable power saver, calibrate battery.",
            "Router Wi-Fi Disconnects: Update firmware, change channel, check ISP logs.",
            "Smart TV Not Connecting to Wi-Fi: Reset TV network settings, restart router, update software.",
            "Bluetooth Pairing Failed: Reset device, delete saved pairings, retry with airplane mode toggle.",
            "Printer Paper Jam: Remove jammed paper carefully, clean rollers, align guides.",
            "Car Battery Dead: Jump start using booster cables, check alternator, replace battery if weak.",
            "Tesla Model Y Firmware Update: Ensure Wi-Fi connectivity, free storage space, retry after reboot.",
            "Xbox Series X No Display: Reset HDMI port, change cable, factory reset console.",
            "PlayStation Controller Drift: Clean joystick sensor, recalibrate controller, update firmware.",

            // ---------------------- COOKING RECIPES (15) ----------------------
            "Paneer Butter Masala Recipe: Marinate paneer, sauté onions, add tomatoes, blend gravy, finish with cream.",
            "Pasta Carbonara Recipe: Boil pasta, cook bacon, mix egg yolk sauce, add parmesan, serve hot.",
            "Veg Biryani Recipe: Fry onions, add marinated vegetables, layer rice, slow cook for 20 minutes.",
            "Chicken Curry Recipe: Marinate chicken, fry spices, simmer in coconut milk, garnish with coriander.",
            "Dosa Batter Recipe: Soak rice & dal, grind smooth, ferment overnight, make thin dosas.",
            "Cheesecake Recipe: Crush biscuits, mix cream cheese, bake at 160°C, chill before serving.",
            "Tandoori Chicken Recipe: Marinate chicken in yogurt & spices, bake at 220°C for 30 minutes.",
            "Miso Soup Recipe: Boil dashi stock, add miso paste, tofu cubes, scallions, simmer lightly.",
            "Caesar Salad Recipe: Toss lettuce, add parmesan, drizzle Caesar dressing, top with croutons.",
            "Chocolate Brownie Recipe: Melt butter, mix sugar & cocoa, bake at 180°C for 25 minutes.",
            "Ramen Noodles Recipe: Boil broth, cook noodles, add toppings like egg & pork slices.",
            "Pav Bhaji Recipe: Mash vegetables, cook with butter & spices, serve with toasted buns.",
            "Sushi Roll Recipe: Place rice on nori, add fillings, roll tightly, slice evenly.",
            "French Omelette Recipe: Beat eggs, cook on low heat, fold gently, serve soft.",
            "Tiramisu Recipe: Dip biscuits in coffee, layer mascarpone cream, refrigerate overnight.",

            // ---------------------- HEALTH & WELLNESS (10) ----------------------
            "Healthy Sleep Tips: Sleep 7-9 hours, avoid caffeine after 6 PM, maintain consistent timings.",
            "Keto Diet Plan: High fats, moderate proteins, low carbs, avoid processed sugar.",
            "Yoga for Back Pain: Practice cat-cow pose, downward dog, and cobra stretch daily.",
            "Meditation Guide: Start with 10 minutes daily, focus on breath, avoid distractions.",
            "HIIT Workout Plan: 20 mins of alternating high & low-intensity exercises, 3x weekly.",
            "Heart Health Tips: Avoid trans fats, eat leafy greens, exercise 30 minutes daily.",
            "Skin Care Routine: Cleanse, exfoliate twice weekly, moisturize, use SPF daily.",
            "Diabetes Management: Monitor sugar, eat low-GI foods, maintain hydration.",
            "Intermittent Fasting: 16:8 method, avoid snacks, hydrate regularly.",
            "Mental Health Tips: Limit screen time, maintain social connections, practice gratitude.",

            // ---------------------- DISTRACTORS (15) ----------------------
            "Canada population in 2025 is estimated at 40M.",
            "India's GDP growth expected to hit 6.5% in 2025.",
            "FIFA World Cup 2026 venues finalized across North America.",
            "Apple’s stock price crosses $300 in 2025 Q2.",
            "Amazon to launch new AI-powered warehouse robots.",
            "SpaceX schedules Mars cargo mission for 2027.",
            "Bitcoin price volatility increases due to regulation changes.",
            "Top 10 tourist destinations in Europe for 2025.",
            "Hollywood strikes affect film production timelines.",
            "Meta introduces new AR glasses with holographic display.",
            "Tesla opens Gigafactory in Canada for battery production.",
            "NASA’s Artemis III mission delayed to 2026.",
            "IMF warns about global inflationary pressures.",
            "COP30 climate summit focuses on carbon neutrality goals.",
            "UN announces global education initiative for AI literacy."
    );
}
