import java.util.Random;

public class VerdantWarden extends Character {
    private int health = 75;
    private int defence = 3;
    private int roll;
    private String type = "Spirits";

    @Override
    public void setHealth(int health){
        this.health = health;
    }

    @Override
    public int getHealth(){
        return health;
    }

    @Override
    public void setDefence(int defence){
        this.defence = defence;
    }
    @Override
    public int getDefence(){
        return defence;
    }

    @Override
    public void setRolls(int roll){
        this.roll = roll;
    }

    @Override
    public int getRolls(){
        return roll;
    }
    
    @Override
    public void setType(String type) {
      this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int takeDamage(int damage){
        return health-=damage;
    }

    @Override
    public int basicAttack(String chars, int res) {
        if (res < 0) {
            res = 0;
        }
    
        int damage = getRandomBetween(0, 7); // Adjusted damage range for a support type with an attack
    
        if (res < 2) {
            System.out.println("Insufficient Spirit or Energy! Please Switch Character or END TURN!");
            return 0; // No damage if not enough resources
        } else {
            res -= 2; // Cost for the basic attack
    
            // Display the attack message
            displayWithDelay(chars + " calls upon nature's strength, drawing their bow to unleash a vibrant arrow!", 150);
            displayWithDelay("The arrow flies true, dealing " + damage + " damage to the enemies.", 150);
            displayWithDelay("You now have " + res + " stamina/energy left.", 150);
        }
    
        return damage; // Return the damage dealt
    }
    



    @Override
    //TODO IMPLEMENT HEAL AND ATTACK
    public int skill(String chars, int res) {
        int damage = 15; // Fixed damage for enemies
        int healAmount = 10; // Fixed healing for allies
       

        if (res < 5) {
            System.out.println("Insufficient Spirit or Energy! Please Switch Character or END TURN!");
            return 0; // No damage or healing if not enough resources
        } else {
            res -= 5; // Cost for the skill
    
            // Display the AoE attack and healing messages
            displayWithDelay(chars + " channels nature's energy, unleashing a flurry of arrows!", 150);
            displayWithDelay("Each enemy takes " + damage + " damage from the barrage!", 150);
            displayWithDelay("All allies receive " + healAmount + " health from the soothing energy!", 150);
            displayWithDelay("You now have " + res + " stamina/energy left.", 150);
        }
    

    
        return damage;
    }
    
    

    @Override
    public int ult(String chars, int res) {
        int damage = getRandomBetween(10,20); // Damage range for the ultimate skill
        int healAmount = 20; // Fixed healing for allies
        int shieldAmount = 1; // Fixed shield amount

        if (res < 8) {
            System.out.println("Insufficient Stamina or Energy! Please Switch Character or END TURN!");
            return 0; // No damage or healing if not enough resources
        } else {
            res -= 8; // Cost for the ultimate skill
    
            // Display the ultimate attack and healing messages
            displayWithDelay(chars + " calls upon the power of nature, preparing their ultimate technique!", 150);
            displayWithDelay("They unleash a devastating strike, dealing " + damage + " damage to a single target!", 150);
            displayWithDelay("In addition, all allies receive " + healAmount + " health and gain a shield of " + shieldAmount + " points!", 150);
            displayWithDelay("You now have " + res + " stamina/energy left.", 150);
        }  
        return damage; // Return the damage dealt to the single target
    }
    
    

    @Override
    public int switchCharacter(String chars,int res){
        if (res < 1) {
            displayWithDelay("Insufficient Spirit or Energy to switch! Please END TURN!", 150);
            return 0;
        } else {
            res -=1;
            displayWithDelay(chars + " calls for reinforcements! A new character is ready to fight!", 150);
            displayWithDelay("You now have " + res + " stamina/energy left.", 150);
        }
        return 0;
    }
}
