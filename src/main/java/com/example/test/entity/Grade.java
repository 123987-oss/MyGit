package com.example.test.entity;

/*************************************************
 *@className ${FILL_NAME}
 *@author lj
 *@date 2020/6/25-16:10
 *version  v1.0
 *************************************************/
public class Grade {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
