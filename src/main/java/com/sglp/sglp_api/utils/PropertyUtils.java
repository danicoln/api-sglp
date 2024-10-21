package com.sglp.sglp_api.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyUtils {

    public static <T> void copyNonNullProperties(T source, T target, String... ignoredProperties) {
        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper trg = new BeanWrapperImpl(target);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        List<String> ignoreList = new ArrayList<>(Arrays.asList(ignoredProperties));

        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            Object propertyValue = src.getPropertyValue(propertyName);

            if (propertyValue != null
                    && trg.isWritableProperty(propertyName)
                    && !ignoreList.contains(propertyName)) {
                trg.setPropertyValue(propertyName, propertyValue);
            }
        }
    }
}
