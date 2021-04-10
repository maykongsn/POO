import java.util.TreeSet;

public class Tweet {
  private int id;
  private String username;
  private String msg;
  private TreeSet<String> likes;

  public Tweet(int id, String username, String msg) {
    this.id = id;
    this.username = username;
    this.msg = msg;
    this.likes = new TreeSet<>();
  }

  public int getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getMessage() {
    return this.msg;
  }

  public void like(String username) {
    for(String user : likes) {
      if(user.equals(username)) {
        throw new RuntimeException("Você já curtiu esse tweet");
      }
    }
    this.likes.add(username);
  }

  public String toString() {
    String solver = "";
    String temp = "";

    for(String name : this.likes) {
      temp += name + " ";
    }
    if(likes.size() == 0) {
      solver += this.id + ":" + this.username + "( " + this.msg + " )\n"; 
      return solver;
    } else {
      solver += this.id + ":" + this.username + "( " + this.msg + " )" + "[ " + temp + "]\n";
    }
    return solver;
  }
}