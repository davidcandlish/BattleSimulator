public class RunBattle {

    public static void main (String[] args)
    {
        Integer modelsKilled =0;
        Float woundsCaused =0F;
        System.out.println("Running battle with 1 simulation");
        modelsKilled = Battle.calculateResult();

        System.out.println("Unit A killed "  +modelsKilled + " models");




    }

}
