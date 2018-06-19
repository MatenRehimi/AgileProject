public class Task {

  private int ID;
  private int projectID;
  private String name;
  private int effortEstimate;
  private Task[] prerequisiteTasks;

  public Task(int ID, int projectID, String name, int effortEstimate, Task[] prerequisiteTasks) {
    this.ID = ID;
    this.projectID = projectID;
    this.name = name;
    this.effortEstimate = effortEstimate;
    this.prerequisiteTasks = prerequisiteTasks;
  }

  public int getID() {
    return ID;
  }

  public int getProjectID() {
    return projectID;
  }

  public String getName() {
    return name;
  }

  public int getEffortEstimate() {
    return effortEstimate;
  }

  public Task[] getPrerequisiteTasks() {
    return prerequisiteTasks;
  }

}
