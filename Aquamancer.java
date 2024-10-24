import java.util.*;
public class Aquamancer extends Characters {

    public Aquamancer(int res) {
        super("Aquamancer", 80, 5, "Mana", res);
    }

    @Override
    public void basicAttack(int res, Characters opponent) {
        if (res < 0) {
            res = 0;
        }
        if (res < 2) {
            System.out.println("Insufficient Mana! Please Switch Character or END TURN!");
            return;
        }
        int damage = getRandomBetween(0, 5); // Damage dealt by the attack
        // Display the attack message
        displayWithDelay(super.getName() + " conjures a concentrated energy blast and launches it at the enemies!", 150);
        displayWithDelay("The blast strikes the enemies, dealing " + damage + " magic damage.", 150);
        displayWithDelay("You now have " + res + " mana left.", 150);
        opponent.takeDamage(damage);
    }

    @Override
    //TODO    
    // Implement additional logic to apply the shieldAmount to an ally's shield here
    public void skill(int res, Characters opponent) {
        if (res < 0) {
            res = 0;
        }
    
        if (res < 5) {
            System.out.println("Insufficient Mana! Please Switch Character or END TURN!");
            return;
        }
    
        int damage = 1; // Damage dealt by the attack
        int shieldAmount = getRandomBetween(1, 3); // Amount of shield provided to an ally
        // Display the attack and support messages
        displayWithDelay(super.getName() + " weaves a magical spell and launches it at the enemies!", 150);
        displayWithDelay("The spell strikes the enemies, dealing " + damage + " magic damage.", 150);
        displayWithDelay("In addition, " + super.getName() + " grants a protective shield of " + shieldAmount + " points to an ally!", 150);
        displayWithDelay("You now have " + res + " mana left.", 150);
    }

    @Override
    // TODO
    // IMPLEMENT THE AOE ULT which is heal
    public void ult(int res, Characters opponent) {
        if (res < 8) { 
            displayWithDelay("Insufficient Mana or Energy to perform the AoE Heal! Please END TURN!", 150);
            return;
        }
        res -= 8;
    
        // Display the AoE heal message
        displayWithDelay(super.getName() + "channels their power and releases a wave of healing energy!", 150);
    }

    @Override
    public void switchCharacter(int res) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
     public int getRandomBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min should be less than or equal to Max");
        }
        Random random = new Random();
        // Generate random number between min (inclusive) and max (inclusive)
        return random.nextInt((max - min) + 1) + min;
    }


    @Override
    public void choices( int res) {
        System.out.println("\nYour current character: " + super.getName() + " (Health: " + super.getHealth() + " | Defence: " + super.getDefence() + " | " + this.type + ": " + res + ")");
        System.out.println("\nChoose Attack: ");
        System.out.println("1) Basic Attack (Cost: 2 Mana )");
        System.out.println("2) Skill (Cost: 5 Mana)");
        System.out.println("3) Ultimate Skill (Cost: 8 Mana)");
        System.out.println("4) Switch Character");
        System.out.println("5) Reroll (For demonstration)");
        System.out.println("6) End Turn");
        System.out.print("\nYour Choice: ");
    }
  
    
}
