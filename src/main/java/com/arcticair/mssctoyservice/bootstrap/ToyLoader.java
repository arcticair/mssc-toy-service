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
                    .upc(3452345345L)
                    .price(new BigDecimal("33.33"))
                    .build()
                    );
            toyRepository.save(Toy.builder()
                    .name("lego")
                    .style("medium")
                    .minOnHand(1)
                    .upc(34523345L)
                    .price(new BigDecimal("323.55"))
                    .build()
            );
        }
        log.info("Repo size is " + toyRepository.count());
    }
}
