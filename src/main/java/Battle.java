public class Battle {

    public static int calculateResult()
    {
        // unit A  = 5x models
        // tesla carbine
        // 2A BS3+ S5 AP0 D1

        // GaussBlaster
        // 2A 3+ S5 AP-1 D1

        // unit B = 5 x terminators
        // T4, W2, 2+, 5++

        int modelsKilled = 0;

        Float woundsCaused = 0F;

        woundsCaused = Calculate.calculateWounds((Calculate.calculateHits(3,10)),5,4);

        // calculate distribution of number of hits
        // based on that aggregate distribution, calculate distribution of wound rolls
        // based on wound roll rdistribution, calculate models killed ->  for each wound roll, is it saved?
        // if it is saved, go to next.
        // if it is not saved, how much damage does the unit have -> if zero, kill the model.  if > zero, wound the model and resolve more wounds starting with the currently wounded one.

        // todo handle multi-wound weapons
//
//        Float failedSaves = Calculate.calculateUnsavedWounds(woundsCaused,4,0,0);
//        System.out.println(failedSaves);

        return modelsKilled;

    }
}
