package com.pratik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Frosting frosting(){
        return new ChocolateFrosting();
    }

    @Bean
    public Syrup syrup(){
        return new ChocolateSyrup();
    }

    @Bean
    public CakeBaker cakeBaker(Frosting frosting,Syrup syrup){
        return new CakeBaker(frosting,syrup);
    }
}
