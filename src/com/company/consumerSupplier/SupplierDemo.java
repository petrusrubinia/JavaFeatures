package com.company.consumerSupplier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Developer obj = factory(Developer::new);
        System.out.println(obj);

        Developer obj2 = factory(() -> new Developer("myname"));
        System.out.println(obj2);
    }

    public static Developer factory(Supplier<? extends Developer> supplier){
        Developer developer = supplier.get();
        if ( developer.getName() == null || "".equals(developer.getName())) {
            developer.setName("default");
        }
        developer.setSalary(BigDecimal.ONE);
        developer.setStart(LocalDate.of(2021,1,1));
        return developer;
    }
}
