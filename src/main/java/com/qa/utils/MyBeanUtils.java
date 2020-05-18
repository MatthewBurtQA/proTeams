package com.qa.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

public class MyBeanUtils {

    //empty constructor
    private MyBeanUtils() {

    }

    //checks if object source is not null, then merges with target
    public static void mergeNotNull(Object source, Object target) throws BeansException {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    //Below is set implementation, the Tadas way was different to this IIRC but that's cool.
    //Had to build this class from Jordans code, cause what even is this

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        Set<String> names = new HashSet<>();
        for (PropertyDescriptor pd : src.getPropertyDescriptors()) {
            if (src.getPropertyValue(pd.getName()) == null)
                names.add(pd.getName());
        }
        return names.toArray(new String[names.size()]);
    }

}