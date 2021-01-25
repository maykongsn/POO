import java.util.ArrayList;
import java.util.Scanner;

class Pass {
    String name;
    int age;

    public Pass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + ":" + age; 
    }
}

class Topic {
    ArrayList<Pass> chairs;
    int qtdPref;

    public Topic(int capacity, int qtdPref) {
        chairs = new ArrayList<>();
        this.qtdPref = qtdPref;
        for(int i = 0; i < capacity; i++) {
            chairs.add(null);
        }
    }

    public boolean in(Pass pass) {
        for(Pass chair : chairs) {
            if(chair != null && chair.name.equals(pass.name)) {
                System.out.println("fail: já está na topic");
                return false;
            }
        }

        if(pass.age > 64) {
            for(int i = 0; i < qtdPref; i++) {
                if(chairs.get(i) == null) {
                    chairs.set(i, pass);
                    return true;
                }
            }
        }
        for(int i = qtdPref; i < chairs.size(); i++) {
            if(chairs.get(i) == null) {
                chairs.set(i, pass);
                return true;
            }
        }
        
        for(int i = 0; i < chairs.size(); i++) {
            if(chairs.get(i) == null) {
                chairs.set(i, pass);
                return true;
            }
        }
        System.out.println("topic lotada");
        return false;
    }

    public Pass out(String name) {
        for(int i = 0; i < chairs.size(); i++) {
            if(chairs.get(i) != null && chairs.get(i).name.equals(name)) {
                chairs.set(i, null);
                return chairs.get(i);
            }
        }
        System.out.println("fail: pass nao esta na topic");
        return null;
    }

    public String toString() {
        String saida = "[ ";
        for(int i = 0; i < qtdPref; i++){
            if(chairs.get(i) == null)
                saida += "@ ";
            else
                saida += "@"+chairs.get(i)+" ";
        }
        for(int i = qtdPref; i < chairs.size(); i++){
            if(chairs.get(i) == null)
                saida += "= ";
            else
                saida += "="+chairs.get(i)+" ";
        }
        return saida+"]"; 
    }
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Topic topic = null;
		
		while(true) {
		 	String line = scanner.nextLine();
		 	String[] ui = line.split(" ");
		 	
		 	if(ui[0].equals("init")){
	 			topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
		 	} else if(ui[0].equals("in")) {
		 		topic.in(new Pass(ui[1], Integer.parseInt(ui[2])));
		 	} else if(ui[0].equals("out")) {
		 		topic.out(ui[1]);
		 	} else if(ui[0].equals("show")) {
		 		System.out.println(topic);
		 	} else if(ui[0].equals("end")) {
		 		break;	
		 	} else {
				System.out.println("Comando invalido");
			}
		}
		
		scanner.close();
    }
}