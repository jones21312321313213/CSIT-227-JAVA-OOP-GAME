import java.util.Random; 
import java.util.Scanner;

public class Player extends Choices { 
    private String name;
    private final Characters[] chosenCharacters = new Characters[3]; // Array to store selected Characters
    private final Characters[] characters; // Array of available characters
    private int index; // Index of the currently active character
    private final String[] type = {"Stamina","Mana","Spirit"};
  
    public Player(String name, Characters[] characters) {
        this.name = name; // Initialize player's name
        this.characters = characters; // Assign the characters array passed to the constructor
        this.index = 0; // Set initial character index to 0
    }

    // Getters
    public String getName() {
        return name; // Return player's name
    }

    public Characters getCurrentCharacter() {
        return characters[index]; // Return the currently active character
    }

    // Method to return chosen Characters
    public Characters[] getChosenChar() {
        return this.chosenCharacters; // Return the array of chosen Characters
    }


    // Switch character if valid
    public boolean switchCharacter(int index) {
        if (index >= 0 && index < characters.length && characters[index].isAlive()) {
            this.index = index; // Switch to the new character if it's alive
            return true; // Successful switch
        } else {
            System.out.println("Cannot switch to that character. Either it's dead or invalid."); // Error message
            return false; // Unsuccessful switch
        }
    }

    // Check if the player has any alive characters
    public boolean hasAliveCharacters() {
        for (Characters character : characters) {
            if (character.isAlive()) {
                return true; // Return true if any character is alive
            }
        }
        return false; // No alive characters found
    }

    // Method to take damage
    public void damageTaken(int damage) {
        Characters currentCharacter = getCurrentCharacter(); // Get the current character
        if (currentCharacter != null && currentCharacter.isAlive()) {
            currentCharacter.takeDamage(damage); // Apply damage to the current character
            System.out.println(currentCharacter.getName() + " takes " + damage + " damage."); // Print damage info
        } else {
            System.out.println("Current character is already dead or invalid."); // Error message
        }
    }

    
    public void printAllCharacterStatus(int[] res) {
        for (int i = 0; i < characters.length; i++) {
            if (characters[i].isAlive()) {
                // If character is alive, print health and resource
                System.out.println((i + 1) + ". " + characters[i].getName() + ": " 
                    + characters[i].getHealth() + " HP, " + this.type[i] + ": " + res[i]);
            } else {
                // If character is dead, print "Dead" instead of health
                System.out.println((i + 1) + ". " + characters[i].getName() + ": Dead, " + this.type[i] +": " + res[i]);
            }
        }
    }

    public void healAllCharacters(int healAmount) {
        for (Characters character : characters) {
            if (character.isAlive()) {
                character.heal(healAmount);
            }
        }
    }

    

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Generate random numbers that sum up to 10
   

    public int[] generateNumbers() {
        Random random = new Random(); // Create a Random object
        int[] numbers = new int[3]; // Array to hold three numbers
        int sum = 10; // We want the sum of the three numbers to be exactly 10
    
        // Generate two random numbers between 0 and the remaining sum
        for (int i = 0; i < 2; i++) {
            numbers[i] = random.nextInt(Math.min(sum + 1, 11)); // Ensure number does not exceed remaining sum or 10
            sum -= numbers[i]; // Subtract the generated number from the sum
        }
    
        // The third number should be the remainder of the sum to ensure total sum is 10
        numbers[2] = sum; // Assign remaining sum to the third number
    
        return numbers; // Return the generated numbers
    }
    

    // Wish method to receive random resources
    public int[] wish() {
        System.out.println("\nPress Enter key to wish..."); // Prompt for user input
        Scanner scan = new Scanner(System.in); // Create a Scanner for input
        scan.nextLine(); // Wait for user to press Enter
        int[] result = generateNumbers(); // Generate random resource numbers
        System.out.println("You received: "); // Print received resources
        System.out.println("Stamina: " + result[0] + ", Mana: " + result[1] + ", Spirit: " + result[2]);

        return result; // Return the generated resources
    }


    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Linux/Mac/ANSI-compatible terminals
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback to print newlines if clear screen fails
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    // Combat method to manage player vs opponent combat
    // NAPAY KUWANG DRE GUYZ ANG PAG INITIALIZE PARA MAKA PILI OG CHARACTERS
    public int combat(Player current, Player opponent) {
        int[] res = wish();  // Assume res[0] is stamina or energy
        Scanner scan = new Scanner(System.in); // Create a Scanner for user input
        Characters opponentCurrent = opponent.getCurrentCharacter(); // Get the opponent's current character
        Characters mc = current.getCurrentCharacter(); // Get the current character of the player
        //int accumulatedDmg = 0; // Variable to accumulate damage
        int choice, damage = 0;
        int a = 0; // Track current character index
    
        do {
            while(true){
                try{
                System.out.println("\nEnemy Current Character: " + opponentCurrent.getName() + ": " + opponentCurrent.getHealth());
                mc.choices(res[a]); // Display choices for current character
                    // Get the player's choice
                    choice = scan.nextInt(); // Read user input for choice
                    if(choice >= 1 && choice<=6){
                        break;
                    }else{
                        System.out.println("Invalid choice. Enter a number from 1 - 6!!!");
                    }
                }catch(Exception e){
                    System.out.println("Invalid choice. Enter a number!!!");
                    scan.next();
                }
            }
            // Perform the attack based on the choice
            if (choice < 4) {
                damage = performAttack(a, res, choice, opponent, mc); // Perform the attack
                //accumulatedDmg += damage; // Accumulate damage
    
                // Check if the opponent's character is dead
                if (!opponentCurrent.isAlive()) {
                    System.out.println("\n" + opponentCurrent.getName() + " has been defeated!");
                    // Automatically switch to the next alive opponent character
                    boolean switched = opponent.switchToNextAliveCharacter();
                    if (!switched) {
                        // No more alive characters, opponent loses
                        System.out.println("\n" + opponent.getName() + " has no characters left!");
                        return 1; // Return 1 to indicate win
                    }
                    opponentCurrent = opponent.getCurrentCharacter(); // Update the opponent's current character
                }
            }
    
            // Handle switching characters and other choices
            switch (choice) {
                case 4: // Switch character
                    System.out.println("Choose a character to switch to: ");
                    current.printAllCharacterStatus(res);
                    a = scan.nextInt() - 1;
                    if (!current.switchCharacter(a)) {
                        System.out.println("Cannot switch to that character. Try again.");
                    } else {
                        mc = current.getCurrentCharacter(); // Update current character
                    }
                    break;
    
                case 5: // Reroll stamina/energy
                    res = wish(); // Reroll resources
                    break;
    
                case 6: // End turn
                    displayWithDelay(mc.getName() + " decides to regroup and ends their turn.", 150);
                    return 0; // Return to end turn
    
                default:
                    if (choice < 1 || choice > 6) {
                        displayWithDelay("Invalid choice. Please select an action.", 300); // Error message for invalid choice
                    }
                    break;
            }
            
        } while (current.hasAliveCharacters() && opponent.hasAliveCharacters() || choice == 6);
    
        return damage; 
    }
    
    
    // Switch to the next alive character in the opponent's team
    public boolean switchToNextAliveCharacter() {
        for (int i = 0; i < characters.length; i++) {
            if (characters[i].isAlive()) {
                index = i; // Switch to the next alive character
                System.out.println("\nEnemy Character is Switching to " + characters[i].getName());
                return true; // Successful switch
            }
        }
        return false; // No alive characters left
    }
    

    // Method to perform the chosen attack
    private int performAttack(int i,  int[] res, int choice, Player opponent,Characters current) {
        int damage = 0; // Initialize damage variable
        switch (choice) {
            case 1: 
                    current.basicAttack(res[i], opponent.getCurrentCharacter()); 
                    if(res[i] >= 2){
                        res[i] -= 2; 
                    }
                break;
            case 2: // Skill attack
                    current.skill(res[i], opponent.getCurrentCharacter());
                    if(res[i] >= 5){
                        res[i] -= 5; 
                    }
                break;
            case 3: // Ultimate attack
                    current.ult(res[i], opponent.getCurrentCharacter());
                    if(res[i] >= 8){
                        if (current.getName().equals("Aquamancer")) { // naa diri ang heal sa aquamancer gi implement
                            int healAmount = current.getRandomBetween(20, 30);
                            healAllCharacters(healAmount);
                            displayWithDelay("All allies are healed for " + healAmount + " health points!", 150);
                            displayWithDelay("You now have " + (res[i]-8) + " mana/energy left.", 150);
                        }
                        res[i] -=8; 
                    }
                break;
            default: // Invalid choice
                System.out.println("Invalid choice for attack.");
                break;
        }
        return damage; // Return damage dealt
    }

    // Display text with a delay for dramatic effect
    public void displayWithDelay(String text, int delayInMillis) {
        String[] words = text.split(" "); // Split text into words
        for (String word : words) {
            System.out.print(word + " ");
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
        System.out.println();
    }

}
