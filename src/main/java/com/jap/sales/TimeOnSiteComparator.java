package com.jap.sales;


import java.util.Comparator;

public class TimeOnSiteComparator implements Comparator<SalesRecord> {
    @Override
    public int compare(SalesRecord o1, SalesRecord o2) {
        return Double.compare(o1.getTime_on_site(), o2.getTime_on_site());
    }
}
