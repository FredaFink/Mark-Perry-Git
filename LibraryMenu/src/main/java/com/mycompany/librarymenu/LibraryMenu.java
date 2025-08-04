/*
 * Click nbfs:/\nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 */

package com.mycompany.librarymenu;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Mark Perry
 * Student id M293122
 * Date created 2025/08/01
 */

/* Notes:
 * 1. Preferably all the data needs to stored in a database but
 *    temporarily could try storing in files.
 * 2. Need to create a class UML.
 * 3. Need to login to borrow and pay.
 * 4. In newMember() need to allow re-entry for each item.
*/
public class LibraryMenu
{
/** This defines the menu options to access
  * modular Library system application that allows access to books
  * for borrowing purposes.  Initial options are:
  * Apply for membership to the library;
  * Borrow one or more books;
  * Search for a book;
  * Return one or more books.
  * Pay a later return fee.
  * @param: (The entry point takes no arguments.)
  */
    // Data available to all methods.

    // Scanner object.
    static Scanner sc = new Scanner(System.in);

    // Whether debug statements should be written.
    static boolean bDebug = true;

    // Files paths containing our data.
    static String sBooksFile = "Books.txt";
    static String sUsersFile = "Users.txt";

    /*************************************************************************
    * Main entry point. Display and process the menu.
    **************************************************************************/

    public static void main(String[] args)
    {
        final String c_sErrExit = "Sorry, the program cannot run and must exit.";

        // First load our data.
        if (!loadBooks())
        {
            System.out.println("ERROR: file " + sBooksFile + " was not found!");
            System.out.println(c_sErrExit);
            return;
        }
        if (!loadUsers())
        {
            System.out.println("ERROR: file " + sUsersFile + " was not found!");
            System.out.println(c_sErrExit);
            return;
        }
        while (true)
        {
            switch(displayMainMenu())
            {
                case '1':
                    newMember();
                    break;
                case '2':
                    bookBorrow();
                    break;
                case '3':
                    bookSearch();
                    break;
                case '4':
                    bookReturn();
                    break;
                case '5':
                    payFee();
                    break;
                default:        // exit selected
                    if (bDebug) System.out.println(">>>>Debug: exiting");
                    sc.close();
                    return;
            }
        }
    }

    /*************************************************************************
    * Function to display the menu
    **************************************************************************/

    private static char displayMainMenu()
    {
        String sInput = "";

        while (true)
        {
            clearScreen();
            System.out.println("Welcome to the Library system.\n" +
                       "Here you can select one the following functions:\n"+
                       "================================================");
           System.out.println("\t1. Apply for membership to the library.\n" +
                              "\t2. Borrow one or more books.\n" +
                              "\t3. Search for a book.\n" +
                              "\t4. Return one or more books.\n" +
                              "\t5. Pay a late return fee.\n" +
                              "\t0. Exit from this system\n");
            System.out.print("Please enter the number of a menu choice: ");
            sInput = sc.nextLine();
            if (sInput.isEmpty()       ||
                sInput.length() > 1    ||
                sInput.charAt(0) > '5' ||
                sInput.charAt(0) < '0')
            {
                 System.out.println("\nI'm afraid your input is invalid.\n" +
                         "Press ENTER to re-enter your choice.\n");
                 sInput = sc.nextLine();
                 continue;
            }
            break;
        }
        return sInput.charAt(0);
    }

    /*************************************************************************
     * Function/method to add a new member.
    **************************************************************************/

    private static void newMember()
    {
        final String c_sPressEnter = "\nOtherwise just press ENTER.";
        final String c_sRetry = "\nPlease retry.";
        final String c_sMandatory = " This is a required entry";
        final String c_sAccept = "\nJust press ENTER to accept " +
                                 "your previous entry.";

        String sFirstName = "";
        String sMiddleName = "";
        String sLastName = "";
        String sFullName;
        String sAddress = "";
        String sMobile = "";
        String sLandLine = "";
        String sEmail = "";

        String sInput;

        if (bDebug) System.out.println(">>>>Debug: newMember() entry");

         while (true)
        {
            clearScreen();
            System.out.println("To become a member of the library.\n" +
                            "We need some of your details.\n");

            // Get a mandatory first name.
            // For ease of re-entry we display and allow any
            // previous entry to be used.
            System.out.println("Enter your First Name." + c_sMandatory);
            if (!sFirstName.isEmpty())
            {
                System.out.println("Default: " + "\"" + sFirstName+ "\"");
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            if (sFirstName.isEmpty())
            {
                if (sInput.isEmpty() || sInput.contains(" "))
                {
                    System.out.println("Your first name appears to be invalid."
                                       + c_sRetry);
                    continue;
                }
                sFirstName = sInput;
            }
            // Get an optional Middle name or initial.
            System.out.println("Enter your Middle Name or initial " +
                               "if you have one" + c_sPressEnter);
            if (!sMiddleName.isEmpty())
            {
                System.out.println("Default: " + "\"" + sMiddleName + "\"");
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            if (!sInput.isEmpty())
            {
                sMiddleName = sInput;
            }

            // Get a mandatory Surname.
            System.out.println("Enter your Last Name/Surname." +
                                c_sMandatory + ":");
            if (!sLastName.isEmpty())
            {
                System.out.println("Default: " + sLastName);
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            if (sLastName.isEmpty())
            {
                if (sInput.isEmpty() || sInput.contains(" "))
                {
                    System.out.println("Your last name appears to be invalid." +
                                       c_sRetry);
                    continue;
                }
                sLastName = sInput;
            }
            sFullName = sFirstName +
                        (sMiddleName.isEmpty() ? " " : sMiddleName + " ") +
                         sLastName;

            // Get a mandatory address.
            System.out.println("Enter your full postal or residential address" +
                               " on a single line separated by commas:\n" +
                               c_sMandatory);
            if (!sAddress.isEmpty())
            {
                System.out.println("Default: " + sAddress);
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            if (sInput.isEmpty() || !sInput.contains(","))
            {
                System.out.println("You must provide a valid address." +
                                   c_sRetry);
                continue;
            }

            // Get an optional mobile number.
            System.out.println("Enter your mobile number if you have one." +
                                c_sPressEnter + sMobile);
            if (!sMobile.isEmpty())
            {
                System.out.println("Default: " + sMobile);
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            if (!checkPhone(sInput))
            {
                System.out.println("The mobile number you entered is invalid." +
                                   c_sRetry);
                continue;
            }
            sMobile = sInput;

            // Get an optional landline number.
            System.out.println("Enter your telephone landline number if " +
                               "you have one." + c_sPressEnter + sLandLine);
            if (!sLandLine.isEmpty())
            {
                System.out.println("Default: " + sLandLine);
                System.out.println(c_sAccept);
            }
            System.out.print(": ");
            sInput = sc.nextLine();
            // Check as for Mobile
            sLandLine = sInput;

            // Get an optional email address.
            System.out.println("Enter your email address if you have one." +
                                c_sPressEnter + sEmail);
            sInput = sc.nextLine();
            if (sInput.isEmpty() || !checkEmail(sInput))
            {
                System.out.println("The email address you entered is invalid." +
                                   c_sRetry);
                continue;
            }
            sEmail = sInput;

            System.out.println("\nThank you.\n");
            if (sMobile.isEmpty() && sLandLine.isEmpty() && sEmail.isEmpty())
            {
                System.out.println("Unfortunately you must provide at least " +
                                   "one of: mobile or landline or email." +
                                   "\n You have not done so." + c_sRetry);
                continue;
            }
            System.out.println("To be sure we have the correct data, " +
                               "please confirm the following:");
            System.out.println("Your name is: " + sFullName);
            System.out.println("Your address is: " + sAddress);
            if  (!sMobile.isEmpty())
            {
                 System.out.println("Your mobile number is: " + sMobile);
            }
            if  (!sLandLine.isEmpty())
            {
                 System.out.println("Your land line number is: " + sLandLine);
            }
            if  (!sEmail.isEmpty())
            {
                 System.out.println("Your email is: " + sEmail);
            }

            System.out.println("Is this correct? Enter Y(es) or N(o)");
            sInput = sc.nextLine();
            if (!sInput.isEmpty())
            {
                sInput = sInput.toUpperCase();
                // Assume anything other than Y(es) is a N(o).
                if (sInput.substring(0,1) == "Y")
                {
                    // We now have user data so can create a user object
                    // to store preferably in a database.
                    // We need to create an object of Class user with
                    // these details plus generate an appropriate number to
                    // ensure uniqueness.
                    // NB: Forget providing a library card.

                    break;
                }
             }
        }
    }

    /*************************************************************************
     * Function/method to load books from a file.
     **************************************************************************/

    private static boolean loadBooks()
    {
        if (bDebug) System.out.println(">>>>Debug: loadBooks()");
        return true;
    }

    /*************************************************************************
     * Function/method to load users from a file.
     **************************************************************************/

    private static boolean loadUsers()
    {
        if (bDebug) System.out.println(">>>>Debug: loadUsers()");
        return true;
    }

   /*************************************************************************
     * Function/method to borrow a book.
     **************************************************************************/

    private static void bookBorrow()
    {
        if (bDebug) System.out.println(">>>>Debug: bookBorrow()");
    }

    /*************************************************************************
     * Function/method to search for a book.
    **************************************************************************/

    private static void bookSearch()
    {
        if (bDebug) System.out.println(">>>>Debug: bookSearch()");
        // We need some books
    }

    /*************************************************************************
     * Function/method to return a book.
    **************************************************************************/

    private static void bookReturn()
    {
        if (bDebug) System.out.println(">>>>Debug: bookReturn()");
    }

    /*************************************************************************
     * Function/method to pay a later return fee.
    **************************************************************************/

   private static void payFee()
    {
        if (bDebug)
            System.out.println(">>>>Debug: payFee()");
    }

    /*************************************************************************
     * Function/method to clear the console screen.
     **************************************************************************/

    private static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
     /*   try
        {
            new ProcessBuilder("cmd", "/c", "cls").
                                inheritIO().start().waitFor();
        }
        catch (Exception e)
        {
            if (bDebug)
                System.out.println("Debug>>>> Exception clearing the screen.");
        } */
    }

    /*************************************************************************
    * Function/method to check an email address.
    * The following restrictions are imposed on the local part:
    * It allows numeric values from 0 to 9.
    * Both uppercase and lowercase letters from a to z are allowed.
    * Allowed are underscore “_”, hyphen “-“, and dot “.”
    * Dot is not allowed at the start and end of the local part.
    * Consecutive dots are not allowed.
    * For the local part, a maximum of 64 characters are allowed.
    *
    * Restrictions for the domain part include:
    * It allows numeric values from 0 to 9.
    * We allow both uppercase and lowercase letters from a to z.
    * Hyphen “-” and dot “.” are not allowed at the start and end of the domain part.
    * No consecutive dots.
     **************************************************************************/

    private static boolean checkEmail(String sTest)
    {
        final String regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\." +
                                "[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+" +
                                "(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexp).matcher(sTest).matches();
    }

    /*************************************************************************
     * Function/method to check phone numbers. A USA test. Is it valid?
     **************************************************************************/

    private static boolean checkPhone(String sTest)
    {
        final String regex = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?" +
                             "(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]" +
                             "1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]" +
                             "|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:" +
                             "[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|" +
                             "[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]" +
                             "{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)" +
                             "\\s*(\\d+))?$";
        return Pattern.compile(regex).matcher(sTest).matches();
    }
}