
package com.solidnw.gametimer.model;

import java.util.ArrayList;

public class DrawerConstants {
    public static final String ITEM_SELECT_GAME_MODE = "Game Mode";
    public static final String ITEM_GROUP_MANAGEMENT = "Group Management";
    public static final String ITEM_PLAYER_MANAGEMENT = "Player Management";
    public static final String ITEM_STATISTICS = "Statistics";

    public static ArrayList<String> getAllItems() {
        ArrayList<String> allItems = new ArrayList<String>();
        
        allItems.add(ITEM_SELECT_GAME_MODE);
        allItems.add(ITEM_GROUP_MANAGEMENT);
        allItems.add(ITEM_PLAYER_MANAGEMENT);
        allItems.add(ITEM_STATISTICS);

        return allItems;
    }
}
