/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idhamcrud;

/**
 *
 * @author MSI GAMING
 */
public class Member {

    private String username;
    private int level;
    private int id;
    private String clas;

    public Member(String username, int level, int id, String clas) {
        super();
        this.username = username;
        this.level = level;
        this.id = id;
        this.clas = clas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return clas;
    }

    public void setRole(String clas) {
        this.clas = clas;
    }

}
