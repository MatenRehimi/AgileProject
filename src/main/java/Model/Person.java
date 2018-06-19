public class Person {

  private int ID;
  private String name;
  private String username;
  private String password;

  public Person(int ID, String name, String username, String password) {
    this.ID = ID;                   this.name = name;
    this.username = username;       this.password = password;
  }

  public int getID() {
    return ID;
  }

}
