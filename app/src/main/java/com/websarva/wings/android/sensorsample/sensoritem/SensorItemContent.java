package com.websarva.wings.android.sensorsample.sensoritem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SensorItemContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<SensorItem> ITEMS = new ArrayList<SensorItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, SensorItem> ITEM_MAP = new HashMap<String, SensorItem>();


    public static void addContent(String content, int type) {
        addItem(new SensorItem(String.valueOf(ITEMS.size()),content,type));
    }

    public static void removeContent() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static void addItem(SensorItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.index, item);
    }

//    private static SensorItem createSensorItem(int position) {
//        return new SensorItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SensorItem {
        public final String index;
        public final String content;
        public final int type;

        public SensorItem(String index, String content, int type) {
            this.index = index;
            this.content = content;
            this.type = type;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
