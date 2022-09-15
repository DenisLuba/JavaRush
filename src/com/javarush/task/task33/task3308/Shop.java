package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    @XmlElement
    public Goods goods;

    @XmlElement
    public int count;
    @XmlElement
    public double profit;
    @XmlElement
    public String[] secretData;

    @XmlType(name = "goods")
    @XmlRootElement
    public static class Goods {
        @XmlElement
        public List<String> names;

        @Override
        public String toString() {
            return "Goods{" +
                    "names=" + names +
                    '}';
        }
    }

    @Override
    public String  toString() {
        return "Shop{" +
                "count=" + count +
                ", profit=" + profit +
                ", secretData=" + Arrays.toString(secretData) +
                ", goods=" + goods +
                '}';
    }
}
