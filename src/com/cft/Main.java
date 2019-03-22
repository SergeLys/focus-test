package com.cft;

import com.cft.gui.MainForm;

public class Main {

    public static void main(String[] args) {

        final int width = 600;
        final int height =300;

        MainForm mainForm =  new MainForm();
        mainForm.pack();
        mainForm.setSize(width,height);
        mainForm.setVisible(true);
    }
}
