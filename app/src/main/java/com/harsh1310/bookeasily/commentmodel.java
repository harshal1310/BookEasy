package com.harsh1310.bookeasily;

public class commentmodel {
    String name,commentedtext;
int imag;
    public commentmodel() {
      //  this.name = name;
    }

    public commentmodel(String name, String commentedtext) {
        this.name = name;
        this.commentedtext = commentedtext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentedtext() {
        return commentedtext;
    }

    public void setCommentedtext(String commentedtext) {
        this.commentedtext = commentedtext;
    }

    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }
}
