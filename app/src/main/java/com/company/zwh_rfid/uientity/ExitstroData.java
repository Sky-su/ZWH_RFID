package com.company.zwh_rfid.uientity;


import java.util.List;

public class ExitstroData {

    private List<Children> children;
    private MainEnt mainEnt;

    @Override
    public String toString() {
        return "ExitstroData{" +
                "children=" + children +
                ", mainEnt=" + mainEnt +
                '}';
    }

    public ExitstroData() {
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public MainEnt getMainEnt() {
        return mainEnt;
    }

    public void setMainEnt(MainEnt mainEnt) {
        this.mainEnt = mainEnt;
    }
}
