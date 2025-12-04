package com.SimpleTherapy.web.pages;

import com.SimpleTherapy.web.utils.ExcelReader;

public class CustomerConfiguration extends BaseClass {

    // Declare ExcelReader instance to read data from Excel file
    private final ExcelReader reader;

    // Declare multiple sheet name variables for different test modules
    private final String sheetName1;
    private final String sheetName2;
    private final String sheetName3;
    private final String sheetName4;
    private final String sheetName5;
    private final String sheetName6;

    // Constructor initializes ExcelReader and assigns sheet names
    public CustomerConfiguration(String sheetName) {
        this.reader = new ExcelReader(Constants.SimpleTherapy_TestData); // Load Excel file path from constants
        this.sheetName1 = sheetName; // Assign same sheet name to each (can vary if needed)
        this.sheetName2 = sheetName;
        this.sheetName3 = sheetName;
        this.sheetName4 = sheetName;
        this.sheetName5 = sheetName;
        this.sheetName6 = sheetName;
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
    public String getOperatorHeadingFromExcel() {
        return reader.getCellData(sheetName1, "OperatorHeading", 2);
    }
    // Fetch popup heading
    public  String getPopupHeadingFromExcel() {
        return reader.getCellData(sheetName1, "PopupHeading", 2);
    }

    // Fetch polish title heading
    public String getPolishTitleHeadingFromExcel() {
        return reader.getCellData(sheetName1, "PolishLanguageTitleHeading", 2);
    }

    public String getExpectedLanguageCodeFromExcel() {
        return reader.getCellData(sheetName1, "ExpectedLanguageCode", 2);
    }

    // ---------- Consent Page Data ----------
    public String getConsentHeadingFromExcel() {

        return reader.getCellData(sheetName2, "ConsentHeading", 2).trim();
    }

    // ---------- Great News Page Data ----------
    public String getGreatNewsHeadingFromExcel() {

        return reader.getCellData(sheetName3, "GreatNewsHeading", 2);
    }

    // ---------- Name Details Page Data ----------
    public String getNameDetailsHeadingFromExcel() {
        return reader.getCellData(sheetName4, "NameDetailsHeading", 2);
    }

    public String getFirstNameFromExcel() {
        return reader.getCellData(sheetName4, "FirstName", 2);
    }

    public String getLastNameFromExcel() {
        return reader.getCellData(sheetName4, "LastName", 2);
    }

    public String getPrefNameFromExcel() {
        return reader.getCellData(sheetName4, "PrefName",2);
    }

    // ---------- Greeting Page Data ----------
    public String getGreetingHeadingFromExcel() {
        return reader.getCellData(sheetName5, "GreetingHeading", 2);
    }

    // ---------- ProgramsInfo Page Data ----------
    public String getProgramsInfoHeadingFromExcel() {
        return reader.getCellData(sheetName6, "ProgramsInfoHeading", 2);
    }

}


