@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDate.class, 
        value=LocalDateAdapter.class),
    @XmlJavaTypeAdapter(type=LocalDateTime.class, 
        value=LocalDateTimeAdapter.class),
})
package com.main.logic.dts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;