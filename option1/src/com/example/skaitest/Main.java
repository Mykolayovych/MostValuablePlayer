package com.example.skaitest;

import com.example.skaitest.service.ServiceCalculationMVP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        //Введення каталогу для пошуку файлів для турніру.
        //За умовчанням файли ігор зберігаються у games
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the name of directory which contains the files: ");
        String directory = br.readLine();
        ServiceCalculationMVP mvp = new ServiceCalculationMVP(directory);
        System.out.println(mvp.getMVP());
    }

}
