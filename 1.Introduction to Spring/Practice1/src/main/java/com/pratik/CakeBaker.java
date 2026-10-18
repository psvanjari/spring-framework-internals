package com.pratik;

public class CakeBaker {
    private Frosting frosting;
    private Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake(){
        System.out.println("Baking cake...");
        System.out.println(
                "Using:"+frosting.getFrostingType()
                +"and"
                +syrup.getSyrupType()
        );

        System.out.println("Cake is ready");
    }
}
