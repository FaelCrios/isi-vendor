package com.isi.isivendor.service;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWrite {

    @SuppressWarnings("unchecked")
    public  static void CreateJson(String[] args ){
        JSONObject jsonObject = new JSONObject();

        FileWriter fileWriter = null;

        jsonObject.put("nome","Rafael");
        jsonObject.put("sobrenome","Colin Rios");
        jsonObject.put("email","faelnek@gmail.com");
        jsonObject.put("telefone","16999991111");
        jsonObject.put("senha","123123");

        try {
            fileWriter = new FileWriter("bd.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println(jsonObject);
    }

}
