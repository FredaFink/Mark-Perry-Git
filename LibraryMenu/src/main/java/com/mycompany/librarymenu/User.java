/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymenu;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Mark Perry
 * Student id: M293122
 */

public class User
{
/**
 * This is class in the Library project to hold details of a library user.
 *
 */
    // Creat  a HashMap as it uses keys.
    HashMap<String, String> userMap = new HashMap<>();

    // Method to initialise the internal storage object
    private void init()
    {
        userMap.put("Identifier", "");
        userMap.put("FirstName", "");
        userMap.put("MiddleName", "");
        userMap.put("LastName", "");
        userMap.put("Address", "");
        userMap.put("Mobile", "");
        userMap.put("LandLine", "");
        userMap.put("Email", "");
    }

    // Method to create a new user from a passed array which has
    // the required field in the same order as the internal HashMap.
    private void setUser(String[] aUser)
    {
    }

    private String getFirstName()
    {
       return userMap.get("FirstName");
    }

    private String getLastName()
    {
        return userMap.get("LastName");
    }

    private String getFullName(boolean bReverse)
    {
        if (bReverse)
            return userMap.get("LastName") + ", " +
                   userMap.get("FirstName");
            return userMap.get("FirstName") + " " +
                   userMap.get("Lastname");
    }

}
