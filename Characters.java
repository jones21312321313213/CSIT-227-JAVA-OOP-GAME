import java.util.Random;

public abstract class Characters {
    protected String name;      // Character's name
    protected int health;       // Character's health points
    protected int defence;      // Character's defence points
    protected String type;      // Type of character (e.g., Mage, Warrior)
    protected int shield = 0;       // Shield value 
    protected int maxHealth;


    // Constructor to initialize character attributes
    public Characters(String name, int health, int defence, String type) {
        this.name = name;       // Set character's name
        this.health = health;   // Set character's initial health
        this.defence = defence; // Set character's defence value
        this.type = type;       // Set character's type
        this.maxHealth = health;
    }

    // Getter for character's name
    public String getName() {
        return name;
    }

    // Getter for character's type
    public String getType() {
        return type;
    }

    // Getter for character's health
    public int getHealth() {
        return health;
    }

    // Getter for character's defence
    public int getDefence() {
        return defence;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }

    // Method to check if the character is alive
    public boolean isAlive() {
        return health > 0; // Returns true if health is greater than 0
    }

    // Method to take damage, applying defence
    public int takeDamage(int damage) {
        //int damageTaken = Math.max(damage - defence, 0); // Calculate damage after defence

        if (damage == 1111111){                        // for 00Kha1
            damage = (int)Math.ceil(health * 0.25);         //-|-
        } else if (damage == 9999999){                      //-|-
            damage = (int)Math.ceil(health * 0.50);         //-|-
        } else if (damage == 10101010){                     //-|-
            damage = health;                                //-|-
        }                                               // for 00Kha1

        if (shield > 0) {
            if (shield > damage) {
                shield -= damage;
                displayWithDelay("Shield blocked the attack!", 150);
                damage = 0;
            } else {
                damage -= shield;
                shield = 0;
                health -= damage;
                displayWithDelay("Shield broken!", 150);
            }
        } else {
            this.health -= damage; // Subtract damage taken from healt
        }

        if (health < 0) {
            health = 0; // Ensure health does not drop below 0
        }
        return damage; // Return the amount of damage taken
    }

    // Abstract methods for character actions to be implemented by subclasses
    public abstract void basicAttack(int res, Characters opponent,int gameMode,int enemyDefence);
    public abstract void skill(int res, Characters opponent,int gameMode,int enemyDefence);
    public abstract void ult(int res, Characters opponent,int gameMode,int enemyDefence);
    public abstract void choices(int res,int gameMode);

    // Method to display text with a delay between words
    public void displayWithDelay(String text, int delayInMillis) {
        String[] words = text.split(" ");  // Split text into words
        for (String word : words) {
            System.out.print(word + " ");   // Print each word followed by a space
            try {
                Thread.sleep(delayInMillis); // Pause for the specified delay
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Restore interrupt status if interrupted
            }
        }
        System.out.println(); // Print a newline after the text
    }

    public int getRandomBetween(int min, int max) {
        Random random = new Random();
        // Generate random number between min (inclusive) and max (inclusive)
        return random.nextInt((max - min) + 1) + min;
    }

    public void heal(int healAmount) {
        this.health += healAmount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }
}
