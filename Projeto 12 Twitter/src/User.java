import java.util.TreeMap;

public class User {
  private String username;
  private TreeMap<String, User> followers;
  private TreeMap<String, User> following;
  private TreeMap<Integer, Tweet> timeline;
  private int unreadCount;

  public User(String username) {
    this.username = username;
    this.followers = new TreeMap<>();
    this.following = new TreeMap<>();
    this.timeline = new TreeMap<>();
    unreadCount = 0;
  }
  
  public String getUsername() {
    return this.username;
  }

  public Tweet getTweet(int id) {
    if(!timeline.containsKey(id)) {
      throw new RuntimeException("fail: tweet nao encontrado");
    }
    return this.timeline.get(id);
  }

  public String getUnread() {
    String saida = "";
    for(int i = this.timeline.size() - unreadCount; i < this.timeline.size(); i++) {
      saida += this.timeline.get(i);
    }
    unreadCount = 0;
    return saida;
  }

  public String getTimeline() {
    String saida = "";
    for(Tweet tweet : this.timeline.values()) {
      saida += tweet;
    }
    unreadCount = 0;
    return saida;
  }

  public void follow(User other) {
    if(following.containsKey(other.username)) {
      throw new RuntimeException("fail: Você já segue esse usuário");
    }
    this.following.put(other.username, other);
    other.followers.put(this.username, this);
  }
  
  public void unfollow(User user) {
    if(!following.containsKey(user.getUsername())) {
      throw new NullPointerException("fail: Você não segue esse usuário");
    }
    this.following.remove(user.getUsername());
    user.followers.remove(this.getUsername());
  }
  
  public void sendTweet(Tweet tweet) {
    this.timeline.put(tweet.getId(), tweet);
    for(User user : followers.values()) {
      user.timeline.put(tweet.getId(), tweet);
      user.unreadCount++;
    }
  }

  public String toString() {
    String saida = "";
    saida += this.getUsername() + "\n";
    saida += " seguidos    [";
    for(User user : this.following.values()) {
      saida += " " + user.getUsername() + " ";
    }
    saida += "]\n seguidores  [";
    for(User user : this.followers.values()) {
      saida += " " + user.getUsername() + " ";
    }
    saida += "]";
    return saida;
  }
}

