import java.util.Scanner;

class Pet {
    private int energyMax;
    private int hungryMax;
    private int cleanMax;
    private int energy;
    private int hungry;
    private int clean;
    int diamonds;
    int age;
    private boolean alive;

    public Pet(int energyMax, int hungryMax, int cleanMax) {
        this.energyMax = energyMax;
        this.hungryMax = hungryMax;
        this.cleanMax = cleanMax;
        this.energy = energyMax;
        this.hungry = hungryMax;
        this.clean = cleanMax;
        this.diamonds = 0;
        this.age = 0;
        this.alive = true;
    }

    // All sets
    private void setEnergy(int value){
        this.energy += value;
        if(this.getEnergy() > this.getEnergyMax())
            this.energy = this.getEnergyMax();
        if(this.energy <= 0){
            energy = 0;
            System.out.println("fail: pet morreu de fraqueza");
            alive = false;
            return;
        }
    }

    private void setHungry(int value) {
        this.hungry += value;
        if(this.hungry > this.hungryMax)
            this.hungry = this.hungryMax;
        if(this.hungry <= 0) {
            hungry = 0;
            System.out.println("fail: pet morreu de fome");
            alive = false;
            return;
        }
    }

    private void setClean(int value) {
        this.clean += value;
        if(this.clean > this.cleanMax)
            this.clean = this.cleanMax;
        if(this.clean <= 0) {
            clean = 0;
            System.out.println("fail: pet morreu de sujeira");
            alive = false;
            return;
        }
    }
    
    // All gets
    public int getEnergyMax() {
        return this.energyMax;
    }

    public int getHungryMax() {
        return this.hungryMax;
    }

    public int getCleanMax() {
        return this.cleanMax;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getHungry() {
        return this.hungry;
    }

    public int getClean() {
        return this.clean;
    }

    public int getDiamonds() {
        return this.diamonds;
    }

    public int getAge() {
        return this.age;
    }

    public boolean getAlive() {
        return this.alive;
    }

    boolean testDead() {
        if(!alive){
            System.out.println("fail: o pet esta morto");
            return true;
        }
        return false;
    }

    /*
        Play
    */
    public void play() {
        if(testDead()) 
            return;
        this.setEnergy(-2);
        this.setHungry(-1);
        this.setClean(-3);
        this.diamonds++;
        this.age++;
    }

    /*
        Shower
    */
    public void shower() {
        if(testDead())
            return;
        this.setEnergy(-3);
        this.setHungry(-1);
        this.setClean(this.getCleanMax());
        this.age++;
    }

    /*
        Eat
    */
    public void eat() {
        if(testDead())
            return;
        this.setEnergy(-1);
        this.setHungry(+4);
        this.setClean(-2);
        this.age++;
    }

    /*
        Sleep
    */
    public void sleep() {
        if(testDead())
            return;
        if(this.getEnergy()-this.getEnergyMax() < 5) {
            System.out.println("fail: nao esta com sono");
            return;
        }
        this.age += this.getEnergyMax() - this.getEnergy();
        this.setEnergy(this.getEnergyMax());
    }

    public String toString() {
        return "E:" + this.energy + "/" + this.energyMax + ", S:" + this.hungry + "/" + this.hungryMax + ", L:" + this.clean + "/" + this.cleanMax + ", D:" + this.diamonds + ", I:" + this.age;
    }


    public static void main(String[] args) {
        Pet tamagotchi = null;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");

            if(ui[0].equals("$init")) {
                tamagotchi = new Pet(Integer.parseInt(ui[1]), 
                                     Integer.parseInt(ui[2]), 
                                     Integer.parseInt(ui[3]));        
            } else if(ui[0].equals("$play")) {
                tamagotchi.play();
            } else if(ui[0].equals("$eat")) {
                tamagotchi.eat();
            } else if(ui[0].equals("$clean")) {
                tamagotchi.shower();
            } else if(ui[0].equals("$sleep")) {
                tamagotchi.sleep();
            } else if(ui[0].equals("$show")) {
                System.out.println(tamagotchi);
            } else if(ui[0].equals("$end")) {
              break;
            } else {
              System.out.println("comando invalido");
            }
        }
        scanner.close();
    }
}
