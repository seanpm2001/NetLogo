// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.mc;

public class Person {

  private String firstName;
  private String lastName;
  private int id;
  private String avatarURL;
  private String emailAddress;

  public Person(String firstName, String lastName, int id, String avatarURL, String emailAddress) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.avatarURL = avatarURL;
    this.emailAddress = emailAddress;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getId() {
    return id;
  }

  public String getAvatarURL() {
    return avatarURL;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

}

