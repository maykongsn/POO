import java.util.TreeMap;

class Controller { 
  private TreeMap<String, User> users;
  private TreeMap<Integer, Tweet> tweets;
  private int nextId;

  public Controller() {
    this.users = new TreeMap<>();
    this.tweets = new TreeMap<>();
    this.nextId = 0;
  }

  public void sendTweet(String username, String msg) {
    if(!users.containsKey(username)) {
      throw new NullPointerException("fail: Usuário não encontrado");
    }
    Tweet tweet = new Tweet(this.nextId, username, msg);
    this.tweets.put(this.nextId, tweet);
    this.users.get(username).sendTweet(tweet);
    nextId++;
  }

  public void addUser(String username) {
    if(users.containsKey(username)) {
      throw new RuntimeException("fail: Usuário já está cadastrado");
    }
    this.users.put(username, new User(username));
  }

  public User getUser(String username) {
    if(!users.containsKey(username)) {
      throw new RuntimeException("fail: Usuário não encontrado");
    }
    return this.users.get(username);
  }

  public String toString() {
    String saida = "";
    for(User user : users.values()) {
      saida += user.toString() + "\n";
    }
    return saida;
  }
}