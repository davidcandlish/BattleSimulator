import javax.swing.*;

public class Calculate {


    public static int calculateModelsKilled ()
    {
        int someint = 0;
        return someint;
    }

//    public static int calculateDamage (Float savesFailed, int weaponDmg, int modelWounds, int modelsInUnit)
//    {
//        int modelsDestroyed = 0;
//        int intSavesFailed = (int) Math.floor(savesFailed);
//        int modelsRemaining = modelsInUnit;
//        int saveCounter = intSavesFailed;
//        // todo rework this loop it's not counting the number of failed saves correctly
//        // need to stop when we have applied all failed saves
//        while (saveCounter > 0) {
//            for (int i = 0; i < intSavesFailed; i++) {
//
//                int currModelWounds = modelWounds;
//
//                if(currModelWounds > 0) {
//
//                    while (currModelWounds > 0) {
//                        currModelWounds = currModelWounds - weaponDmg;
//                        saveCounter--;
//                    }
//                    modelsDestroyed++;
//                }
//
//            }
//        }
//        System.out.println("A total of " + modelsDestroyed + " models were destroyed");
//
//        return modelsDestroyed;
//    }

        public static float calculateUnsavedWounds (float woundsTaken, int armourSave, int invulnSave, int weaponAP)
    {
        float savesFailed = 0F;

        savesFailed = (woundsTaken * calculateOddsD6(calculateSaveThrow(armourSave,weaponAP,invulnSave)));

        return savesFailed;
    }

    public static int calculateSaveThrow(int armourSave, int weaponAP, int invulnSave)
    {
        int adjustedSave =0;

        if (armourSave + weaponAP <= 6) {
            adjustedSave = (armourSave + weaponAP);
        } else if (invulnSave != 0)
        {
            return invulnSave;
        }

        return adjustedSave;
    }
    public static float calculateOddsD6 (int dieRoll)
    {
        if(dieRoll < 1 || dieRoll > 6)
        {
            throw new IllegalArgumentException("Invalid dieRoll");
        }
        int favourableOutcomes = 6 - dieRoll + 1;
        return (float) favourableOutcomes / 6;
    }
    public static float calculateHits (Integer toHit, Integer numAttacks)
    {
        
        float numHits =  (calculateOddsD6(toHit) * numAttacks);
        //System.out.println("Hitting on " + toHit + "s...");

        String formattedHits = String.format("%.1f",numHits);
        System.out.println(formattedHits + " hits scored");

        return numHits;
    }
    
    public static float calculateWounds (Float numHits, Integer hitStrength, Integer toughness)
    {
        
        float numWounds = 0F;

         int comparisonResult = compareStrengthToughness(hitStrength, toughness);

         switch (comparisonResult)
         {
             case 0:
                 System.out.println("Wound on 4s");
                 numWounds = (numHits * (calculateOddsD6(4)));
                 break;
             case 1:
                 System.out.println("Strength is greater than toughness but less than double toughness.");
                 System.out.println("Wound on 3s");
                 numWounds = (numHits * (calculateOddsD6(3)));
                 break;
             case 2:
                 System.out.println("Strength is double or more than toughness.");
                 System.out.println("Wound on 2s");
                 numWounds = (numHits * (calculateOddsD6(2)));
                 break;
             case 3:
                 System.out.println("Toughness is double strength.");;
                 numWounds = (numHits * (calculateOddsD6(6)));
                 System.out.println("Wound on 6s");
                 break;
             case 4:
                 System.out.println("Toughness is greater than strength but less than double strength.");;
                 numWounds = (numHits * (calculateOddsD6(5)));
                 System.out.println("Wound on 5s");
                 break;
             default:
                 System.out.println("Other conditions not met.");
         }

        return numWounds;
    }

    private static int compareStrengthToughness (int str, int tou) {
        if (str == tou)
        {return 0;}
        else if (tou > str && tou < str * 2) {
            return 4; }
        else if (str > tou && str < tou * 2) {
            return 1; }
        else if (str >= tou*2) {
            return 2;
        } else if (tou >= str*2) {
            return 3;
        } else {
            return -1;
        }

        }

}
