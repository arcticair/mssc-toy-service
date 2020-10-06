package com.arcticair.mssctoyservice.bootstrap;

import com.arcticair.mssctoyservice.domain.Toy;
import com.arcticair.mssctoyservice.repositories.ToyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Slf4j
@Component
public class ToyLoader implements CommandLineRunner {

    public static final String upc1 = "0000011111";
    public static final String upc2 = "0000011141";
    public static final String upc3 = "0000016111";



    private final ToyRepository toyRepository;

    public ToyLoader(ToyRepository toyRepository) {
        this.toyRepository = toyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadObjects();
    }

    private void loadObjects() {
        if(toyRepository.count() == 0){
            toyRepository.save(Toy.builder()
                    .name("bottle")
                    .style("baby")
                    .minOnHand(1)
                    .upc(upc1)
                    .price(new BigDecimal("33.33"))
                    .build()
                    );
            toyRepository.save(Toy.builder()
                    .name("lego")
                    .style("medium")
                    .minOnHand(1)
                    .upc(upc2)
                    .price(new BigDecimal("323.55"))
                    .build()
            );
            toyRepository.save(Toy.builder()
                    .name("rty")
                    .style("medium")
                    .minOnHand(1)
                    .upc(upc3)
                    .price(new BigDecimal("3.55"))
                    .build()
            );
        }
        log.info("Repo size is " + toyRepository.count());
    }
}
