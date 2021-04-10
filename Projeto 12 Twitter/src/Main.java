import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Controller sistema = new Controller();
  
    while(true){
        String line = scanner.nextLine();
        String ui[] = line.split(" ");
        try{
            if(ui[0].equals("end")){
                break;
            } else if(ui[0].equals("addUser")) {
                sistema.addUser(ui[1]);
            } else if(ui[0].equals("follow")) {
                User user = sistema.getUser(ui[1]);
                User other = sistema.getUser(ui[2]);
                user.follow(other);
            }  else if(ui[0].equals("unfollow")) {
                User user = sistema.getUser(ui[1]);
                User other = sistema.getUser(ui[2]);
                user.unfollow(other);
            }  else if(ui[0].equals("like")) {
                User user = sistema.getUser(ui[1]);
                Tweet tweet = user.getTweet(Integer.parseInt(ui[2]));
                tweet.like(ui[1]);
            } else if(ui[0].equals("twittar")) {
                String username = ui[1];
                String msg = "";
                for(int i = 2; i < ui.length; i++)
                    msg += ui[i] + " ";
                sistema.sendTweet(username, msg);
            } else if(ui[0].equals("timeline")) {
                User user = sistema.getUser(ui[1]);
                System.out.print(user.getTimeline());
            } else if(ui[0].equals("show")) {
                System.out.print(sistema);
            } else {
                System.out.println("fail: comando invalido");
            }
        } catch(IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch(RuntimeException err) {
            System.out.println(err.getMessage());
        }
    }
    scanner.close();
  }
}