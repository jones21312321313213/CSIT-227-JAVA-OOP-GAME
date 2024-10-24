public abstract class Characters {
    protected String name;      // Character's name
    protected int health;       // Character's health points
    protected int defence;      // Character's defence points
    protected String type;      // Type of character (e.g., Mage, Warrior)
    protected int res;          // Resource or special attribute related to the character's skills

    // Constructor to initialize character attributes
    public Characters(String name, int health, int defence, String type, int res) {
        this.name = name;       // Set character's name
        this.health = health;   // Set character's initial health
        this.defence = defence; // Set character's defence value
        this.type = type;       // Set character's type
        this.res = res;         // Set character's resource value
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

    // Getter for character's resource
    public int getRes() {
        return res;
    }

    // Method to check if the character is alive
    public boolean isAlive() {
        return health > 0; // Returns true if health is greater than 0
    }

    // Method to take damage, applying defence
    public int takeDamage(int damage) {
        //int damageTaken = Math.max(damage - defence, 0); // Calculate damage after defence
        this.health -= damage; // Subtract damage taken from health
        if (health < 0) {
            health = 0; // Ensure health does not drop below 0
        }
        return damage; // Return the amount of damage taken
    }

    // Abstract methods for character actions to be implemented by subclasses
    public abstract void basicAttack(int res, Characters opponent);
    public abstract void skill(int res, Characters opponent);
    public abstract void ult(int res, Characters opponent);
    public abstract void switchCharacter(int res);
    public abstract int getRandomBetween(int min, int max);
    public abstract void choices(int res);

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

    public void heal(int healAmount) {
        this.health += healAmount;
    }
}
