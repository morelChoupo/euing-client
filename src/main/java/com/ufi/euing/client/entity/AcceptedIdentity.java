package com.ufi.euing.client.entity;

import java.io.Serializable;

public class AcceptedIdentity implements Serializable {
    private int Id;
    private String Name;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public AcceptedIdentity(int id, String name) {
        super();
        Id = id;
        Name = name;
    }

    public AcceptedIdentity() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "AcceptedIdentity [Id=" + Id + ", Name=" + Name + "]";
    }

}
