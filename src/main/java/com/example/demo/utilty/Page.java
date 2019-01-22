package com.example.demo.utilty;

public class Page {


    int start = 1;
    int count = 5;
    int last = 0;
    private String question;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", last=" + last + ", question=" + question + ", name="
                + name + "]";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public void caculateLast(int total) {
        if (0 == total % count)
            last = total / count;

        else
            last = total / count + 1;

    }

}
