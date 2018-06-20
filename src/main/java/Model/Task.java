import java.util.ArrayList;

public class Task {

  private int ID;
  private int projectID;
  private String name;
  private int effortEstimate;
  private ArrayList<Integer> prerequisiteTasks;

  public Task(int ID, int projectID, String name, int effortEstimate, ArrayList<Integer> prerequisiteTasks) {
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

  public ArrayList<Integer> getPrerequisiteTasks() {
    return prerequisiteTasks;
  }

  public void removePrerequisiteTask(int ID) {
    for (int i = 0; i < prerequisiteTasks.size(); i++) {
      if (prerequisiteTasks.get(i) == ID) {
        prerequisiteTasks.remove(i);
      }
    }
  }

  public String toString() {
    String preRequisiteTasks = "Empty";
    if (prerequisiteTasks.size() == 1) {
      preRequisiteTasks = Integer.toString(prerequisiteTasks.get(0));
      for (int i = 1; i < prerequisiteTasks.size(); i++) {
        preRequisiteTasks = preRequisiteTasks + ", " + Integer.toString(prerequisiteTasks.get(i));
      }
    }

    return "Number: " + ID + ", Name: " + name + ", Effort: " + effortEstimate + ", Prerequisite Tasks: " + preRequisiteTasks;
  }

}
