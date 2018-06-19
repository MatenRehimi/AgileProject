public class Project {

  private int ID;
  private int managerID;
  private String name;

  public Project(int ID, int managerID, String name) {
    this.ID = ID;
    this.managerID = managerID;
    this.name = name;
  }

  public int getID() {
    return ID;
  }

  public int getManagerID() {
    return managerID;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return name;
  }
}
