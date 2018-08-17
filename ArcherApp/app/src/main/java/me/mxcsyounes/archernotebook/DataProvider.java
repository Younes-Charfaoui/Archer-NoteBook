package me.mxcsyounes.archernotebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public final class DataProvider {

    private static List<Adjustment> adjustmentList = new ArrayList<>();

    static {
        adjustmentList.add(new Adjustment(1, 2, "6.2", "1.3", new Date(),
                null, "Hello world"));
        adjustmentList.add(new Adjustment(1, 1, "11", "1.2", new Date(),
                null, "Hello algeria"));
        adjustmentList.add(new Adjustment(1, 3, "8", "4", new Date(),
                null, "Hello merrakch"));
        adjustmentList.add(new Adjustment(1, 4, "6", "12", new Date(),
                null, "Hello tiaret"));


    }

    public static List<Adjustment> getAdjustmentList() {
        return adjustmentList;
    }
}
