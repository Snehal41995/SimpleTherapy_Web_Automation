package com.simpleTherapy.web.pages;

import com.simpleTherapy.web.utils.ExcelReader;

public class CustomerConfiguration extends BaseClass {

    // Declare ExcelReader instance to read data from Excel file
    private final ExcelReader reader;

    // Declare multiple sheet name variables for different test modules
    private final String sheetName1;

    // Constructor initializes ExcelReader and assigns sheet names
    public CustomerConfiguration(String sheetName) {
        this.reader = new ExcelReader(Constants.SimpleTherapy_TestData); // Load Excel file path from constants
        this.sheetName1 = sheetName;
    }

    // ---------- Landing Page Data ----------
    // Fetch title heading
    public String getTitleHeadingFromExcel() {
        return reader.getCellData(sheetName1, "TitleHeading", 2);
    }

    // Fetch employer name
    public String getEmployerNameFromExcel () {
        return reader.getCellData(sheetName1, "EmployerName", 2);
    }

    // Fetch operator heading
    public String getChatBotHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ChatBotHeading", 2);
    }

    // Fetch popup heading
    public  String getPopupHeadingFromExcel() {
        return reader.getCellData(sheetName1, "NeedHelpPopupHeading", 2);
    }

    // ---------- Consent Page Data ----------
    public String getConsentHeadingFromExcel() {
        return reader.getCellData(sheetName1, "ConsentHeading", 2).trim();
    }

    // ---------- Great News Page Data ----------
    public String getGreatNewsHeadingFromExcel() {
        return reader.getCellData(sheetName1, "GreatNewsHeading", 2);
    }

    // ---------- Name Details Page Data ----------
    public String getNameDetailsHeadingFromExcel() {
        return reader.getCellData(sheetName1, "NameDetailsHeading", 2);
    }

    public String getFirstNameFromExcel() {
        return reader.getCellData(sheetName1, "FirstName", 2);
    }

    public String getLastNameFromExcel() {
        return reader.getCellData(sheetName1, "LastName", 2);
    }

    public String getPrefNameFromExcel() {
        return reader.getCellData(sheetName1, "PrefName",2);
    }

    // ---------- First Greeting Page Data ----------
    public String getFirstGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName1, "FirstGreetingHeading", 2);
    }

    // ---------- Second Greeting Page Data ----------
    public String getSecondGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName1, "SecondGreetingHeading", 2);
    }
}


